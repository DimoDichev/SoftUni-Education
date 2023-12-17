function solve(input) {
    const goldPrice = 67.51;
    const bitcoinPrice = 11949.16;

    let bitcoins = 0;
    let dayOfFirstBitcoin = 0;
    let money = 0;

    for (let i = 0; i < input.length; i++) {
        let grams = input[i];

        if ((i + 1) % 3 === 0) {
            grams = grams * 0.70;
        }

        money = money + (grams * goldPrice);

        if (money >= bitcoinPrice) {
            if (dayOfFirstBitcoin === 0) {
                dayOfFirstBitcoin = i + 1;
            }

            let newBitcoinBuy = Math.floor(money / bitcoinPrice);
            bitcoins = bitcoins + newBitcoinBuy;
            money = money - (newBitcoinBuy * bitcoinPrice);
        }
    }

    console.log(`Bought bitcoins: ${bitcoins}`);
    if (dayOfFirstBitcoin !== 0) {
        console.log(`Day of the first purchased bitcoin: ${dayOfFirstBitcoin}`);
    }
    console.log(`Left money: ${money.toFixed(2)} lv.`)
}