import uvicorn
from fastapi import FastAPI

from routes.todo_route import todo_router
from routes.employees_route import employees_router

app = FastAPI()

# @app.get(path="/")
# async def home():
#     return {
#         "message": "Hello, FastAPI programming world !!!"
#     }

app.include_router(todo_router, prefix="/todo")
app.include_router(employees_router, prefix="/employees")

# print("------------------------> app.py 1")
# print("------------------------>", __name__)

if __name__ == "__main__": # import 할 때에는 실행하지 않음. 터미널에서 python app.py로 실행할 때는 실행
    # print("-----------------------------------> app.py 2")
    uvicorn.run("app:app", host='0.0.0.0', port=8088) # localhost, 127.0.0.1, 192.168.0.14