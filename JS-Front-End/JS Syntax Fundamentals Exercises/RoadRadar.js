function solve(speed, area) {
    let overSpeed = false;
    let speedLimit = 0;
    let difference = 0;
    let status = '';

    switch (area) {
        case 'motorway':
            speedLimit = 130;
            if (speed > 130) {
                overSpeed = true;
                difference = speed - 130;

                if (difference <= 20) {
                    status = 'speeding';
                } else if (difference <= 40) {
                    status = 'excessive speeding';
                } else {
                    status = 'reckless driving';
                }
            }
            break;
        case 'interstate':
            speedLimit = 90;
            if (speed > 90) {
                overSpeed = true;
                difference = speed - 90;

                if (difference <= 20) {
                    status = 'speeding';
                } else if (difference <= 40) {
                    status = 'excessive speeding';
                } else {
                    status = 'reckless driving';
                }
            }
            break;
        case 'city':
            speedLimit = 50;
            if (speed > 50) {
                overSpeed = true;
                difference = speed - 50;

                if (difference <= 20) {
                    status = 'speeding';
                } else if (difference <= 40) {
                    status = 'excessive speeding';
                } else {
                    status = 'reckless driving';
                }
            }
            break;
        case 'residential':
            speedLimit = 20;
            if (speed > 20) {
                overSpeed = true;
                difference = speed - 20;

                if (difference <= 20) {
                    status = 'speeding';
                } else if (difference <= 40) {
                    status = 'excessive speeding';
                } else {
                    status = 'reckless driving';
                }
            }
            break;
    }

    if (overSpeed) {
        console.log(`The speed is ${difference} km/h faster than the allowed speed of ${speedLimit} - ${status}`)
    } else {
        console.log(`Driving ${speed} km/h in a ${speedLimit} zone`)
    }
}