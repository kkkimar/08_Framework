/* 책 목록 조회 */

//조회버튼
const bookListBtn = document.querySelector("#bookListBtn");
//tbody
const tbody = document.querySelector("#tbody");

//td 함수
const createTd = (text)=>{
  const td = document.createElement("td");
  td.innerText = text;
  return td;
}

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