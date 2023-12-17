function attachEvents() {
    const BASE_URL = 'http://localhost:3030/jsonstore/tasks/'
    const loadBtn = document.getElementById('load-board-btn');
    const createTaskBtn = document.getElementById('create-task-btn');

    const toDoContainer = document.getElementById('todo-section');
    const inProgressContainer = document.getElementById('in-progress-section');
    const codeReviewContainer = document.getElementById('code-review-section');
    const doneContainer = document.getElementById('done-section');

    let taskTitleInput = document.getElementById('title');
    let taskDeskInput = document.getElementById('description');

    loadBtn.addEventListener('click', loadHandler);
    createTaskBtn.addEventListener('click', createTaskHandler);

    function createTaskHandler() {
        const payload = JSON.stringify({
            title: taskTitleInput.value,
            description: taskDeskInput.value,
            status: 'ToDo'
        });
        const httpHeaders = {
            method: 'POST',
            body: payload
        };
        fetch(BASE_URL, httpHeaders)
            .then(() => {
                loadHandler();
                taskTitleInput.value = '';
                taskDeskInput.value = '';
            })
            .catch((err) => console.error(err));
    }

    function loadHandler() {
        fetch(BASE_URL)
            .then((res) => res.json())
            .then((data) => {
                toDoContainer.innerHTML = '';
                inProgressContainer.innerHTML = '';
                codeReviewContainer.innerHTML = '';
                doneContainer.innerHTML = '';

                for (const { description, status, title, _id } of Object.values(data)) {
                    switch (status) {
                        case 'ToDo':
                            let liToDo = createElement('li', toDoContainer, null, ['task'], _id);
                            createElement('h3', liToDo, title);
                            createElement('p', liToDo, description);
                            let liToDoBtn = createElement('button', liToDo, 'Move to In Progress');
                            liToDoBtn.addEventListener('click', moveHandler);
                            break;
                        case 'In Progress':
                            let liInProgress = createElement('li', inProgressContainer, null, ['task'], _id);
                            createElement('h3', liInProgress, title);
                            createElement('p', liInProgress, description);
                            let liInProgressBtn = createElement('button', liInProgress, 'Move to Code Review');
                            liInProgressBtn.addEventListener('click', moveHandler);
                            break;
                        case 'Code Review':
                            let liCodeReview = createElement('li', codeReviewContainer, null, ['task'], _id);
                            createElement('h3', liCodeReview, title);
                            createElement('p', liCodeReview, description);
                            let liCodeReviewBtn = createElement('button', liCodeReview, 'Move to Done');
                            liCodeReviewBtn.addEventListener('click', moveHandler);
                            break;
                        case 'Done':
                            let liDone = createElement('li', doneContainer, null, ['task'], _id);
                            createElement('h3', liDone, title);
                            createElement('p', liDone, description);
                            let liDoneBtn = createElement('button', liDone, 'Close');
                            liDoneBtn.addEventListener('click', moveHandler);
                            break;
                    }
                }
            })
            .catch(err => console.error(err));
    }

    function moveHandler() {
        let container = this.parentNode;
        let title = container.querySelector('h3');
        let description = container.querySelector('p');
        let status = container.querySelector('button')
        let id = this.parentNode.id;

        switch (status.textContent) {
            case 'Move to In Progress':
                const moveInProgres = JSON.stringify({
                    title: title.textContent,
                    description: description.textContent,
                    status: 'In Progress'
                });
                const httpHeadersInProgress = {
                    method: 'PATCH',
                    body: moveInProgres
                };
                fetch(`${BASE_URL}${id}`, httpHeadersInProgress)
                    .then(() => loadHandler())
                    .catch((err) => console.error(err));
                break;
            case 'Move to Code Review':
                const moveToCode = JSON.stringify({
                    title: title.textContent,
                    description: description.textContent,
                    status: 'Code Review'
                });
                const httpHeadersToCode = {
                    method: 'PATCH',
                    body: moveToCode
                };
                fetch(`${BASE_URL}${id}`, httpHeadersToCode)
                    .then(() => loadHandler())
                    .catch((err) => console.error(err));
                break;
            case 'Move to Done':
                const moveToDone = JSON.stringify({
                    title: title.textContent,
                    description: description.textContent,
                    status: 'Done'
                });
                const httpHeadersToDone = {
                    method: 'PATCH',
                    body: moveToDone
                };
                fetch(`${BASE_URL}${id}`, httpHeadersToDone)
                    .then(() => loadHandler())
                    .catch((err) => console.error(err));
                break;
            case 'Close':
                fetch(`${BASE_URL}${id}`, { method: 'DELETE'})
                    .then(() => loadHandler())
                    .catch((err) => console.error(err));
                break;
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

//"title": "Fix Bug",
// "description": "Fix Bug Fast",
// "status": "ToDo",