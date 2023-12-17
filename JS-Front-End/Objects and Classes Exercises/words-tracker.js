function wordsTracker(input) {
    let searchedWords = {};

    let words = input.shift().split(' ').forEach( (w) => {
        searchedWords[w] = 0;
    });

    for (const word of input) {
        for (const key in searchedWords) {
            if (key === word) { 
                searchedWords[key] = searchedWords[key] + 1;
            }
        }
    }

    let sortedWords = Object.entries(searchedWords).sort(([_keyA, valueA], [_keyB, valueB]) => valueB - valueA);

    for (const word of sortedWords) {
        console.log(`${word[0]} - ${word[1]}`)
    }
}