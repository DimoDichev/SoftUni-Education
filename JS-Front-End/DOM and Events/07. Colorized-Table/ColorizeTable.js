function colorize() {
    let tableRows = Array.from(document.getElementsByTagName('tr'));

    for (let row = 0; row < tableRows.length; row++) {
        if (row % 2 !== 0) {
            tableRows[row].style.backgroundColor = 'Teal';
        }
    }
}