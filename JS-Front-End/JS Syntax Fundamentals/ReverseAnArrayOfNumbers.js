function solve(n, inputArr) {
    let newArr = [];

    for (let i = 0; i < n; i++) {
        newArr.push(inputArr[i]);
    }

    newArr.reverse();

    console.log(newArr.join(' '));
}