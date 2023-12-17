function solve(yield) {
    let day = 0;
    let amountOfSpice = 0;

    while (yield >= 100) {
        amountOfSpice += yield;
        amountOfSpice -= 26;
        yield -= 10;
        day++;
    }

    amountOfSpice -= 26;
    
    if (amountOfSpice < 0) {
        amountOfSpice = 0;
    }

    console.log(day);
    console.log(amountOfSpice);
}