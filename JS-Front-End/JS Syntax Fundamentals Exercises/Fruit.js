function solve(type, weight, price) {
    let totalPrice = price * (weight / 1000);
    console.log(`I need $${totalPrice.toFixed(2)} to buy ${(weight / 1000).toFixed(2)} kilograms ${type}.`)
}