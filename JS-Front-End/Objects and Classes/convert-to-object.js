function convertToObject(input) {
    let result = JSON.parse(input);
    let entries = Object.entries(result);

    for (const [ key, value ] of entries) {
        console.log(`${key}: ${value}`)
    }
}