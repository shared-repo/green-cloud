from pydantic import BaseModel # 유효성 검사 기능을 포함하는 DTO base 클래스

class ChatMessage(BaseModel): # BaseModel 클래스를 상속하는 Todo 클래스 
    message: str

