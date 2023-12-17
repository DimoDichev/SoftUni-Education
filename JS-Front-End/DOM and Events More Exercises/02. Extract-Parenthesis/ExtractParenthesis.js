function extract(content) {
    let text = document.getElementById(content).textContent;
    let result = [];

    let patern = /\(([^)]+)\)/g;

    let match = patern.exec(text);

    while (match) {
        let curentText = match[0];
        curentText = curentText.replace('(', '');
        curentText = curentText.replace(')', '');
        result.push(curentText);
        match = patern.exec(text);
    }

    return result.join('; ')
}