function piccolo(input) {
    let parking = {};

    for (const line of input) {
        let command = line.split(', ')[0];
        let carNumber = line.split(', ')[1];

        if (command === 'IN' && !parking.hasOwnProperty(carNumber)) {
            parking[carNumber] = 1;
        } else if (command === 'OUT' && parking.hasOwnProperty(carNumber)) {
            delete parking[carNumber];
        }
    }

    let sortedParking = Object.keys(parking).sort((a, b) => a.localeCompare(b));

    for (const car of sortedParking) {
        console.log(car);
    }
}