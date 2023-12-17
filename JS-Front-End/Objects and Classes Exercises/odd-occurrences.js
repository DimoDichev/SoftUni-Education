function printOddWords(input) {
    let words = {};
    let result = [];

    for (const word of input.split(' ')) {
        let lowerCaseWord = word.toLowerCase();
        if (!words.hasOwnProperty(lowerCaseWord)) {
            words[lowerCaseWord] = 1;
        } else {
            let newValue = Object.entries(words).find(([key, _value]) => key === lowerCaseWord);
            words[lowerCaseWord] = newValue[1] + 1;
        }
    }

    for (const key in words) {
        let value = words[key];
        if (value % 2 != 0) {
            result.push(key);
        }
    }

    console.log(result.join(' '));
}