from fastapi import APIRouter

from db import employees_dao

employees_router = APIRouter()

@employees_router.get("/list")
async def employee_list():
    
    employees = employees_dao.select_employees()
    col_names = ["emp_no", "first_name", "last_name", "gender", "hire_date"]
    employees2 = ( { col_name: col for col_name, col in zip(col_names, row) }  for row in employees )
    
    return {
        "metadata": "employees list",
        "data": employees2
    }
