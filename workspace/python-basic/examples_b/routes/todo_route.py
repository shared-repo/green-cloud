from fastapi import APIRouter, Form
from datetime import date

from db import todo_dao
from models.todo import Todo

todo_router = APIRouter()

@todo_router.get("/list")
async def todo_list():
    print("--------------------> todo list")

    todos = todo_dao.select_todos()

    return {
        "metadata": "todo list",
        "data": todos
    }

@todo_router.get("/search")
async def search_todo_list(search_word:str):
    print("--------------------> search todo list")

    todos = todo_dao.select_todos_by_search_word(search_word)

    return {
        "metadata": "search todo list",
        "data": todos
    }

@todo_router.post("/create")
async def create_todo(content: str = Form(...), isDone: bool = Form(...), createdDate: date = Form(...)):
    print("--------------------> create todo")

    todo_dao.insert_todo(content, isDone, createdDate)

    return {
        "metadata": "create todo",
        "data": "success"
    }

@todo_router.post("/create2")
async def create_todo2(todo: Todo):
    print("--------------------> create todo 2")

    todo_dao.insert_todo2(todo)

    return {
        "metadata": "create todo 2",
        "data": "success"
    }