from fastapi import APIRouter

todo_router = APIRouter()

@todo_router.get("/list")
async def todo_list():
    print("--------------------> todo list")
    return {
        "result": "success"
    }