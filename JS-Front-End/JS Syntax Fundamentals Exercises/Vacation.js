function solve(count, type, day) {

    let price = 0;
    
    if (type === 'Students') {

        if (day === 'Friday') {
            price = count * 8.45;
        } else if (day === 'Saturday') {
            price = count * 9.80;
        } else if (day === 'Sunday') {
            price = count * 10.46;
        }

        if (count >= 30) {
            price = price * 0.85;
        }

    } else if (type === 'Business') {

        if (count >= 100) {
            count -= 10;
        }

        if (day === 'Friday') {
            price = count * 10.90;
        } else if (day === 'Saturday') {
            price = count * 15.60;
        } else if (day === 'Sunday') {
            price = count * 16.00;
        }

    } else if (type === 'Regular') {

        if (day === 'Friday') {
            price = count * 15.00;
        } else if (day === 'Saturday') {
            price = count * 20.00;
        } else if (day === 'Sunday') {
            price = count * 22.50;
        }

        if (count >= 10 && count <= 20) {
            price = price * 0.95;
        }

    }

    console.log(`Total price: ${price.toFixed(2)}`)
}