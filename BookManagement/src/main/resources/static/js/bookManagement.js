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
const serchBookTbody = document.querySelector("#serchBookTbody");

if(bookTitleSearch != null){

  searchBookBtn.addEventListener("click",()=>{

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
      serchBookTbody.innerText = "";

      for(let book of bookSearchList){
        //console.log(book);
        const thead = document.querySelector("#thead");
        const th = thead.querySelectorAll("th");
        const tr = document.createElement("tr");
        
        for(let i=0 ;i<th.length;i++){
          



        }

      }
      
    })
  });
};


  

//td 함수
const createTd = (text)=>{
  const td = document.createElement("td");
  td.innerText = text;
  return td;
}