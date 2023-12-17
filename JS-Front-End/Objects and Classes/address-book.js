function addressBookParser(input) {
    addressBook = {};

    for (const line of input) {
        let [ name, address ] = line.split(':');
        addressBook[name] = address;
    }

    let sorted = Object.keys(addressBook).sort((nameA, nameB) => nameA.localeCompare(nameB));

    for (let key of sorted) {
        console.log(`${key} -> ${addressBook[key]}`);
    }
}