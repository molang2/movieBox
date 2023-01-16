

document.querySelector('#login-btn').addEventListener('click', function() {
    var id = encodeURIComponent(document.querySelector('#f-id').value);
    var password = document.querySelector('#f-passowrd');

    fetch(`http://localhost:8080/members/${id}&${password}`, {
        method: 'POST',
        headers: {
        'Content-type': 'application/x-www-form-urlencoded'
        },
        body: `id=${id}&password=${password}`
    })
    .then((response) => response.json())
    .then((obj) => {
        if (obj.status == "failure") {
        alert("아이디가 없거나 비밀번호가 다릅니다!\n" + obj.data);
        return;
        }
        location.href = "index2.html";
    })
    .catch((err) => {
        alert("서버 요청 오류!");
        console.log(err);
    });
});
