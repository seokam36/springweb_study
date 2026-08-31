console.log( "INDEX.JS 열림" );

// [1] 전체 조회 , function 함수명( 매개변수명 ){ }
async function boardFindAll() {
    console.log('boardFindAll 열림');
    // 1. 어디에 : html table 본문에 , 식별
    let tbody = document.querySelector('.boardList'); // document(HTML문서).query(질의)Selector

    // 2. 무엇을 (HTTP 통신(AXIOS) 이용한 백엔드에게 요청)
    // ** await axios.HTTP메소드( "HTTP 주소" ) , 함수명 앞에 async 추가 **
    // 동기화 하는 이유 : 해당 통신 이후에 아래 코드 실행
    // 비동기화( 요청 후 응답 대기X ), 동기화( 요청 후 응답 대기 )
    let html = "";
    const response = await axios.get("http://localhost:8080/board/findAll")
    console.log(response)
    const responseList = response.data;
    console.log(responseList)
    for (let i = 0; i < responseList.length; i++) {
        const responseObj = responseList[i];
        html += `<tr>
                    <td> ${responseObj.no} </td>
                    <td> ${responseObj.writer} </td>
                    <td> ${responseObj.content} </td>
                    <td>
                        <button onclick="boardUpdate(${responseObj.no})">수정</button>
                        <button onclick="boardDelete(${responseObj.no})">삭제</button>
                    </td>
                </tr>` // 백틱 ${}
    }

    // 3. 출력
    tbody.innerHTML = html;
}
boardFindAll() // HTML 열릴 때 최초 1번 실행

// [2] 등록
async function boardSave(){
    // 1. 입력받은 값 가져오기
    const content = document.querySelector('.content').value; // value : 입력상자에 입력된 값 반환
    const writer = document.querySelector('.writer').value;

    // 2. 저장
    const response = await axios.post(`/board/save?content=${content}&writer=${writer}`);

    // 3. 출력
    if (response.data == true){
        alert('저장 성공');
        boardFindAll();
    } else {
        alert('저장 실패');
    }
}

// [3] 수정
async function boardUpdate(no){
    // 1. 수정할 내용 입력
    const content = prompt("수정할 내용 : ");

    // 2. 수정처리
    const response = await axios.put(`/board/update?no=${no}&content=${content}`);

    // 3. 결과
    if (response.data == true){
        alert('수정 성공');
        boardFindAll();
    } else {
        alert('수정 실패');
    }
}

// [4] 삭제
async function boardDelete(no){
    const response = await axios.delete(`/board/delete?no=${no}`);
    if (response.data==true){
        alert('삭제 성공');
        boardFindAll();
    } else {
        alert('삭제 실패')
    }
}