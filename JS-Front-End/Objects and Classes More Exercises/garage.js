function garages(input) {
    let garage = {};

    for (let i = 0; i < input.length; i++) {
        let currentGarage = input[i];
        let tokens = currentGarage.split(' - ');
        let garageNumber = tokens[0];
        let carParts = tokens[1].split(', ');

        if (!garage.hasOwnProperty(garageNumber)) {
            garage[garageNumber] = {};
            garage[garageNumber].cars = [];
        }

        let car = {};

        for (let i = 0; i < carParts.length; i++) {
            let [ key, value ] = carParts[i].split(': ');
            car[key] = value;
        }

        garage[garageNumber].cars.push(car);
    }

    for (const key in garage) {
        console.log(`Garage № ${key}`)

        let cars = garage[key].cars;

        for (const car of cars) {
            let properties = [];

            for (const property in car) {
                properties.push(`${property} - ${car[property]}`)
            }

            console.log(`--- ${properties.join(', ')}`);
        }
    }
}