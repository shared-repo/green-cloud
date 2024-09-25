from fastapi import APIRouter

import os
import pickle # python의 binary 파일 도구 (읽기/쓰기)

from db import recruit_dao, chromadb_helper

admin_router = APIRouter()


@admin_router.get("/init-db")
async def init_db():
    with open("data-files/jobkorea-webdeveloper-detail-20240707.pickle", "rb") as f:
        # print('-------------------------> file is opened')
        webdeveloper_recruit_info = pickle.load(f)

    columns = ['회사명', '제목', '기타', '상세정보', '경력', '학력', '스킬', '핵심역량', '우대', '기본우대', '고용형태', '급여', '지역']
    data = [ { k:v for k, v in row.items() if k not in ['회사링크', '상세링크'] } for row in webdeveloper_recruit_info['data'] ]
    values = []
    for row in data:
        r = []
        for col in columns:
            r.append( row[col] if col in row.keys() else "")
        values.append(r)
    
    recruit_dao.remove_all_recruit()
    recruit_dao.insert_all_recruit(values)

    return "success"

@admin_router.get("/load-all-recruits")
async def loadd_all_recruits():
    recruits = recruit_dao.load_all_recruits()
    print("------------------------>", len(recruits))
    for idx, recruit in enumerate(recruits):
        print(recruit)
        if idx == 4:
            break
    return "success"

@admin_router.get("/init-embedding-db")
async def init_embedding_db():

    recruits = recruit_dao.load_all_recruits()
    # print(recruits[:5])

    columns = ( "company_name", "title", "etc", "detail_info", 
                "experience", "education", "skill", "core_competencies", "preference", 
                "basic_preference", "employment_type", "salary", "region" )

    # for idx, row in enumerate(recruits[:10]):
    for idx, row in enumerate(recruits):
        doc = ( "[{0}:{1}]".format(c, v) for c, v in zip(columns, row) ) # vectordb에 저장될 text 만들기 
        chromadb_helper.store_document("  ".join(doc))
        if idx > 1 and idx % 10 == 0:
            print("--------------> {0} data is saved".format(idx + 1))

    return { "metadata": "all recruits", "data": recruits[:5] }