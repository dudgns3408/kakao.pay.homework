# kakao.pay.homework

1. 개발프레임워크 : spring boot 2.2.4
2. 문제해결 전략
- 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API(POST /api/eco/file/read)
<br>opencsv 라이브러리를 이용하여 파일을 dto 객체로 읽은 후 mapper를 통해 entity 클래스로 변환하여 저장함
    
- 생태 관광정보 데이터를 조회하는 API(GET /api/eco/program/{id})
<br>pathParameter로 받은 후 entity를 조회하여 mapper를 통해 dto 클래스로 변환하여 리턴
    
- 생태 관광정보 데이터를 추가하는 API(POST /api/eco/program)
<br>json을 dto객체로 받아 mapper를 통해 entity로 변환한 후 등록

- 생태 관광정보 데이터를 수정하는 API(PUT /api/eco/program)
<br>json을 dto객체로 받아 maaper를 통해 entity로 변환한 후 저장

- 생태 관광지 중에 서비스 지역 컬럼에서 특정 지역에서 진행되는 프로그램명과 테마를 출력하는 API(GET /api/eco/program/region/contains)
<br>지역을 queryParam으로 받은 후 entity의 컬럼을 like로 검색하여 반환

- 생태 정보 데이터에 "프로그램 소개” 컬럼에서 특정 문자열이 포함된 레코드에서 서비스 지역 개수를 세어 출력하는 API(GET /api/eco/programs/summary/contains/region/count)
<br>지역을 queryParam으로 받은 후 entity의 컬럼을 like로 검색한 후 stream으로 순회하며, 지역별 count를 계산하여 반환

- 모든 레코드에 프로그램 상세 정보를 읽어와서 입력 단어의 출현빈도수를 계산하여 출력하는 API(GET /api/eco/programs/description/contains/count)
<br>단어를 queryParam으로 받은 후 entity의 컬럼을 like로 검색한 후 stream으로 순회하며, 단어가 포함된 stream을 먼저 찾고 그 후에 StringUtils.countMatches를 통해 숫자를 세어 반환

- 생태관광 정보를 기반으로 생태관광 프로그램을 추천해주려고 합니다. 지역명과 관광 키워드를 입력받아 프로그램 코드를 출력하는 API(GET /api/eco/program/recommendation)
<br>지역과 키워드를 queryParam으로 받은 후 queryDsl을 통해 리스트 검색 후 stream으로 순회하며, 각 컬럼별 키워드 포함 숫자를 계산 한 후 각 컬럼별 가중치를 곱하여 점수를 계산 한 후 max인 프로그램의 id를 반환

빌드 및 실행 : 
- 빌드<br>maven complile 한후 HomeworkApplication run 하시면 됩니다.
- 실행<br>homeworkApiTest.http 파일을 열어서 api 실행(intellij 가 아닌경우 postman 등 사용)
첨부 파일의 경우 <br>"< /Users/martin/Downloads/sample.csv" 부분에 본인 컴퓨터의 파일 위치를 지정하여 사용
