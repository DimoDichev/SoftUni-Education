function solve(words, text) {
    let wordArr = words.split(', ');

    for (let i = 0; i < wordArr.length; i++) {
        let currentWord = wordArr[i];
        let searchWord = '*'.repeat(currentWord.length);

        text = text.replace(searchWord, currentWord);
    }

    console.log(text);
}