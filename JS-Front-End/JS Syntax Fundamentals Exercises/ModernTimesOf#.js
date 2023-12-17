function solve(text) {
    let textArr = text.split(' ');
    let result = [];

    for (let i = 0; i < textArr.length; i++) {
        let currentWord = textArr[i];

        if (currentWord.startsWith('#') && currentWord.length > 1 && checkIsWordIsValid(currentWord)) {
            result.push(currentWord.substring(1));
        }
    }

    console.log(result.join('\n'));

    function checkIsWordIsValid(word) {
        let wordInLowerCase = word.toLowerCase().substring(1);

        for (const symbol of word) {
            if (symbol < 97 || symbol > 122) {
                return false;
            }
        }

        return true;
    }
}