window.addEventListener("load", solve);

function solve() {
  const inputFields = {
    firstName: document.getElementById('first-name'),
    lastName: document.getElementById('last-name'),
    age: document.getElementById('age'),
    title: document.getElementById('story-title'),
    genre: document.getElementById('genre'),
    story: document.getElementById('story')
  }
  const publishBtn = document.getElementById('form-btn');
  const previewList = document.getElementById('preview-list');
  const mainContainer = document.getElementById('main');

  let storyState = {
    firstName: null,
    lastName: null,
    age: null,
    title: null,
    genre: null,
    story: null
  }

  publishBtn.addEventListener('click', publishStoryHandler);

  function publishStoryHandler() {
    let checkFieldsIsEmpty = Object.values(inputFields)
      .every((input) => input.value !== '');

    if (!checkFieldsIsEmpty) {
      return;
    }

    let storyInfoContainer = createElement('li', previewList, '', ['story-info']);
    let articleContainer = createElement('article', storyInfoContainer);
    createElement('h4', articleContainer, `Name: ${inputFields.firstName.value} ${inputFields.lastName.value}`);
    createElement('p', articleContainer, `Age: ${inputFields.age.value}`);
    createElement('p', articleContainer, `Title: ${inputFields.title.value}`);
    createElement('p', articleContainer, `Genre: ${inputFields.genre.value}`);
    createElement('p', articleContainer, inputFields.story.value);
    let saveBtn = createElement('button', storyInfoContainer, 'Save Story', ['save-btn']);
    let editBtn = createElement('button', storyInfoContainer, 'Edit Story', ['edit-btn']);
    let deleteBtn = createElement('button', storyInfoContainer, 'Delete Story', ['delete-btn']);

    saveBtn.addEventListener('click', saveHandler);
    editBtn.addEventListener('click', editHandler);
    deleteBtn.addEventListener('click', deleteHandler);

    for (const key in inputFields) {
      storyState[key] = inputFields[key].value;
    }

    publishBtn.setAttribute('disabled', true);
    Object.values(inputFields).forEach((input) => input.value = '');
  }

  function saveHandler() {
    mainContainer.innerHTML = '';
    createElement('h1', mainContainer, 'Your scary story is saved!');
  }

  function editHandler() {
    for (const key in storyState) {
      inputFields[key].value = storyState[key];
    }
    let elementForRemove = this.parentNode;
    elementForRemove.remove();
    publishBtn.removeAttribute('disabled');
  }

  function deleteHandler() {
    let liForRemove = this.parentNode;
    liForRemove.remove();
    publishBtn.removeAttribute('disabled');
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
