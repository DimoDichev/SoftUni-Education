function changeNumber(number) {
    
    while (averageSum(number) <= 5) {
        number = (number * 10) + 9;
    }

    console.log(number);

    function averageSum(num) {
        return (num.toString().split('').map(n => Number(n)).reduce((a, b) => a + b, 0)) / num.toString().length;
    }
}