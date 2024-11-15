// 파일 미리보기 및 업로드 기능
function loadFile(input) {
    var file = input.files[0];
    var name = document.getElementById('fileName');
    name.textContent = file.name;

    var newImage = document.createElement('img');
    newImage.setAttribute("class", 'img');
    newImage.src = URL.createObjectURL(file);

    // 이미지 미리보기
    var container = document.getElementById('image-show');
    container.innerHTML = '';  // 기존 이미지를 지우고 새 이미지를 미리보기로 설정
    container.appendChild(newImage);

    newImage.style.width = "70%";
    newImage.style.height = "70%";
    newImage.style.objectFit = "contain";
}

function submitForm() {
    // 이미지 업로드를 서버로 전달
    document.getElementById('uploadForm').submit();

}
