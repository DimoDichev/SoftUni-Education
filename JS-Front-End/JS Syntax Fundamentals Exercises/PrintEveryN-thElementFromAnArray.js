function solve(stringArr, step) {
    let resultArr = [];

    for (let i = 0; i < stringArr.length; i+= step) {
        resultArr.push(stringArr[i]);
    }

    return resultArr;
}