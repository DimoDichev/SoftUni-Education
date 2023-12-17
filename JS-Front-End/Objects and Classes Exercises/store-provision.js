function storeProvision(stock, ordered) {
    let allProduct = [...stock, ...ordered];

    let store = {};

    for (let i = 0; i < allProduct.length; i+=2) {
        let product = allProduct[i];
        let value = Number(allProduct[i + 1]);

        if (!store.hasOwnProperty(product)) {
            store[product] = value;
        } else {
            let newValue = store[product] + value;
            store[product] = newValue;
        }
    }

    for (const key in store) {
        console.log(`${key} -> ${store[key]}`);
    }
}