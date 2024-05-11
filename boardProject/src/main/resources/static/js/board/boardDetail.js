/* 좋아요 버튼(하트) 클릭 시 비동기로 좋아요 INSERT/DELETE */

// Thymeleaf는 아래와 같이 이뤄져 있음
// - html 코드(+ css, js) 
// - th: 코드(java) , Spring EL

// Thymeleaf 코드 해석 순서
// 1. th: 코드(java) , Spring EL
// 2. html 코드(+ css, js) 

// 1) 로그인한 회원 번호 준비
//    --> session에서 얻어오기 (session은 서버에서 관리하기때문에 JS로 얻어 올 수 없음)

// 2) 현재 게시글 번호 준비

// 3) 좋아요 여부 준비


//--------------------------------------------------------------------------------------------------------------

//1. #boardLike가 클릭 되었을 때
const boardLike = document.querySelector("#boardLike");
boardLike.addEventListener("click",e=>{

  //2. 로그인 상태가 아닌 경우 동작이 안되도록 하기
  if(loginMemberNo == null){
    alert("로그인 후 이용해 주세요");
    return;
  }

  //3. 준비된 3개의 변수를 객체로 저장 (JSOM 변환 예정)
  const obj ={
    "memberNo" : loginMemberNo,
    "boardNo" : boardNo,
    "likeCheck" : likeCheck
  };

  //4. 좋아요 INSERT/DELETE 비동기 요청
  fetch("/board/like",{
    method : "POST",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(obj) //객체를 JSON으로 문자열화
  })
.then(resp => resp.text()) //반환 결과 test(글자)형태로 변환
.then(count=>{
  // count == 첫 번째 then의 파싱되어 반환된 값(-1 또는 게시글 좋아요 수)
  //console.log("result : ",result);

  if(count == -1){
    console.log("좋아요 처리 실패");
    return;
  }

  //5. likeCheck 값 0 <-> 1 q변환
  //  (왜? > 클릭될 때마다 INSERT/DELETE 동작을 번갈아 가면서 할 수 있음)
  likeCheck = likeCheck == 0 ? 1 : 0;

  //6. 하트를 채웠다/비웠다 바꾸기 -> 토글 이용
  e.target.classList.toggle("fa-regular");
  e.target.classList.toggle("fa-solid");

  //7. 게시글 좋아요 수 수정
  e.target.nextElementSibling.innerText = count;

  //취소한 경우에는 알림 안보내고 끝냄 
  if(likeCheck == 0) return;

  // 웹소켓을 이용해서 알림 추가
  sendNotificationFn("boardLike", location.pathname, boardNo);
});
});


//--------삭제버튼 클릭시-----------------------------------------------
const deleteBtn = document.querySelector("#deleteBtn");

if(deleteBtn != null){

  deleteBtn.addEventListener("click",()=>{

    if(!confirm("삭제하시겠습니까?")){
      alert("취소되었습니다");
      return;
    }

    //location.href = `/editBoard/${boardCode}/${boardNo}/delete?cp=${cp}`;

    //location.pathname -> hostname 뒤의 '/'문자 뒤의 경로를 가져옴
    //location.replace -> 기능: 기존페이지를 새로운 페이지로 변경
    //location.search -> '?' 뒤의 쿼리스트링을 가져옴 
    const url = location.pathname.replace("board","editBoard") + "/delete"; // /editBoard/1/2000/delete
    const queryString = location.search; // ? cp=1
    location.href = url + queryString;
  
    /* ----------- 확인 클릭 시 GET -> POST 방식으로 변경 --------*/
    // // form태그 생성
    // const form = document.querySelector("form");
    // form.action = url;
    // form.method = "POST";

    // // cp값을 저장할 input 생성
    // const input = document.querySelector("input");
    // input.type = "hidden";
    // input.name = "cp";

    // // 쿼리스트링에서 원하는 파라미터 얻어오기
    // const params = new URLSearchParams(location.search)
    // const cp = params.get("cp");
    // input.value = cp;

    // form.append(input);

    // // 화면에 form태그를 추가한 후 제출하기
    // document.querySelector("body").append(form);
    // form.submit();
  });
};

/* ----------------------게시글 수정 버튼-------------------------- */
const updateBtn = document.querySelector("#updateBtn");

if(updateBtn !=null){
  updateBtn.addEventListener("click",()=>{

    // 현재 : /board/1/2010?cp=1 
    // 목표: /editBoard/1/2010/update?cp=1  (GET 방식)
    location.href = location.pathname.replace('board','editBoard')
                    +"/update"
                    +location.search

  });
};

/* 목록으로 돌아가는 버튼 */
const goToList = document.querySelector("#goToListBtn");

goToList.addEventListener("click",()=>{

  // 상세조회 : /board/1/2011?cp=1
  // 목록: /board/1?cp=1
  let url = location.pathname;
  //url.lastIndexOf("/") : 마지막 /의 인덱스 반환
  url = url.substring(0,url.lastIndexOf("/")) 
  location.href = url + location.search; 
})

