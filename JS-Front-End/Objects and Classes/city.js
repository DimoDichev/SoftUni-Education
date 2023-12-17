function printInfo(info) {
    let entries = Object.entries(info);
    for (let [ key, value ] of entries) {
        console.log(`${key} -> ${value}`);
    }
}