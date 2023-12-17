function solve(numArr, rotation) {
    rotation = rotation % numArr.length;

    for (let i = 0; i < rotation; i++) {
        let firstElement = numArr.shift();
        numArr.push(firstElement);
    }

    console.log(numArr.join(' '));
}