function printObject(input) {
    let townsArr = [];

    for (let i = 0; i < input.length; i++) {
        let [ town, latitude, longitude] = input[i].split(' | ');
        let currentTown = {
            town,
            latitude: Number(latitude).toFixed(2),
            longitude: Number(longitude).toFixed(2),
        }
        townsArr.push(currentTown);
    }

    for (const town of townsArr) {
        console.log(town);
    }
}