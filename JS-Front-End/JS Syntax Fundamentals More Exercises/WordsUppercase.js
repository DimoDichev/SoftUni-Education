function solve(text) {
    let regexp = /\w+/g;
    let result = [...text.matchAll(regexp)];

    console.log(result.join(', ').toUpperCase());
}