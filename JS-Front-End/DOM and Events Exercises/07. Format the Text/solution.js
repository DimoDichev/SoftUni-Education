function solve() {
  let textInput = document.getElementById('input').value;
  let textOutput = document.getElementById('output');

  let textArr = Array.from(textInput.split('.'));
  textArr.pop();
  
  while (textArr.length > 0) {
    let currentText = textArr.splice(0, 3)
      .map((e) => e.trim())
      .join('.') + '.';

    let p = document.createElement('p');
    p.textContent = currentText;
    
    textOutput.appendChild(p);
  }
}