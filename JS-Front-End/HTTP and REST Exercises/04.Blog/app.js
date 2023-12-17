function attachEvents() {
    const POSTS_URL = 'http://localhost:3030/jsonstore/blog/posts';
    const COMMENTS_URL = 'http://localhost:3030/jsonstore/blog/comments';
    const loadPostBtn = document.getElementById('btnLoadPosts');
    const viewPostBtn = document.getElementById('btnViewPost');
    const postsContainer = document.getElementById('posts');
    const postTitle = document.getElementById('post-title');
    const postBody = document.getElementById('post-body');
    const postComments = document.getElementById('post-comments');

    let postContainer = {};

    loadPostBtn.addEventListener('click', loadPostHandler);
    viewPostBtn.addEventListener('click', viewPostHandler);

    function viewPostHandler() {
        removeAllChildNodes(postComments);
        fetch(COMMENTS_URL)
            .then((res) => res.json())
            .then((data) => {
                let searchedPost = postsContainer.value;
                postTitle.textContent = postContainer[searchedPost].title;
                postBody.textContent = postContainer[searchedPost].body;
                for (const input in data) {
                    let currentPostId = data[input].postId;
                    if (searchedPost == currentPostId) {
                        createElement('li', postComments, data[input].text, null, data[input].id);
                    }
                }
            })
    }

    function loadPostHandler() {
        removeAllChildNodes(postsContainer);
        fetch(POSTS_URL)
            .then((res) => res.json())
            .then((data) => {
                for (const input in data) {
                    createElement('option', postsContainer, data[input].title, null, null, { 'value': input })
                    postContainer[input] = data[input];
                }
            })
            .catch((err) => console.error(err));
    }

    function removeAllChildNodes(parent) {
        while (parent.firstChild) {
            parent.removeChild(parent.firstChild);
        }
    }

    function createElement(type, parentNode, content, classes, id, attributes, useInnerHtml) {
        const htmlElement = document.createElement(type);

        if (content && useInnerHtml) {
            htmlElement.innerHTML = content;
        } else if (content && type !== 'input') {
            htmlElement.textContent = content;
        } else if (content && type === 'input') {
            htmlElement.value = content;
        }

        // [ ]
        if (classes && classes.length > 0) {
            htmlElement.classList.add(...classes);
        }

        if (id) {
            htmlElement.id = id;
        }

        // {src: 'link', href: 'http'}
        if (attributes) {
            for (let key in attributes) {
                htmlElement[key] = attributes[key];
            }
        }

        if (parentNode) {
            parentNode.appendChild(htmlElement);
        }

        return htmlElement;
    }
}

attachEvents();