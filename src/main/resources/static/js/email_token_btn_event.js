document.getElementById('btn_emailToken').addEventListener('click', function () {
      const messageDisplay = document.getElementById('emailSendError');
      const formData = new FormData();
      formData.append('username', document.getElementById('email').value);
      formData.append('_csrf', document.querySelector('input[name="_csrf"]').value);

      fetch('/email-token', {
                method: 'POST',
                body: formData
            })
          .then(function (response) {
              if (!response.ok)
                messageDisplay.textContent = '오류가 발생했습니다.';

              return response.json();
           })
          .then(function (data) {
              messageDisplay.textContent = data.msg;
          })
          .catch(function (error) {
              messageDisplay.textContent = '오류가 발생했습니다.'; // 에러 처리
          });
  });