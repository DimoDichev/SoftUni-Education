window.addEventListener('load', solve);

function solve() {
    const addBtn = document.getElementById('add-btn');
    const hitsContainer = document.querySelector('#all-hits > div');
    const savedContainer = document.querySelector('#saved-hits > div');
    const totalLikeContainer = document.querySelector('#total-likes > div > p');
    const inputFields = {
        genre: document.getElementById('genre'),
        name: document.getElementById('name'),
        author: document.getElementById('author'),
        date: document.getElementById('date')
    }
    let totalLike = 0;

    addBtn.addEventListener('click', addHandler);

    function addHandler(event) {
        event.preventDefault();

        let checkFieldsIsEmpty = Object.values(inputFields)
            .every((input) => input.value !== '');

        if (!checkFieldsIsEmpty) {
            return;
        }

        let hitInfoDiv = createElement('div', hitsContainer, '', ['hits-info']);
        createElement('img', hitInfoDiv, null, null, null, { src: './static/img/img.png' });
        createElement('h2', hitInfoDiv, `Genre: ${inputFields.genre.value}`);
        createElement('h2', hitInfoDiv, `Name: ${inputFields.name.value}`);
        createElement('h2', hitInfoDiv, `Author: ${inputFields.author.value}`);
        createElement('h3', hitInfoDiv, `Date: ${inputFields.date.value}`);
        let saveBtn = createElement('button', hitInfoDiv, 'Save song', ['save-btn']);
        let likeBtn = createElement('button', hitInfoDiv, 'Like song', ['like-btn']);
        let deleteBtn = createElement('button', hitInfoDiv, 'Delete', ['delete-btn']);

        saveBtn.addEventListener('click', saveHandler);
        likeBtn.addEventListener('click', liekHandler);
        deleteBtn.addEventListener('click', deleteHandler);

        clearAllField();
    }

    function saveHandler() {
        let songRef = this.parentNode;
        let saveBtn = songRef.querySelector('.save-btn');
        let likeBtn = songRef.querySelector('.like-btn');
        savedContainer.appendChild(songRef);

        saveBtn.remove();
        likeBtn.remove();
    }

    function liekHandler(event) {
        totalLike++;
        this.setAttribute('disabled', true);
        totalLikeContainer.textContent = `Total Likes: ${totalLike}`;
    }

    function deleteHandler() {
        let container = this.parentNode;
        container.remove();
    }

    function clearAllField() {
        Object.values(inputFields).forEach((input) => input.value = '');
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