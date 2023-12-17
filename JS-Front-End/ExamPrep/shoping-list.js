function solve(input) {
    let products = input.shift().split('!');

    for (const commandInput of input) {
        if (commandInput === 'Go Shopping!') {
            break;
        }

        let command = commandInput.split(' ')[0];
        let product = commandInput.split(' ')[1];

        switch (command) {
            case 'Urgent':
                if (!products.includes(product)) {
                    products.unshift(product);
                }
                break;
            case 'Unnecessary':
                if (products.includes(product)) {
                    let indexForRemove = products.indexOf(product);
                    products.splice(indexForRemove, 1);
                }
                break;
            case 'Correct':
                if (products.includes(product)) {
                    let newProduct = commandInput.split(' ')[2];
                    let indexForChange = products.indexOf(product);
                    products.splice(indexForChange, 1, newProduct);
                }
                break;
            case 'Rearrange':
                if (products.includes(product)) {
                    let indexOfProduct = products.indexOf(product);
                    let productForMove = products.splice(indexOfProduct, 1);
                    products.push(productForMove);
                }
                break;
        }
    }

    console.log(products.join(', '));

}