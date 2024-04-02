/* 쿠키에서 key가 일치하는 value 얻어오기 함수 */

// 쿠키는 "k=v; k=v;" 형식

// 배열.map(함수) : 배열의 각 요소를 이용해 함수 수행 후
// 결과 값으로 새로운 배열을 만들어 반환
const getCookie = key=>{
  const cookies = document.cookie; //"k=v; k=v" 

  // cookies 문자열을 배열 형태롤 변환
  const cookieList = cookies.split("; ") //["k=v", "k=v"]
        .map(el=>  el.split("=") ) //["k","v"]

  // 배열 -> 객체로 변환 (그래야 다루기 쉬움)
  const obj={}; //비어있는 객체 선언

  for(let i=0; i <cookieList.length; i++){
    const k = cookieList[i][0]; //key 값
    const v = cookieList[i][1]; //value 값
    obj[k] = v; /* 객체에 추가 */
  }

  //console.log("obj : ",obj);

  return obj[key]; // 매개 변수로 전달 받은 key와 obj 객체에 저장된 키가
                  // 일치하는 요소의 값 반환

};


const loginEmail = document.querySelector("#loginForm input[name='memberEmail']");


/* 로그인 안된 상태인 경우에만 수행 */
if(loginEmail != null){ //로그인창의 이메일 입력 부분이 있을 때

  // 쿠키 중 key값이 "saveId"인 요소의 value 얻어오기
  const saveId = getCookie("saveId"); // undefined 또는 이메일

  // saveId 값이 있을 경우
  if(saveId != undefined){
    loginEmail.value = saveId; //쿠키에서 얻어온 값을 input에 value로 세팅
    document.querySelector("input[name='saveId']").checked = true;
  }
};

/* 이메일, 비밀번호 미작성 시 로그인 막기 */
const loginForm = document.querySelector("#loginForm");
const loginPw = document.querySelector("#loginForm input[name='memberPw']");
// #loginForm이 화면에 존재할 때 == 로그인 상태가 아닐 경우
if(loginForm != null){
  // 제출 이벤트 발생 시
  loginForm.addEventListener("submit", e =>{

    if(loginEmail.value.trim().length === 0){
      alert("이메일을 작성해 주세요");
      e.preventDefault(); // 기본 이벤트(제출) 막기
      loginEmail.focus(); // 초점 이동
      return;
    };

    //비밀번호 미작성
    if(loginPw.value.trim().length === 0){
      alert("비밀번호를 작성해 주세요");
      e.preventDefault(); // 기본 이벤트(제출) 막기
      loginPw.focus(); // 초점 이동
      return;
    };
  });
}

/* 빠른 로그인 */
const quickLoginBtns = document.querySelectorAll(".quickLogin");

quickLoginBtns.forEach((item,index)=>{
  // item : 현재 반복 시 꺼내온 객체
  // index : 현재 반복 중인 인덱스

  // 배열엔 이벤트 추가 못함 향상된 for문으로 요소를 하나씩 꺼내
  // 이벤트 리스너 추가
  item.addEventListener("click",e=>{

  const email = item.innerText; // 버튼에 작성된 이메일 얻어오기

  location.href = "/member/quickLogin?memberEmail="+email;

  });
});

/* 회원 목록 조회(비동기) */

//조회 버튼
const selectMemberList = document.querySelector("#selectMemberList")

// tbody
const memberList = document.querySelector("#memberList")

// 조회 버튼 클릭시 
selectMemberList.addEventListener("click",()=>{

  // 1) 비동기로 회원 목록 조회
  // (포함될 회원 정보 : 회원번호, 이메일, 닉네임, 탈퇴여부)
  // 첫번째 then(response=> response.json())
  //  JSON Array-> JS 객체 배열로 반환 [{},{},{},{}]
  fetch("/member/memberList")
  .then(resp=>resp.text())
  .then(result => {
    const memberAllList = JSON.parse(result);
    
    memberList.innerText ="";

   for(let member of memberAllList ){
    



   }


    





  })


  // 2) 두번째 then
  // tbody에 이미 작성되어 있던 내용(이전에 조회한 목록) 삭제
  
  // 3) 두번째 then
  // 조회된 JS 객체 배열을 이용해
  // tbody에 들어갈 요소를 만들고 값 세팅 후 추가

});


