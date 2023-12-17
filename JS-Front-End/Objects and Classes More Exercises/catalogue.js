function catalogueParser(input) {
    let catalogue = {};

    for (const line of input) {
        let [ product, price] = line.split(' : ');
        catalogue[product] = price;
    }

    let sorted = Object.keys(catalogue).sort((a, b) => a.localeCompare(b));

    let groupInitial;

    for (const product of sorted) {
        if (!groupInitial || groupInitial !== product.charAt(0)) {
            groupInitial = product.charAt(0);
            console.log(groupInitial);
        }

        console.log(`  ${product}: ${ catalogue[product]}`)
    }
}