from fastapi import APIRouter

from openai import OpenAI
import os

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

    query = message_list.messages[-1].content # 마지막 요소 반환
    selected_documents = chromadb_helper.query_similar_documents(query, top_k=10)

    selected_documents2 = \
        [ [doc, dist] for (doc, dist) in zip(selected_documents['documents'][0], selected_documents['distances'][0]) ]
    
    context_str = '\n'.join(["recruid-{0}. {1}".format(idx + 1, doc) for idx, (doc, dist) in enumerate(selected_documents2) ]) # 1. .... \n\n 2. ....

    prompt =    """
                당신은 구인-구직 매칭 웹서비스의 컨설턴트입니다.
                당신은 아래 지시사항을 적용해서 고객의 요청을 분석하고 적절한 답변을 제공해야 합니다.

                [지시사항]
                1. 채용공고 관련 요청인 경우 주어진 [채용공고 목록]을 기반으로 적합한 채용공고를 찾아주어야 합니다. 이 때 답변 형식은 번호. 회사이름, 타이틀 형식으로 만들어야 합니다.

                2. 위의 지시사항에 해당되지 않는 경우 다른 정보에 의존하지 않고당신의 기본 지식을 사용해서 답변을 만들어야 합니다.이 때 답변 형식은 번호를 사용하지 않고 자유 문장형식으로 만들어야 합니다.

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
        ] + [{ 'role': message.role, 'content': message.content } for message in message_list.messages],
        n=1, 
        temperature=1
    )

    return {
        "metadata": "chatbot response",
        "message": completion.choices[0].message.content
    }


