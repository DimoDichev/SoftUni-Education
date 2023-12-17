function solve(num) {
    let numberToString = num.toString();
    let sameSymbol = 1;
    let result = 0;

    for (let i = 0; i < numberToString.length; i++) {
        result += Number(numberToString.charAt(i));
        if (i !== 0 && numberToString.charAt(i - 1) === numberToString.charAt(i)) {
            sameSymbol++;
        }
    }

    console.log(sameSymbol === numberToString.length);
    console.log(result);
}