function signCheck(...number) {
    return number.filter((n) => n < 0).length % 2 === 0 ? 'Positive' : 'Negative';
}