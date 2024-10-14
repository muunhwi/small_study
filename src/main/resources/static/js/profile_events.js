    // 이미지 변경 이벤트
document.getElementById('profileImage').addEventListener('change', function (event) {
            const file = event.target.files[0]; // 선택된 파일 가져오기
            if (file) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    // 이미지 미리보기 업데이트
                    document.getElementById('profileImagePreview').src = e.target.result;
                };
                reader.readAsDataURL(file); // 파일을 Data URL로 읽기
            }
     });


function confirmAndSubmit(event) {
     let result = confirm("프로필 변경 사항을 적용하시겠습니까?");
     if (result)
         return true;
     else {
         event.preventDefault();
         return false;
     }
}