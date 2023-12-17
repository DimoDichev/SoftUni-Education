function solve(namesArr) {
    return namesArr.sort((a, b) => a.localeCompare(b)).map((text, index) => `${index + 1}.${text}`).join('\n');
}
