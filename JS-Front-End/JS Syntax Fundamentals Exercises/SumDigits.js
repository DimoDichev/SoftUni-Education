function solve(number) {
    let stringNumber = number.toString();
    let sum = 0;

    for (const num of stringNumber) {
        sum += Number(num);
    }

    console.log(sum);
}