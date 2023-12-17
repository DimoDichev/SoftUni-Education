function factorialDivision(numFirst, numSecond) {
    return (calculateFactorial(numFirst) / calculateFactorial(numSecond)).toFixed(2);    

    function calculateFactorial(num) {
        if (num === 1) {
            return 1;
        }

        return num * calculateFactorial(num - 1);
    }
}