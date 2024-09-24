from pydantic import BaseModel # 유효성 검사 기능을 포함하는 DTO base 클래스
from typing import List

class ChatMessage(BaseModel): # BaseModel 클래스를 상속하는 Todo 클래스 
    message: str

class ChatMessage2(BaseModel):
    role: str
    content: str

class ChatMessageList(BaseModel):
    messages: List[ChatMessage2]

