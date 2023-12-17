function solve(x1, y1, x2, y2) {

    let isValidX1Y100 = Number.isInteger(Math.sqrt(((0 - x1) ** 2) + ((0 - y1) ** 2)))
    let isValidX2Y200 = Number.isInteger(Math.sqrt(((x2 - 0) ** 2) + ((y2 - 0) ** 2)))
    let isValidX1Y1X2Y2 = Number.isInteger(Math.sqrt(((x2 - x1) ** 2) + ((y2 - y1) ** 2)))

    if (isValidX1Y100) {
        console.log(`{${x1}, ${y1}} to {0, 0} is valid`);
    } else {
        console.log(`{${x1}, ${y1}} to {0, 0} is invalid`);
    }

    if (isValidX2Y200) {
        console.log(`{${x2}, ${y2}} to {0, 0} is valid`);
    } else {
        console.log(`{${x2}, ${y2}} to {0, 0} is invalid`);
    }

    if (isValidX1Y1X2Y2) {
        console.log(`{${x1}, ${y1}} to {${x2}, ${y2}} is valid`);
    } else {
        console.log(`{${x1}, ${y1}} to {${x2}, ${y2}} is invalid`);
    }
}