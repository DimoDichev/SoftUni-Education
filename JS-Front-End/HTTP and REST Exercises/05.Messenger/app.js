function attachEvents() {
    const BASE_URL = 'http://localhost:3030/jsonstore/messenger';
    const sendBtn = document.getElementById('submit');
    const refrechBtn = document.getElementById('refresh');
    const author = document.getElementsByName('author')[0];
    const message = document.getElementsByName('content')[0];
    const textArea = document.getElementById('messages');

    refrechBtn.addEventListener('click', refreshHandler);
    sendBtn.addEventListener('click', sendHandler);

    function sendHandler() {
        const payload = JSON.stringify({
            author: author.value,
            content: message.value,
        })

        const httpHeaders = {
            method: 'POST',
            body: payload
        }

        fetch(BASE_URL, httpHeaders)
            .then(() => {
                author.value = '';
                message.value = '';
            })
            .catch((err) => console.error(err));
    }

    function refreshHandler() {
        textArea.textContent = '';
        fetch(BASE_URL)
            .then((res) => res.json())
            .then((data) => {
                let contentArr = [];
                for (const line in data) {
                    let {author, content} = data[line];
                    contentArr.push(`${author}: ${content}`);
                }
                textArea.textContent = contentArr.join('\n');
            })
            .catch((err) => console.error(err));
    }
}

attachEvents();