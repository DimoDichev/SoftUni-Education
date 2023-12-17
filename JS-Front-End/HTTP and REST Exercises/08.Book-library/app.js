function attachEvents() {
  const BASE_URL = 'http://localhost:3030/jsonstore/collections/books';
  const tableContainer = document.querySelector('table > tbody');
  const loadBtn = document.getElementById('loadBooks');
  const submitBtn = document.querySelector('#form > button');
  const inputFields = {
    titleInput: document.querySelectorAll('#form input')[0],
    authorInput: document.querySelectorAll('#form input')[1]
  }
  const formTitle = document.querySelector('#form > h3');
  let idForEditedBook = '';

  loadBtn.addEventListener('click', loadBookHandler);
  submitBtn.addEventListener('click', submitHandler);

  function loadBookHandler() {
    removeAllChildNodes(tableContainer);
    fetch(BASE_URL)
      .then((res) => res.json())
      .then((data) => {
        for (const [id, book] of Object.entries(data)) {
          let { author, title } = book;
          let row = createElement('tr', tableContainer, '', null, id);
          createElement('td', row, title);
          createElement('td', row, author);
          let btnContainer = createElement('td', row);
          let editBtn = createElement('button', btnContainer, 'Edit');
          let deteleBtn = createElement('button', btnContainer, 'Delete');

          editBtn.addEventListener('click', editHandler);
          deteleBtn.addEventListener('click', deleteHandler);
        }
      })
  }

  function submitHandler() {
    if (checkFieldsIsItEmpty(inputFields)) {
      if (submitBtn.textContent === 'Save') {
        const payload = JSON.stringify({
          author: inputFields.authorInput.value,
          title: inputFields.titleInput.value
        });

        const httpHeaders = {
          method: 'PUT',
          body: payload
        }

        fetch(`${BASE_URL}/${idForEditedBook}`, httpHeaders)
          .then(() => {
            formTitle.textContent = 'FORM';
            submitBtn.textContent = 'Submit';
            loadBookHandler();
          })
          .catch((err) => console.error(err));

      } else {
        const payload = JSON.stringify({
          author: inputFields.authorInput.value,
          title: inputFields.titleInput.value
        });

        const httpHeaders = {
          method: 'POST',
          body: payload
        }

        fetch(BASE_URL, httpHeaders)
          .then(() => loadBookHandler())
          .catch((err) => console.error(err));
      }
      for (const input in inputFields) {
        inputFields[input].value = '';
      }
    }
  }

  function editHandler() {
    formTitle.textContent = 'EditFORM';
    submitBtn.textContent = 'Save';
    inputFields.titleInput.value = this.parentNode.parentNode.querySelectorAll('td')[0].textContent;
    inputFields.authorInput.value = this.parentNode.parentNode.querySelectorAll('td')[1].textContent;
    idForEditedBook = this.parentNode.parentNode.id;
  }

  function deleteHandler() {
    let id = this.parentNode.parentNode.id;
    fetch(`${BASE_URL}/${id}`, { method: 'DELETE' })
      .then(() => {
        loadBookHandler();
      })
      .catch((err) => console.error(err));
  }

  function checkFieldsIsItEmpty(fields) {
    for (const key in fields) {
      if (fields[key].value.trim() === '') {
        return false
      }
      return true;
    }
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