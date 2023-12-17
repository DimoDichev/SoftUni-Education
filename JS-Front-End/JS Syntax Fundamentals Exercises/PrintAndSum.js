function solve(startNum, endNum) {
    let numArr = [];
    let sum = 0;

    for (let i = startNum; i <= endNum; i++) {
        let currentNum = i;
        numArr.push(currentNum);
        sum += currentNum;
    }

    console.log(numArr.join(" "));
    console.log('Sum: ' + sum);
}