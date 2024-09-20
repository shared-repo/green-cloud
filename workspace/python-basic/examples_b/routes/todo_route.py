from fastapi import APIRouter

from db import todo_dao

todo_router = APIRouter()

@todo_router.get("/list")
async def todo_list():
    print("--------------------> todo list")

    todos = todo_dao.select_todos()

    return {
        "metadata": "todo list",
        "data": todos
    }