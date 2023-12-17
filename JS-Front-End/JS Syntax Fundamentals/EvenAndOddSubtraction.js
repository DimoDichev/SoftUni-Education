function solve(arr) {
    let even = 0;
    let odd = 0;

    for (let i = 0; i < arr.length; i++) {
        let current = Number(arr[i]);
        if (current % 2 === 0) {
            even += current;
        } else {
            odd += current;
        }
    }

    console.log(even - odd);
}