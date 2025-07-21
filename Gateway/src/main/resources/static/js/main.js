// 사용자 로그인을 처리하는 비동기 함수
async function login() {
    try {
        console.log("userId element: ", document.getElementById('userId')); // ✅ 추가
        console.log("password element: ", document.getElementById('password'));
        // 📌 HTML의 input 요소에서 username과 password 값을 가져옴
        const response = await axios.post('/api/account/login', {
            // 'username' 입력창의 값을 가져와 요청에 포함
            userId: document.getElementById('userId').value,
            // 'password' 입력창의 값을 가져와 요청에 포함
            password: document.getElementById('password').value
        });
        const nickNameResponse = await axios.get('/api/account/my-profile', {
            headers: {
                Authorization: `Bearer ${response.data.userToken}`
            }
        });
        // 📌 서버로부터 받은 응답 객체에서 토큰 값을 추출
        // 일반적으로 백엔드는 로그인 성공 시 JWT 토큰을 반환함
        localStorage.setItem("userToken", response.data.userToken);
        localStorage.setItem("nickName", nickNameResponse.data.nickName);
        // 로그인 성공 후 다른 페이지로 이동
        window.location.href = "/pages/dashboard.html"; // 새 HTML 페이지로 이동

    } catch (error) {
        alert("로그인 실패: " + (error.response?.data?.message || error.message));
    }
}


// 회원가입 요청을 처리하는 비동기 함수
async function register() {
    try {
        // 📌 HTML 입력창에서 값을 읽어와 객체로 구성 후 서버에 POST 요청 보냄
        // axios.post(URL, data)는 비동기 요청이므로 await로 응답을 기다림
        await axios.post('/api/account/register', {
            // id가 'register-userId'인 input 요소의 값을 가져옴 → 아이디
            userId: document.getElementById('register-userId').value,
            // 비밀번호 input의 값
            password: document.getElementById('register-password').value,
            // 닉네임 input의 값
            nickName: document.getElementById('register-nickName').value
        });

        // 📌 서버 응답이 정상적일 경우 alert으로 성공 메시지를 출력
        alert("회원가입 성공! 로그인 해주세요.");

    } catch (error) {
        // 📌 서버 요청 중 오류 발생 시 catch 블록 실행

        // error.response?.data?.message
        // => 서버에서 응답이 왔지만 에러 메시지가 포함되어 있을 경우
        // error.message
        // => 그 외 네트워크 오류 등 기본 JS 에러 메시지

        alert("회원가입 실패: " + (error.response?.data?.message || error.message));
    }
}
// async function recommend() {
//     try {
//         const keyword = document.getElementById('keyword').value;
//         const response = await axios.post(
//             '/recommend/list/keyword',
//             { keyword },
//             {
//                 headers: {
//                     Authorization: 'Bearer ' + token
//                 }
//             }
//         );
//
//         const resultList = document.getElementById('result-list');
//         resultList.innerHTML = '';
//         response.data.forEach(item => {
//             const li = document.createElement('li');
//             li.innerText = item.title || '제목 없음';
//             resultList.appendChild(li);
//         });
//     } catch (error) {
//         alert("추천 요청 실패: " + (error.response?.data?.message || error.message));
//     }
// }
function toggleRegister() {
    const form = document.getElementById('register-form');
    form.classList.toggle('expanded');
}