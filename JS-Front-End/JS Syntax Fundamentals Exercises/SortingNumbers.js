function solve(numbersArr) {
    let sortedAcsArr = [...numbersArr].sort((a, b) => a - b);

    let resultArr = [];

    for (let i = 0; i < numbersArr.length; i++) {
        if (i % 2 === 0) {
            let firstNumber = sortedAcsArr.shift();
            resultArr.push(firstNumber);
        } else {
            let lastNumber = sortedAcsArr.pop();
            resultArr.push(lastNumber);
        }
        
    }

    return resultArr;
}