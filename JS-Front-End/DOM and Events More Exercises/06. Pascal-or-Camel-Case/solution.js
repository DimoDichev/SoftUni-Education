function solve() {
  let text = document.getElementById('text').value;
  let currentCase = document.getElementById('naming-convention').value;
  let result = document.getElementById('result');
  result.textContent = '';

  let modifiedText = text.toLowerCase().split(' ');

  if (currentCase === 'Camel Case') {
    let firstWord = modifiedText.shift();
    result.textContent += firstWord;
    for (let i = 0; i < modifiedText.length; i++) {
      let currentWord = modifiedText[i];
      let firstChar = currentWord.charAt(0);
      let otherText = currentWord.slice(1);
      result.textContent += firstChar.toUpperCase() + otherText;
    }
  } else if (currentCase === 'Pascal Case') {
    for (let i = 0; i < modifiedText.length; i++) {
      let currentWord = modifiedText[i];
      let firstChar = currentWord.charAt(0);
      let otherText = currentWord.slice(1);
      result.textContent += firstChar.toUpperCase() + otherText;
    }
  } else {
    result.textContent = 'Error!';
  }
}