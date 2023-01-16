
document.querySelector('#add-btn').onclick = (e) => {
    var name = encodeURIComponent(document.querySelector('#f-name').value);
    var tel = document.querySelector('#f-tel').value;
    var id = document.querySelector('#f-id').value;
    var password = encodeURIComponent(document.querySelector('#f-password').value);
    var nickName = encodeURIComponent(document.querySelector('#f-nickName').value);

    fetch('http://localhost:8080/members', {
        method: 'POST',
        headers: {
            'Content-type': 'application/x-www-form-urlencoded'
        },
        body: `name=${name}&tel=${tel}&id=${id}&password=${password}&nickName=${nickName}`
        })
        .then((response) => {return response.json();})
        .then((obj) => {
        location.href = "index2.html";
        })
        .catch((err) => {
        alert("서버 요청 오류!");
        console.log(err);
        });

    };

document.querySelector('#cancel-btn').onclick = (e) => {
    location.href = "index.html";
};