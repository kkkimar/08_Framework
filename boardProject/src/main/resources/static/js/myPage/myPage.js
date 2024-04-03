/* 회원 정보 수정 페이지 */
const updateInfo = document.querySelector("#updateInfo");

// #updateInfo 요소가 존재 할 때만 수행
if(updateInfo !=null){

  // form 제출 시
  updateInfo.addEventListener("submit",e=>{
    
    const memberNickname = document.querySelector("#memberNickname");
    const memberTel = document.querySelector("#memberTel");
    const memberAddress = document.querySelectorAll("[name='memberAddress']");

    // 닉네임 유효성 검사
    if(memberNickname.value.trim().length == 0){
      alert("닉네임을 입력해 주세요");
      e.preventDefault(); // 제출 막기
      return;
    }

    // 정규식에 맞지 않으면
    let regExp = /^[가-힣a-zA-Z0-9]{2,10}$/;
    if(!regExp.test(memberNickname.value)){
      alert("닉네임이 유효하지 않습니다")
      e.preventDefault(); // 제출 막기
      return;
    }

    //********************************************************************** */
    // 중복 검사는 나중에 추가 예정 --------------------------
    // (테스트 시 닉네임 중복 안되게 조심하기!)
    //**********************************************************************

    // 전화번호 유효성 검사

    if(memberTel.value.trim().length == 0){
      alert("전화번호를 입력해 주세요");
      e.preventDefault(); // 제출 막기
      return;
    }
    // 정규식에 맞지 않으면
    regExp = /^01[0-9]{1}[0-9]{3,4}[0-9]{4}$/;
    
    if(!regExp.test(memberTel.value)){
      alert("전화번호가 유효하지 않습니다")
      e.preventDefault(); // 제출 막기
      return;
    }

    // 주소 유효성 검사
    // 입력을 안하면 전부 안해야되고
    // 입력하면 전부 해야한다

    const addr0 = memberAddress[0].value.trim().length == 0; // t/f
    const addr1 = memberAddress[1].value.trim().length == 0; // t/f
    const addr2 = memberAddress[2].value.trim().length == 0; // t/f

    // 모두 true인 경우만 true저장
    const result1 = addr0 && addr1 && addr2; // 아무것도 입력 X

    // 모두 false인 경우만 true 저장
    const result2 = !(addr0 || addr1 || addr2); // 모두 다 입력

    // 모두 입력 또는 모두 미입력이 아니면
    if(  !(result1 || result2)  ){
      alert("주소를 모두 작성 또는 미작성 해주세요.")
      e.preventDefault(); // 제출 막기
    }

  })


};