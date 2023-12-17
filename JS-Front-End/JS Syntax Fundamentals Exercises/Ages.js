function solve(ages) {
    let result = '';

    if (0 <= ages && ages <= 2) {
        result = 'baby';
    } else if (3 <= ages && ages <= 13) {
        result = 'child';
    } else if (14 <= ages && ages <= 19) {
        result = 'teenager';
    } else if (20 <= ages && ages <= 65) {
        result = 'adult';
    } else if (66 <= ages) {
        result = 'elder';
    } else {
        result = 'out of bounds';
    }

    console.log(result);
}