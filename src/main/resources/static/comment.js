function loadComments(pictureName, container) {
    fetch(`/comments?pictureName=${pictureName}`)
        .then(response => response.json())
        .then(comments => {
            const commentsContainer = document.createElement('div');
            commentsContainer.classList.add('comment-box');
            commentsContainer.id = `comments-container-${pictureName}`;

            (Array.isArray(comments) ? comments : []).forEach(comment => {
                const commentElem = document.createElement('div');
                commentElem.classList.add('comment');
                commentElem.textContent = comment.content;  // 댓글 내용

                const deleteBtn = document.createElement('span');
                deleteBtn.classList.add('delete-btn');
                deleteBtn.textContent = "삭제";


                deleteBtn.onclick = () => deleteComment(pictureName, comment.content, commentElem);

                commentElem.appendChild(deleteBtn);
                commentsContainer.appendChild(commentElem);
            });

            container.appendChild(commentsContainer);

            const commentForm = document.createElement('div');
            commentForm.innerHTML = `
                <input type="text" placeholder="댓글을 입력하세요" id="comment-${pictureName}">
                <button onclick="addComment('${pictureName}')">추가</button>
            `;
            container.appendChild(commentForm);
        })
        .catch(error => console.error('Error loading comments:', error));
}

function addComment(pictureName) {
    const input = document.getElementById(`comment-${pictureName}`);
    const content = input.value.trim();

    if (!content) return;

    fetch(`/addComment`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ pictureName, content })
    })
        .then(response => response.json())
        .then(comment => {
            const commentsContainer = document.getElementById(`comments-container-${pictureName}`);

            const commentElem = document.createElement('div');
            commentElem.classList.add('comment');
            commentElem.textContent = comment.content;

            const deleteBtn = document.createElement('span');
            deleteBtn.classList.add('delete-btn');
            deleteBtn.textContent = "삭제";
            deleteBtn.onclick = () => deleteComment(pictureName, comment.content, commentElem);

            commentElem.appendChild(deleteBtn);
            commentsContainer.appendChild(commentElem);



            input.value = '';
        })
        .catch(error => console.error('Error adding comment:', error));
}

function deleteComment(pictureName, content, commentElem) {
    fetch(`/deleteComment?pictureName=${encodeURIComponent(pictureName)}&content=${encodeURIComponent(content)}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('Failed to delete comment');
            }
        })
        .then(() => {
            commentElem.remove();  // 댓글 요소 삭제
        })
        .catch(error => console.error('Error deleting comment:', error));
}

// Add this function for deleting a picture and its comments
function deletePhoto(pictureName, container) {
    fetch(`/deletePicture?pictureName=${encodeURIComponent(pictureName)}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                container.remove(); // Remove the photo container from the DOM
            } else {
                throw new Error('Failed to delete photo');
            }
        })
        .catch(error => console.error('Error deleting photo:', error));
}

fetch('/comment', { method: 'POST' })
    .then(response => response.json())
    .then(pictures => {
        const gallery = document.getElementById('image-gallery');
        pictures.forEach(picture => {
            const container = document.createElement('div');
            container.classList.add('picture-container');
            container.id = `picture-${picture.name}`;

            // Delete Photo button
            const deletePhotoBtn = document.createElement('button');
            deletePhotoBtn.classList.add('delete-photo-btn');
            deletePhotoBtn.textContent = '사진삭제';
            deletePhotoBtn.onclick = () => deletePhoto(picture.name, container);
            container.appendChild(deletePhotoBtn);

            const img = document.createElement('img');
            img.src = '/uploads/' + picture.name;
            img.alt = 'Uploaded Image';

            const p = document.createElement('p');
            p.textContent = `사진 게시 날짜: ${picture.uploadDate}`;

            container.appendChild(img);
            container.appendChild(p);
            gallery.appendChild(container);

            loadComments(picture.name, container);
        });
    })
    .catch(error => console.error('Error loading images:', error));