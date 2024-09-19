import pymysql

def select_employees():

    db = pymysql.connect(host="192.168.0.14", port=3306, user="green_cloud", password="mysql", database="employees")

    cursor = db.cursor() # cursor : 명령을 실행하는 도구 ( Java의 PreparedStatement와 비슷한 역할 )

    sql =   """SELECT emp_no, first_name, last_name, gender, hire_date 
            FROM employees
            LIMIT 0, 100"""

    cursor.execute(sql)

    employees = cursor.fetchall()
    
    cursor.close()
    db.close()

    return employees