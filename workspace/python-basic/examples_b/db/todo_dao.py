import pymysql

conn = pymysql.connect(
    host="192.168.0.14", 
    port=3306, 
    user="green_cloud", 
    password="mysql", 
    database="todo"
)

def select_todos():

    todos = None # None : java의 null
    # cursor = conn.cursor()
    with conn.cursor() as cursor: # with 구문이 종료될 때 close() 자동 호출

        sql =   """SELECT id, content, isDone, createdDate 
                FROM todo"""

        cursor.execute(sql)

        todos = cursor.fetchall()    
    # cursor.close()

    return todos