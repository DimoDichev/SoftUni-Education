function sumTable() {
    let table = Array.from(document.getElementsByTagName('td'));

    let sumProduct = 0;

    for (let i = 0; i < table.length - 1; i++) {
        if (i % 2 !== 0) {
            sumProduct += Number(table[i].textContent);
        }
    }

    let sum = document.getElementById('sum');
    sum.textContent = sumProduct;
}