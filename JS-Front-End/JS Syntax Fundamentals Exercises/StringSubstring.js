function solve(searchWord, text) {
    let textArr = text.split(' ');
    let wordLowerCase = searchWord.toLowerCase();

    for (const word of textArr) {
        if (word.toLowerCase() === wordLowerCase) {
            return searchWord;
        }
    }
    
    return searchWord + ' not found!';
}