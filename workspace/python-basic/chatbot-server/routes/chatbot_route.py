from fastapi import APIRouter

from openai import OpenAI
import os

from models.chatbot_models import ChatMessage

chatbot_router = APIRouter()

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
