/* 책 목록 조회 */

//조회버튼
const bookListBtn = document.querySelector("#bookListBtn");
//tbody
const tbody = document.querySelector("#tbody");
// 모든 책 조회 테이블
const bookListTable = document.querySelector("#bookListTable");

if(bookListTable !=null){

  bookListBtn.addEventListener("click",()=>{

    fetch("/book/bookList")
    .then(resp=>resp.text())
    .then(result=>{
      const bookAllList = JSON.parse(result);
  
      //console.log(bookAllList);
      tbody.innerText ="";
  
      for(let book of bookAllList){
  
        const tr = document.createElement("tr");
        const arr = ['bookNo','bookTitle','bookWriter','bookPrice','regDate'];
  
        for(let key of arr){
          tr.append(createTd(book[key]));
        }
        tbody.append(tr);
      }
    })
  });

  
}



/* 수정/삭제/비동기 조회 */

const searchBookBtn = document.querySelector("#searchBookBtn");
const inputTitle = document.querySelector("#inputTitle");
const bookTitleSearch = document.querySelector("#bookTitleSearch");
const searchBookTbody = document.querySelector("#searchBookTbody");
const btnMod = document.querySelectorAll(".btnMod");
const btnDel = document.querySelector(".btnDel");

if(bookTitleSearch != null){

  searchBookBtn.addEventListener("click",()=>{
    //fnSearch();

    const param = {
      "bookTitle" : inputTitle.value
    }
    fetch("/bookSearch/bookTitleSearch",{
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify(param)
    })
    .then(resp=>resp.text())
    .then(result=>{
      const bookSearchList = JSON.parse(result);
      //console.log(bookSearchList);
      searchBookTbody.innerText = "";

      for(let book of bookSearchList){
        //console.log(book.bookNo);
        const thead = document.querySelector("#thead");
        //const th = thead.querySelectorAll("th");
        const tr = document.createElement("tr");

        const arr = ['bookNo','bookTitle','bookWriter','bookPrice','regDate'];
        for(let i = 0; i < arr.length; i++) {
          tr.append(createTd(book[arr[i]]));
        }
        tr.append(createBtn(book["bookNo"], "btnMod"));
        tr.append(createBtn(book["bookNo"], "btnDel"));
        searchBookTbody.append(tr);
      }

      fnMod();
    })

  });
};

function fnMod()
{
   const btnMod = document.querySelectorAll(".btnMod");
    btnMod.forEach(button => {
      //console.log(">>>>>>>>>>>> "+button)
      let key = button.name; // 글번호
      button.addEventListener('click', ()=>{
        console.log("key >>>>>>>>>>>> "+ key);
        // 수정
        //fnMod()

      }, false);
  });
}
  

//td 함수
const createTd = (text)=>{
  const td = document.createElement("td");
  td.innerText = text;
  return td;
}

//수정 삭제 td 함수
const createBtn = (text, btnClass)=>{
  const td = document.createElement("td");
  let val = "";
  if (btnClass == "btnMod") {
    val = "수정";
  } else {
    val = "삭제";
  }

  td.innerHTML = "<input type = 'button' value='"+ val +"' class='"+ btnClass +"' name='"+ text +"'/ >";
  return td;
}