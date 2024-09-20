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

def select_todos_by_search_word(search_word):

    todos = None
    with conn.cursor() as cursor:

        sql =   """SELECT id, content, isDone, createdDate 
                FROM todo
                WHERE content LIKE %s"""

        cursor.execute(sql, ('%' + search_word + '%'))

        todos = cursor.fetchall()

    return todos

def insert_todo(content, isDone, createdDate):

    with conn.cursor() as cursor:

        sql =   """INSERT INTO todo (content, isDone, createdDate)
                VALUES (%s, %s, %s)"""

        cursor.execute(sql, (content, isDone, createdDate))

        conn.commit()

def insert_todo2(todo):

    with conn.cursor() as cursor:

        sql =   """INSERT INTO todo (content, isDone, createdDate)
                VALUES (%s, %s, %s)"""

        cursor.execute(sql, (todo.content, todo.isDone, todo.createdDate))

        conn.commit()
