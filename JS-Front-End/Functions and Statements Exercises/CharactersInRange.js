function printCharacters(first, second) {
    let firstAscii = first.charCodeAt(0);
    let secondAscii = second.charCodeAt(0);

    if (firstAscii > secondAscii) {
        [firstAscii, secondAscii] = [secondAscii, firstAscii];
    }

    let result = [];

    for (let i = firstAscii + 1; i < secondAscii; i++) {
        result.push(String.fromCharCode(i));
    }

    console.log(result.join(' '));
}