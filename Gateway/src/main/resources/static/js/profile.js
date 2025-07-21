document.addEventListener("DOMContentLoaded", () => {
    // ✅ 닉네임 표시
    const nickName = localStorage.getItem("nickName");
    document.getElementById("edit-nickname").value = nickName || "";
});

// ✅ 섹션 전환 함수
function showSection(type) {
    const edit = document.getElementById("edit");
    const del = document.getElementById("delete");

    if (type === 'edit') {
        edit.classList.remove("hidden");
        del.classList.add("hidden");
    } else {
        edit.classList.add("hidden");
        del.classList.remove("hidden");
    }
}

// ✅ 회원정보 수정
document.getElementById("profile-form").addEventListener("submit", async (e) => {
    e.preventDefault();

    const token = localStorage.getItem("userToken");
    const newNick = document.getElementById("edit-nickname").value;
    const currentPw = document.getElementById("edit-current-password").value;
    const newPw = document.getElementById("edit-new-password").value;

    try {
        const res = await axios.post("/api/account/update", {
            newNickName: newNick,
            currentPassword: currentPw,
            newPassword: newPw
        }, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        alert(res.data.message || "✅ 정보 수정 완료!");
        localStorage.setItem("nickName", newNick);
        document.getElementById("profile-form").reset();
        goToDashboard();

    } catch (err) {
        alert("❌ 수정 실패: " + (err.response?.data?.message || err.message));
    }
});

// ✅ 회원탈퇴
document.getElementById("delete-form").addEventListener("submit", async (e) => {
    e.preventDefault();

    const pw = document.getElementById("delete-password").value;
    const token = localStorage.getItem("userToken");

    if (!confirm("정말 탈퇴하시겠습니까? 😢")) return;

    try {
        const res = await axios.post("/api/account/delete", { password: pw }, {
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        alert(res.data.message || "😢 탈퇴가 완료되었습니다.");
        localStorage.clear();
        window.location.href = "/index.html";

    } catch (err) {
        document.getElementById("delete-result").textContent =
            "❌ 탈퇴 실패: " + (err.response?.data?.message || err.message);
    }
});

// ✅ 로그아웃
function logout() {
    localStorage.clear();
    window.location.href = "/index.html";
}

// ✅ 대시보드 이동
function goToDashboard() {
    window.location.href = "/pages/dashboard.html";
}
