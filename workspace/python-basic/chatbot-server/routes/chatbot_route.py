from fastapi import APIRouter, UploadFile, File

from openai import OpenAI
import os
import shutil

from models.chatbot_models import ChatMessage, ChatMessageList
from db import chromadb_helper

chatbot_router = APIRouter()

os.environ['OPENAI_API_KEY'] = "API_KEY"
client = OpenAI()

@chatbot_router.post("/chat-text")
async def process_chat_text(msg: ChatMessage):

    completion = client.chat.completions.create(
        model="gpt-4o-mini",
        messages=[
            {'role': 'system', 'content': 'You are an assistant that explain general topic. You have to answere as possible as short.'},
            {'role': 'user', 'content': msg.message}
        ],
        n=1, 
        temperature=1
    )

    return {
        "metadata": "chatbot response",
        "message": completion.choices[0].message.content
    }

@chatbot_router.post("/chat-text-with-history")
async def process_chat_text_with_history(message_list: ChatMessageList):

    completion = client.chat.completions.create(
        model="gpt-4o-mini",
        messages=[
            {'role': 'system', 'content': 'You are an assistant that explain general topic. You have to answere as possible as short.'}
        # ] + message_list.messages,
        ] + [{ 'role': message.role, 'content': message.content } for message in message_list.messages],
        n=1, 
        temperature=1
    )

    return {
        "metadata": "chatbot response",
        "message": completion.choices[0].message.content
    }

@chatbot_router.post("/search-jobs")
async def process_searching_jobs(message_list: ChatMessageList):

    query = message_list.messages[-1].content # 마지막 질문 요소 반환
    selected_documents = chromadb_helper.query_similar_documents(query, top_k=10) # vectordb에서 질문과 관련된 데이터 조회

    selected_documents2 = \
        [ [doc, dist] for (doc, dist) in zip(selected_documents['documents'][0], selected_documents['distances'][0]) ]
    
    context_str = '\n'.join(["recruid-{0}. {1}".format(idx + 1, doc) for idx, (doc, dist) in enumerate(selected_documents2) ]) # 1. .... \n\n 2. ....

    prompt =    """
                당신은 구인-구직 매칭 웹서비스의 컨설턴트입니다.
                당신은 아래 지시사항을 적용해서 고객의 요청을 분석하고 적절한 답변을 제공해야 합니다.

                [지시사항]
                지시사항 1. 채용공고 관련 요청인 경우 주어진 [채용공고 목록]을 기반으로 적합한 채용공고를 찾아주어야 합니다. 답변에는 회사이름과 채용공고 제목만 포함해야 합니다.
                지시사항 2. 위의 지시사항에 해당되지 않는 경우 당신의 기본 지식을 사용해서 답변을 만들어야 합니다.

                ----------------------------------------------
                
                [채용공고 목록]
                {0}
                """.format(context_str)
    
    print(context_str)

    completion = client.chat.completions.create(
        model="gpt-4o-mini",
        messages=[
            {'role': 'system', 'content': context_str }
        # ] + message_list.messages,
        ] + [{ 'role': message.role, 'content': message.content } for message in message_list.messages[-1:]],
        n=1, 
        temperature=1
    )

    return {
        "metadata": "chatbot response",
        "message": completion.choices[0].message.content
    }


@chatbot_router.post("/audio-to-text")
async def process_audio(audio: UploadFile = File(...)) -> str :
    print( "------------------->", audio.filename )

    with open(audio.filename, "wb") as f:
        shutil.copyfileobj(audio.file, f) # 파일로 저장

    with open(audio.filename, "rb") as f:
        transcription = client.audio.transcriptions.create(
            model='whisper-1',
            file=f
        )

    return transcription.text

