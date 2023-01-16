const star1 = document.querySelector('#star-btn1');
const star2 = document.querySelector('#star-btn2');
const star3 = document.querySelector('#star-btn3');
const star4 = document.querySelector('#star-btn4');
const star5 = document.querySelector('#star-btn5');

(function() {

  starAction();

  review();

  reivews();

})();

function review() {
  document.querySelector('#add-btn').onclick = (e) => {
    var content = encodeURIComponent(document.querySelector('#f-content').value);
    var password = document.querySelector('#f-password').value;
  
    fetch('http://localhost:8080/reviews', {
      method: 'POST',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded'
      },
      body: `content=${content}&password=${password}`
    })
    .then((response) => {return response.json();})
    .then((obj) => {
      location.reload();
    })
    .catch((err) => {
      alert("서버 요청 오류!");
      console.log(err);
    });
  };
}

function reivews() {
  fetch('http://localhost:8080/reviews')
  .then((response) => {return response.json();})
  .then((obj) => {
    var html = '';
    for (var r of obj.data) {
      html += `<tr>
        <td>${r.no}</td>
        <td>${r.createdDate}</td>
        <td>${r.content}</td>
        <td><button>변경</button></td>
        <td><button onclick='remove()' id='delete-btn'>삭제</button></td>
        </tr>\n`;
    }

    document.querySelector('tbody').innerHTML = html;
  })
  .catch((err) => {
    alert("서버 요청 오류!");
    console.log(err);
  }); 
}

function remove() {
    var password = prompt('암호를 입력하세요!');

    document.querySelector('tbody tr').remove();
    // fetch(`http://localhost:8080/reviews/${no}`, {
    //   method: 'DELETE',
    //   headers: {
    //     'Content-type': 'application/x-www-form-urlencoded'
    //   },
    //   body: `password=${password}`
    // })
    // .then((response) => response.json())
    // .then((obj) => {
    //   if (obj.status == "failure") {
    //     alert("게시글 삭제 오류!\n" + obj.data);
    //     return;
    //   }
  
    //   location.reload();
    // })
    // .catch((err) => {
    //   alert("서버 요청 오류!");
    //   console.log(err);
    // });
}

function starAction() {

  star1.addEventListener('click', function() {

    if (star1.className == 'on') {
      star2.classList.remove('on');
      star2.style.color = "lightgray";
      star3.classList.remove('on');
      star3.style.color = "lightgray";
      star4.classList.remove('on');
      star4.style.color = "lightgray";
      star5.classList.remove('on');
      star5.style.color = "lightgray";
    } else {
      star1.classList.add('on');
      star1.style.color = "black";
    }
  });

  star2.addEventListener('click', function() {

    if (star2.className == 'on') {
      star3.classList.remove('on');
      star3.style.color = "lightgray";
      star4.classList.remove('on');
      star4.style.color = "lightgray";
      star5.classList.remove('on');
      star5.style.color = "lightgray";
    } else {
      star1.classList.add('on')
      star2.classList.add('on');
      star1.style.color = "black";
      star2.style.color = "black";
    }
  });

  star3.addEventListener('click', function() {

    if (star3.className == 'on') {
      star4.classList.remove('on');
      star4.style.color = "lightgray";
      star5.classList.remove('on');
      star5.style.color = "lightgray";
    } else {
      star1.classList.add('on')
      star1.style.color = "black";
      star2.classList.add('on');
      star2.style.color = "black";
      star3.classList.add('on');
      star3.style.color = "black";
    }
  });

  star4.addEventListener('click', function() {

    if (star4.className == 'on') {
      star5.classList.remove('on');
      star5.style.color = "lightgray";
    } else {
      star1.classList.add('on')
      star1.style.color = "black";
      star2.classList.add('on');
      star2.style.color = "black";
      star3.classList.add('on');
      star3.style.color = "black";
      star4.classList.add('on');
      star4.style.color = "black";
    }
  });

  star5.addEventListener('click', function() {

    if (star5.className == 'on') {
      star5.classList.remove('on');
      star5.style.color = "lightgray";
    } else {
      star1.classList.add('on')
      star1.style.color = "black";
      star2.classList.add('on');
      star2.style.color = "black";
      star3.classList.add('on');
      star3.style.color = "black";
      star4.classList.add('on');
      star4.style.color = "black";
      star5.classList.add('on');
      star5.style.color = "black";
    }
  });

}