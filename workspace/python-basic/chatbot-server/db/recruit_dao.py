import pymysql

conn = pymysql.connect(
    # host="localhost",
    host="192.168.0.14",
    port=3306,
    user="green_cloud",
    password="mysql",
    database='recruitdb'
)

def remove_all_recruit():
    try:
        with conn.cursor() as cursor:
            sql = "DELETE FROM tbl_recruit"
            cursor.execute(sql)
        
    except Exception as e:
        print(e)
    finally:
        pass

def insert_all_recruit(recruit_list):
    # print(len(recruit_list), len(recruit_list[0]))
    try:
        with conn.cursor() as cursor:
            sql = """   INSERT INTO tbl_recruit (
                                                    company_name, title, etc, detail_info, 
                                                    experience, education, skill, core_competencies, preference, 
                                                    basic_preference, employment_type, salary, region 
                                                )
                        VALUES  ( %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s )"""
            cursor.executemany(sql, recruit_list)
            
        conn.commit()

    except Exception as e:
        conn.rollback()
        print(e)
    finally:
        pass

def load_all_recruits():
    
    try:
        with conn.cursor() as cursor:
            sql = """   SELECT  company_name, title, etc, detail_info, 
                                experience, education, skill, core_competencies, preference, 
                                basic_preference, employment_type, salary, region 
                        FROM tbl_recruit"""
            cursor.execute(sql)

            all_recruits = cursor.fetchall()

    except Exception as e:
        print(e)
    finally:
        pass    

    return all_recruits