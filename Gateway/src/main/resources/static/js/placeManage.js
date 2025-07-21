function showPlaceForm(type) {
    const container = document.getElementById("place-manage-content");
    let html = "";

    switch (type) {
        case "register":
            html = `
        <h3>📌 여행지 등록</h3>
        <input id="title" placeholder="여행지명"><br>
        <textarea id="content" placeholder="설명"></textarea><br>
        <select id="category">
          <option value="">카테고리 선택</option>
          <option value="CULTURE">CULTURE</option>
          <option value="ACTIVITY">ACTIVITY</option>
          <option value="NATURE">NATURE</option>
          <option value="SHOPPING">SHOPPING</option>
          <option value="TRANSPORT">TRANSPORT</option>
        </select><br>
        <select id="location">
          <option value="">지역 선택</option>
          <option value="서울특별시">서울특별시</option>
          <option value="경기도">경기도</option>
          <option value="강원특별자치도">강원특별자치도</option>
          <option value="부산광역시">부산광역시</option>
          <!-- 더 추가 가능 -->
        </select><br>
        <input id="address" placeholder="주소"><br>
        <button onclick="registerPlace()">등록</button>
      `;
            break;

        case "list":
            getAllPlaces();
            html = `<h3>📋 전체 여행지 목록</h3><div id="place-list-box">로딩 중...</div>`;
            break;

        case "getById":
            html = `
        <h3>🔎 ID로 조회</h3>
        <input id="place-id" placeholder="place_id">
        <button onclick="getPlaceById()">조회</button>
        <div id="place-result"></div>
      `;
            break;

        case "search":
            html = `
        <h3>🔍 조건 검색</h3>
        <input id="title" placeholder="제목">
        <select id="category">
          <option value="">카테고리 선택</option>
          <option value="CULTURE">CULTURE</option>
          <option value="ACTIVITY">ACTIVITY</option>
          <option value="NATURE">NATURE</option>
          <option value="SHOPPING">SHOPPING</option>
          <option value="TRANSPORT">TRANSPORT</option>
        </select>
        <select id="location">
          <option value="">지역 선택</option>
          <option value="서울특별시">서울특별시</option>
          <option value="경기도">경기도</option>
        </select>
        <button onclick="searchPlace()">검색</button>
        <div id="search-result"></div>
      `;
            break;

        case "update":
            html = `
        <h3>✏️ 여행지 수정</h3>
        <input id="place-id" placeholder="place_id"><br>
        <input id="title" placeholder="새 제목"><br>
        <textarea id="content" placeholder="새 설명"></textarea><br>
        <select id="category">
          <option value="CULTURE">CULTURE</option>
          <option value="ACTIVITY">ACTIVITY</option>
        </select><br>
        <select id="location">
          <option value="서울특별시">서울특별시</option>
          <option value="경기도">경기도</option>
        </select><br>
        <input id="address" placeholder="새 주소"><br>
        <button onclick="updatePlace()">수정</button>
      `;
            break;

        case "delete":
            html = `
        <h3>🗑 여행지 삭제</h3>
        <input id="place-id" placeholder="place_id">
        <button onclick="deletePlace()">삭제</button>
      `;
            break;
    }

    container.innerHTML = html;
}
