# 🌐 MSA Project (Team PlaceData)

## 📌 프로젝트 개요

본 프로젝트는 **MSA (Microservice Architecture)** 백엔드 시스템과 연동되는 정적 웹 기반 **프론트엔드 인터페이스**입니다.  
Tomcat에서 서빙되며, 로그인, 회원가입, 여행지 추천, 리뷰 관리 등 다양한 기능을 제공합니다.

---

## 📂 폴더 구조

```
📁 static/
├── css/                  # 공통 스타일
│   └── style.css
├── js/                   # JS 기능 모듈
│   ├── main.js           # 로그인/회원가입
│   ├── dashboard.js      # 대시보드 기능
│   └── util.js           # 토큰 처리 유틸
├── index.html            # 로그인 진입 페이지
├── profile.html          # 로그인 후 사용자정보 수정
└── dashboard.html        # 로그인 후 대시보드
```

---

## 🖥️ 주요 화면

## 1. 🔐 로그인 / 회원가입 (`index.html`)

📸 예시 화면:

![로그인 페이지](https://github.com/user-attachments/assets/4e356665-0e64-4953-817a-3d6d6c68109b)

---

### 2. 📜 사용자 정보 수정 (`profile.html`)

📸 예시 화면:

![사용자 정보 수정 1](https://github.com/user-attachments/assets/3de84db0-c8b1-4f2b-a381-a908b5687401)
![사용자 정보 수정 2](https://github.com/user-attachments/assets/633804fc-97e3-4717-8509-d20de59d0485)

---

### 3. 🧾 사용자 대시보드 (`dashboard.html`)

📸 예시 화면:

![대시보드 1](https://github.com/user-attachments/assets/d4b283d3-1cc7-4d2c-89f1-e2e75c107580)
![대시보드 2](https://github.com/user-attachments/assets/2ad762ca-ab43-4e58-bf6e-a15085f61d62)
![대시보드 3](https://github.com/user-attachments/assets/67e46619-95e4-4270-995b-84fb7af39eef)

## 🔐 인증 흐름

1. 로그인 → redis -> userToken 발급
2. localStorage에 `userToken` 저장
3. 이후 모든 요청 시 `Authorization: Bearer {token}` 헤더 전송
4. 백엔드 MSA API와 연동

```javascript
const token = localStorage.getItem("userToken");
axios.get("/api/account/info", {
  headers: { Authorization: `Bearer ${token}` }
});
```

---

## 🔗 주요 API 연동 (MSA 서비스)

| 기능               | API Endpoint (예시)            |
|--------------------|--------------------------------|
| 로그인             | `/account/login`              |
| 회원가입           | `/account/register`           |
| 장소 등록/조회     | `/place/register`, `/place/list` |
| 추천 조회          | `/recommend/list/keyword`     |
| 회원정보 수정       | `/account/update`             |
| 회원정보 탈퇴       | `/account/delete`             |

---

## ⚙️ 시스템 구성

- **서버**: Tomcat 9+
- **프론트 기술 스택**: HTML, CSS, JavaScript, Axios
- **인증 방식**: OAuth 기반 AccessToken 활용
- **배포 방식**: `static` 디렉터리를 WAR 형식으로 Tomcat에 배포하거나 `/webapps` 하위에 위치

---

## 🛠️ 개발 및 빌드 참고

- 로컬 테스트: `http://localhost:8080/index.html`
- JS → Axios 통해 백엔드 MSA 서비스 호출
- Gateway 및 Eureka 연동을 통해 MSA 각 서비스로 라우팅됨

---

## 📝 기타

- 모든 페이지는 RESTful API 기반이며, Backend 서비스는 각각 Account, Place, Recommend, Review 등으로 분리되어 있음.
- 해당 프론트는 Gateway를 통해 API 요청을 전달하며, 별도 인증/보안 처리는 백엔드에서 수행.

---

