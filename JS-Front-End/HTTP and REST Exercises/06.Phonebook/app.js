function attachEvents() {
    const BASE_URL = 'http://localhost:3030/jsonstore/phonebook';
    const phonebook = document.getElementById('phonebook');
    const loadBtn = document.getElementById('btnLoad');
    const createBtn = document.getElementById('btnCreate');
    const personInput = document.getElementById('person');
    const phoneInput = document.getElementById('phone');

    loadBtn.addEventListener('click', loadHandler);
    createBtn.addEventListener('click', createHandler)

    function createHandler() {
        const payload = JSON.stringify({
            person: personInput.value,
            phone: phoneInput.value,
        });

        const httpHeaders = {
            method: 'POST',
            body: payload
        }

        fetch(BASE_URL, httpHeaders)
            .then(() => {
                loadHandler();
                personInput.value = '';
                phoneInput.value = '';
            })
            .catch((err) => console.error(err));
    }

    function loadHandler() {
        fetch(BASE_URL)
            .then((res) => res.json())
            .then((data) => {
                removeAllChildNodes(phonebook);
                for (const { person, phone, _id } of Object.values(data)) {
                    let li = createElement('li', phonebook, `${person}: ${phone}`, null, _id);
                    let deleteBtn = createElement('button', li, 'Delete');

                    deleteBtn.addEventListener('click', deteleHandler);
                }

            })
    }

    function deteleHandler() {
        let parent = this.parentNode;
        fetch(`${BASE_URL}/${parent.id}`, { method: 'DELETE' })
            .then(() => loadHandler())
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