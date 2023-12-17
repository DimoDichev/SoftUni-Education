window.addEventListener('load', solve);

function solve() {
    const createBtn = document.getElementById('create-task-btn');
    const deleteContainerBtn = document.getElementById('delete-task-btn');

    let taskContainer = document.getElementById('tasks-section');
    let totalPointsContainer = document.getElementById('total-sprint-points');
    let inputHidenContainer = document.getElementById('task-id');

    let inputFields = {
        title: document.getElementById('title'),
        description: document.getElementById('description'),
        label: document.getElementById('label'),
        points: document.getElementById('points'),
        assignee: document.getElementById('assignee')
    }

    let iconCodes = {
        'Feature': '&#8865',
        'Low Priority Bug': '&#9737',
        'High Priority Bug': '&#9888'
    }

    let classParser = {
        'Feature': 'feature',
        'Low Priority Bug': 'low-priority',
        'High Priority Bug': 'high-priority'
    }

    let tasksData = {};

    let taskCount = 0;
    let totalPoints = 0;

    createBtn.addEventListener('click', addHandler);
    deleteContainerBtn.addEventListener('click', deleteHandler);

    function deleteHandler() {
        let taskIdForDel = inputHidenContainer.value;
        let containerForDelete = taskContainer.querySelector(`#${taskIdForDel}`);
        let pointForRemove = Number(inputFields.points.value);
        totalPoints -= pointForRemove;
        totalPointsContainer.textContent = `Total Points ${totalPoints}pts`;

        createBtn.removeAttribute('disabled');
        deleteContainerBtn.setAttribute('disabled', true);
        Object.values(inputFields).forEach(input => input.removeAttribute('disabled'));
        Object.values(inputFields).forEach(input => input.value = '');

        containerForDelete.remove();
    }


    function addHandler(event) {

        let checkFieldsIsEmpty = Object.values(inputFields)
            .every((input) => input.value !== '');
        if (!checkFieldsIsEmpty) {
            return;
        }

        taskCount++;
        let taskId = `task-${taskCount}`
        totalPoints = totalPoints + Number(inputFields.points.value);
        totalPointsContainer.textContent = `Total Points ${totalPoints}pts`;

        let article = createElement('article', taskContainer, null, ['task-card'], taskId);
        createElement('div', article, `${inputFields.label.value} ${iconCodes[inputFields.label.value]}`, ['task-card-label', `${classParser[inputFields.label.value]}`], null, null, true);
        createElement('h3', article, inputFields.title.value, ['task-card-title']);
        createElement('p', article, inputFields.description.value, ['task-card-description']);
        createElement('div', article, `Estimated at ${inputFields.points.value} pts`, ['task-card-points']);
        createElement('div', article, `Assigned to: ${inputFields.assignee.value}`, ['task-card-assignee']);
        let btnContainer = createElement('div', article, null, ['task-card-actions']);
        let deleteBtn = createElement('button', btnContainer, 'Delete');

        deleteBtn.addEventListener('click', loadToDeleteHandler);

        let currentTask = {
            title: null,
            description: null,
            label: null,
            points: null,
            assignee: null
        };

        for (const key in inputFields) {
            currentTask[key] = inputFields[key].value;
        }

        tasksData[taskId] = currentTask;

        Object.values(inputFields).forEach(input => input.value = '');
    }

    function loadToDeleteHandler() {
        let containerId = this.parentNode.parentNode.id;
        let inputs = tasksData[containerId];
        inputHidenContainer.value = containerId;

        for (const key in inputs) {
            inputFields[key].value = inputs[key];
        }

        Object.values(inputFields).forEach(input => input.setAttribute('disabled', true))

        deleteContainerBtn.removeAttribute('disabled');
        createBtn.setAttribute('disabled', true);
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