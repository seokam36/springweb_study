// 1. 전체리스트 조회
async function findAllList(){
    let html = "";
    let tbody = document.querySelector(".waitList")
    const response = await axios.get("/waitList/findAllList")
    const responseList = response.data
    for (let i = 0; i < responseList.length; i++) {
        let responseObj = responseList[i];
        html += `<tr>
                    <td>${responseObj.num}</td>
                    <td>${responseObj.telNum}</td>
                    <td>${responseObj.people}</td>
                    <td>
                        <button onclick="listUpdate('${responseObj.telNum}')">수정</button>
                        <button onclick="listDelete('${responseObj.telNum}')">삭제</button>
                    </td>
                </tr>`
    }

    tbody.innerHTML = html;
}
findAllList()

// 2. 등록
async function save(){
    let telNum = document.querySelector(".telNum").value;
    let people = document.querySelector(".people").value;

    const response = await axios.post(`/waitList/save?telNum=${telNum}&people=${people}`);
    if (response.data == true){
        alert("등록 성공");
        findAllList();
    } else {
        alert("등록 실패")
    }
}

// 3. 수정
async function listUpdate(telNum){
    let people = prompt("수정할 인원 수 입력 : ")

    const response = await axios.put(`/waitList/update?telNum=${telNum}&people=${people}`);
    if (response.data == true){
        alert('수정 완료');
        findAllList();
    } else {
        alert('수정 실패')
    }
}

// 4. 삭제
async function listDelete(telNum){
    const response = await axios.delete(`/waitList/delete?telNum=${telNum}`)

    if (response.data == true){
        alert('삭제 완료');
        findAllList();
    } else {
        alert('삭제 실패');
    }
}