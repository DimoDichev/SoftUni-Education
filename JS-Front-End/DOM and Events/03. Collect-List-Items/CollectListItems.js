function extractText() {
    let links = Array.from(document.querySelectorAll('#items li'));
    let textArea = document.querySelector('#result');

    for (const link of links) {
        textArea.textContent += link.textContent + '\n';
    }
}