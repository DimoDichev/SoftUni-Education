function attachEvents() {
    const BASE_URL = 'http://localhost:3030/jsonstore/tasks/';
    const loadAllBtn = document.getElementById('load-button');
    const addBtn = document.getElementById('add-button');
    const titleInput = document.getElementById('title');
    const listContainer = document.getElementById('todo-list');


    loadAllBtn.addEventListener('click', loadAllHandler);
    addBtn.addEventListener('click', addHandler);

    function loadAllHandler(event) {
        if (event) {
            event.preventDefault();
        }

        listContainer.innerHTML = '';

        fetch(BASE_URL)
            .then((res) => res.json())
            .then((dataRes) => {
                let data = Object.values(dataRes);
                for (const { name, _id } of data) {
                    let li = createElement('li', listContainer);
                    createElement('span', li, name);
                    let removeBtn = createElement('button', li, 'Remove');
                    let editBtn = createElement('button', li, 'Edit');

                    li.id = _id;

                    removeBtn.addEventListener('click', removeHandler);
                    editBtn.addEventListener('click', editHandler);
                }
            })
            .catch((err) => console.error(err));
    }

    function addHandler(event) {
        event.preventDefault();
        let name = titleInput.value;
        const httpHeaders = {
            method: 'POST',
            body: JSON.stringify({ name })
        }
        fetch(BASE_URL, httpHeaders)
            .then(() => {
                loadAllHandler();
                titleInput.value = '';
            })
            .catch((err) => console.error(err));
    }

    function removeHandler() {
        let id = this.parentNode.id;
        fetch(`${BASE_URL}${id}`, { method: 'DELETE' })
            .then(() => loadAllHandler())
            .catch((err) => console.error(err));
    }

    function editHandler() {
        let parentLi = this.parentNode;
        let [span, _removeBtn, editBtn] = Array.from(parentLi.children);
        let inputField = document.createElement('input');
        let submitBtn = document.createElement('button');

        inputField.value = span.textContent;
        submitBtn.textContent = 'Submit';
        submitBtn.addEventListener('click', submitHandler);

        parentLi.prepend(inputField)
        parentLi.append(submitBtn);

        span.remove();
        editBtn.remove();
    }

    function submitHandler() {
        let parent = this.parentNode;
        let inputField = parent.querySelector('input');

        const httpHeaders = {
            method: 'PATCH',
            body: JSON.stringify({ name: inputField.value })
        }

        fetch(`${BASE_URL}${parent.id}`, httpHeaders)
            .then(() => {
                loadAllHandler();
            })
            .catch((err) => console.error(err));
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
