function matrixPrinter(number) {
    let arr = new Array(number).fill(new Array(number).fill(number));

    for (let row = 0; row < arr.length; row++) {
        console.log(arr[row].join(' '));
    }
}