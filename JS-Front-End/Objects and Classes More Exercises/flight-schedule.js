function flightSchedule(input) {
    let allFlights = input.shift();
    let changedStatusesFlights = input.shift();
    let checkStatus = input.shift()[0];

    let flights = {};

    for (let line of allFlights) {
        let [flight, ...destinationArr] = line.split(' ');
        flights[flight] = {};
        flights[flight].Destination = destinationArr.join(' ');
        flights[flight].Status = '';
    }

    for (let line of changedStatusesFlights) {
        let [flight, status] = line.split(' ');
        if (flights.hasOwnProperty(flight)) {
            flights[flight].Status = status;
        }
    }

    for (let flight in flights) {
        if (flights[flight].Status === '' && checkStatus === 'Ready to fly') {
            flights[flight].Status = checkStatus;
        }

        if (flights[flight].Status === checkStatus) {
            console.log(flights[flight])
        }
    }
}