function solve(input, ...operations) {
    let number = Number(input);

    for (let i = 0; i < operations.length; i++) {
        let command = operations[i];
        switch (command) {
            case 'chop':
                number /= 2;
                break;
            case 'dice':
                number = Math.sqrt(number);
                break;
            case 'spice':
                number++;
                break;
            case 'bake':
                number *= 3;
                break;
            case 'fillet':
                number = number * 0.80;
                break;
        }

        console.log(number);
    }
}