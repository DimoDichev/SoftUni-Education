function phoneBookParser(input) {
    let phoneBook = {};
    for (const line of input) {
        let name = line.split(' ')[0];
        let number = line.split(' ')[1];
        phoneBook[name] = number;
    }
    for (const key in phoneBook) {
        console.log(`${key} -> ${phoneBook[key]}`);
    }
}