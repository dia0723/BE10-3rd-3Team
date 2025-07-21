// ✅ 공통: 토큰 가져오기
function getAuthHeader() {
    const token = localStorage.getItem("userToken");
    return { Authorization: `Bearer ${token}` };
}

// 📌 1. 전체 여행지 조회
async function getAllPlaces() {
    try {
        const res = await axios.get("/api/place/list");
        const box = document.getElementById("place-list-result");
        box.innerHTML = res.data.map(p => `
            <div class="result-box">
                <strong>${p.title}</strong><br>
                ${p.category} | ${p.location}<br>
                📍 ${p.address}<br>
                📝 ${p.content}
            </div>`).join("");
    } catch (err) {
        alert("조회 실패: " + (err.response?.data?.message || err.message));
    }
}

// 📌 2. place_id로 조회
async function getPlaceById() {
    const id = document.getElementById("detail-id").value;
    try {
        const res = await axios.get(`/api/place/${id}`);
        document.getElementById("place-detail-result").innerHTML = `
            <div class="result-box">
                <strong>${res.data.title}</strong><br>
                ${res.data.category} | ${res.data.location}<br>
                📍 ${res.data.address}<br>
                📝 ${res.data.content}
            </div>`;
    } catch (err) {
        alert("조회 실패: " + (err.response?.data?.message || err.message));
    }
}

// 📌 3. 조건 검색
async function searchPlace() {
    const body = {
        title: document.getElementById("search-title").value,
        category: document.getElementById("search-category").value,
        location: document.getElementById("search-location").value,
    };
    try {
        const res = await axios.post("/api/place/search", body);
        const out = res.data.map(p => `
            <div class="result-box">
                <strong>${p.title}</strong><br>
                ${p.category} | ${p.location}<br>
                📍 ${p.address}<br>
                📝 ${p.content}
            </div>`).join("<hr>");
        document.getElementById("place-search-result").innerHTML = out;
    } catch (err) {
        alert("검색 실패: " + (err.response?.data?.message || err.message));
    }
}

// 📌 4. 여행지 수정
async function updatePlace() {
    const body = {
        place_id: document.getElementById("update-id").value,
        title: document.getElementById("update-title").value,
        content: document.getElementById("update-content").value,
        category: document.getElementById("update-category").value,
        location: document.getElementById("update-location").value,
        address: document.getElementById("update-address").value,
    };
    try {
        const res = await axios.post("/api/place/update", body, {
            headers: getAuthHeader()
        });
        alert("수정 완료: " + res.data.title);
    } catch (err) {
        alert("수정 실패: " + (err.response?.data?.message || err.message));
    }
}

// 📌 5. 여행지 삭제
async function deletePlace() {
    const place_id = document.getElementById("delete-id").value;
    try {
        const res = await axios.delete(`/api/place/delete/${place_id}`, {
            headers: getAuthHeader()
        });
        if (typeof res.data === "string") {
            alert(res.data);
        } else {
            alert(res.data.message);
        }
    } catch (err) {
        alert("삭제 실패: " + (err.response?.data?.message || err.message));
    }
}

async function registerPlace() {
    const token = localStorage.getItem("userToken");

    const body = {
        title: document.getElementById("register-title").value,
        content: document.getElementById("register-content").value,
        category: document.getElementById("register-category").value,
        location: document.getElementById("register-location").value,
        address: document.getElementById("register-address")?.value || "", // 선택적으로 처리
    };

    try {
        const response = await axios.post("/api/place/register", body, {
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });
        alert(response.data.message || "등록 성공!");
    } catch (err) {
        alert("등록 실패: " + (err.response?.data?.message || err.message));
    }
}