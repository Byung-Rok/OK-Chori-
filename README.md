# 1. 디렉터리 관련
    
##### 1.1. 디렉터리는 아래와 같은 구조로 관리하겠습니다.
```
Ex)
    Root ------ 20190806 ------ 모든 순열     ------ BOJ_10974_임진균_X.java
            |                |- 차이를 최대로     |- BOJ_10974_박정호_O.java
            |                |- [.....]           |- [......]
            |     
            |
            |-- 20190808 ------ N과 M(1)
            |                |- N과 M(2)
            |                |- [......]
            |
            |-- [......]
```
  


# 2. 파일명 관련

##### 2.1. 가독성을 위해서 파일명 관련 규칙을 정하겠습니다. 업로드 하실 때, 아래 규칙을 반드시 준수하시기 바랍니다.

##### 2.2. 사이트명은 BOJ(백준), SWEA(삼성 SW 아카데미), AS(알고스팟), JO(정올) 키워드 중 하나를 기입합니다. 
- 키워드는 반드시 대문자로 작성할 것
    
##### 2.3. 성공 여부는 O나 X 중 하나를 기입합니다. (대문자로 작성할 것)
- 테스트케이스의 일부만 통과한 경우는 그냥 틀린 것이므로 O로 표기하지 않습니다.
- 또한, 이클립스에서만 실행되는 경우도 틀린 것이므로 O로 표기하지 않습니다.

```
Ex)
    [사이트명]_[문제번호]_[이름]_[성공 여부].[확장자]
    BOJ_10974_임진균_X.java
    SWEA_1000_임진균_O.cpp
```



# 3. *.java 파일 내 클래스 및 패키지 설정 관련

##### 3.1. GitLab의 소스를 이클립스에 복사한 뒤에 클래스 명이나 패키지 관련 설정 오류 때문에 쓸데없이 시간낭비하는 것이 싫어서 다음과 같은 규칙을 정하겠습니다.
      
##### 3.2. 별도의 패키지는 사용하지 않습니다. 즉, 그냥 default package에서 작업하시기 바랍니다.
    
##### 3.3. BOJ는 클래스명을 Main으로, SWEA는 클래스명을 Solution으로 작성하시기 바랍니다.
    
    
    
# 4. Commit 규칙

##### 4.1. Commit 내용은 자유롭게 하되 어떤 작업을 했는지 간단 명료하게 기술하시기 바랍니다.

##### 4.2. 새 파일을 추가할 경우.
```
Ex)
    [추가] N과M(8)_15657
```

##### 4.3. 올린 파일을 수정할 경우.
```
Ex)
    [수정] java 컨벤션 적용
```
