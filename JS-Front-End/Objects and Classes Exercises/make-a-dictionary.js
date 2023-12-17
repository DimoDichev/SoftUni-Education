function dictionaryParser(input) {
    let dictionary = {};

    for (const line of input) {
        let currentEntry = Object.entries(JSON.parse(line));
        let key = currentEntry[0][0];
        let value = currentEntry[0][1];
        dictionary[key] = value;
    }

    let sorted = Object.entries(dictionary).sort(([keyA, _valueA], [keyB, _ValueB]) => keyA.localeCompare(keyB));

    for (const [key, _value] of sorted) {
        console.log(`Term: ${key} => Definition: ${dictionary[key]}`);
    }

}