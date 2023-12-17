function attachEvents() {
  const BASE_URL = 'http://localhost:3030/jsonstore/collections/students';
  const resultTable = document.querySelector('#results > tbody');
  const submitBtn = document.getElementById('submit');
  const inputFields = {
    firstName: document.getElementsByName('firstName')[0],
    lastName: document.getElementsByName('lastName')[0],
    facultyNumber: document.getElementsByName('facultyNumber')[0],
    grade: document.getElementsByName('grade')[0]
  }

  loadStudent();
  submitBtn.addEventListener('click', submitHandler);


  function submitHandler() {
    if (checkFieldsIsItEmpty(inputFields)) {
      const payload = JSON.stringify({
        firstName: inputFields.firstName.value,
        lastName: inputFields.lastName.value,
        facultyNumber: inputFields.facultyNumber.value,
        grade: inputFields.grade.value
      });

      const httpHeaders = {
        method: 'POST',
        body: payload
      }

      fetch(BASE_URL, httpHeaders)
        .then(() => loadStudent())
        .catch((err) => console.error(err));
    }
  }

  function checkFieldsIsItEmpty(fields) {
    for (const key in fields) {
      if (fields[key].value.trim() === '') {
        return false
      }
      return true;
    }
  }

  function loadStudent() {
    removeAllChildNodes(resultTable);
    fetch(BASE_URL)
      .then((res) => res.json())
      .then((data) => {
        for (const { facultyNumber, firstName, grade, lastName } of Object.values(data)) {
          let row = createElement('tr', resultTable);
          createElement('td', row, firstName);
          createElement('td', row, lastName);
          createElement('td', row, facultyNumber);
          createElement('td', row, grade);
        }
      })

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