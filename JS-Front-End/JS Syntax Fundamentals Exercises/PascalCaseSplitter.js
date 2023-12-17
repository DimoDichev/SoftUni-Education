function solve(text) {
    let result = [];
    let currentWord = '';

    for (let i = 0; i < text.length; i++) {
        let currentSymbol = text[i];

        currentWord += currentSymbol;

        let nextSymbol = text[i + 1];

        if (nextSymbol === undefined || (nextSymbol >= 'A' && nextSymbol <= 'Z')) {
            result.push(currentWord);
            currentWord = '';
        }
    }

    console.log(result.join(', '));
}