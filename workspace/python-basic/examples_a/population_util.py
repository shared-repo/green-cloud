def select_population_by_dong2(dong_name):
    import csv
    data = [] # 리스트 만들기
    with open("data-files/population.csv", encoding="utf-8") as f:
        reader = csv.reader(f) # csv 처리 : ,로 분리하고 "와 같은 데이터 구분 표식 제거하고 리스트로 데이터 반환

        next(reader) # 한 행 읽기 ( 첫 번째 행 skip )
        for _, line in enumerate(reader):
            if dong_name in line[0]:
                # row = []
                # for d in line[3:104]:
                #     row.append(int(d.replace(",", "")))
                
                row = [ int(d.replace(",", "")) for d in line[3:104] ]
                
                data.append(row) 

    return data      