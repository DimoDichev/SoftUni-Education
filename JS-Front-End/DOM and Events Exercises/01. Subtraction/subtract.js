function subtract() {
    let firstNum = Number(document.getElementById('firstNumber').value);
    let secondNum = Number(document.getElementById('secondNumber').value);

    let sum = document.getElementById('result');
    sum.textContent = firstNum - secondNum;
    console.log(sum);
}