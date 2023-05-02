## **1) 회원 관리**

1. 로그인 POST
2. 로그아웃 GET
3. 회원 가입 POST
4. 회원 탈퇴 PATCH
5. 비밀번호 찾기
    1. 이메일 인증 GET
    2. 비밀번호 변경페이지 b로 이동
6. 비밀번호 변경
    1. 비밀번호 확인 POST
    2. 비밀번호 변경 PATCH
7. 관광지 좋아요 기능 POST, DELETE
8. 유저 신고 POST
9. 글 신고 POST
10. 우편함 카운트 GET

## **2) 공지**

1. 글 작성 POST
2. 글 조회 GET
3. 글 수정 PATCH
4. 글 삭제 PATCH

## **3) 관광지**

1. 존재하는 지역 조회 GET
2. 지역 내 시군구 조회 GET
3. 관광지 조회 GET
4. 좋아요한 관광지만 조회 GET

## **4) 여행 후기**

1. 글 작성 POST
2. 글 조회 GET
3. 글 수정 PATCH
4. 글 삭제 PATCH
5. 글 신고 POST
6. 글 댓글 POST
7. 댓글에 대한 답글 POST
8. 좋아요한 사람의 글만 보기 GET

## **5) 여행 계획**

1. 여행 계획 조회 GET
2. 여행 계획 작성 POST
3. 여행 계획 삭제 PATCH
4. 여행 계획 댓글 POST
5. 여행 계획 답글 POST
6. 팔로우하고 있는 사람의 글만 보기 GET

## **6) 팔로우**

1. 유저 팔로우 POST
2. 유저 언팔로우 DELETE
3. 유저 검색 GET

## 7) 관리자

1. 유저 삭제 PATCH
2. 유저 수정 PATCH
3. 신고 처리
    1. 유저 처리 POST
    2. 글 처리 POST

## 8) 우편함

1. 우편함 목록 GET
2. 우편함 삭제 DELETE
    1. 전체 삭제 DELETE
    2. 선택 삭제 DELETE