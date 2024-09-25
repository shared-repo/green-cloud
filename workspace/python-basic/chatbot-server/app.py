import uvicorn # tomcat
from fastapi import FastAPI # servlet
from fastapi.middleware.cors import CORSMiddleware

from routes.chatbot_route import chatbot_router
from routes.admin_route import admin_router

app = FastAPI()

origins = [
    "http://localhost:8080",
    "http://127.0.0.1:8080",
    "http://localhost:5001"
]
app.add_middleware(
    CORSMiddleware,
    allow_origins = origins,
    allow_credentials=True,
    allow_methods=["GET", "POST"],
    allow_headers=["*"]
)

app.include_router(chatbot_router, prefix="/chatbot")
app.include_router(admin_router, prefix="/admin")

if __name__ == "__main__": 
    uvicorn.run("app:app", host='0.0.0.0', port=8088) # localhost, 127.0.0.1, 192.168.0.14