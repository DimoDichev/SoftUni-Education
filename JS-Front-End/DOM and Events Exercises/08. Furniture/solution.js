function solve() {
  let [generateTextArea, buyTextArea] = document.getElementsByTagName('textarea');
  let [generateBtn, buyBtn] = document.getElementsByTagName('button');
  let tbody = document.querySelector('.table > tbody');

  generateBtn.addEventListener('click', generateData);
  buyBtn.addEventListener('click', buyHandler);

  function generateData() {
    let data = JSON.parse(generateTextArea.value);
    for (const { img, name, price, decFactor } of data) {
      let tableRow = createElement('tr', '', tbody);

      let firstColum = createElement('td', '', tableRow);
      createElement('img', '', firstColum, '', '', { src: img });

      let secondColum = createElement('td', '', tableRow);
      createElement('p', name, secondColum);

      let thirdColum = createElement('td', '', tableRow);
      createElement('p', price, thirdColum)

      let fourthColum = createElement('td', '', tableRow);
      createElement('p', decFactor, fourthColum);

      let fifthColum = createElement('td', '', tableRow);
      createElement('input', '', fifthColum, '', '', { type: 'checkbox' });
    }

  }

  function buyHandler() {
    let checkedItems = Array.from(document.querySelectorAll('tbody tr input:checked'))

    let names = [];
    let totalPrice = 0;
    let decorationFactor = 0;

    for (const row of checkedItems) {
      let parent = row.parentElement.parentElement;
      let [ _first, second, third, fourth ] = Array.from(parent.children);
      names.push(second.textContent);
      totalPrice += Number(third.textContent);
      decorationFactor += Number(fourth.textContent);
    }

    buyTextArea.value += `Bought furniture: ${names.join(', ')}\n`;
    buyTextArea.value += `Total price: ${totalPrice.toFixed(2)}\n`;
    buyTextArea.value += `Average decoration factor: ${decorationFactor / names.length}`
    

  }

  function createElement(type, content, parentNode, id, classes, attributes) {
    let element = document.createElement(type);

    if (content && type === 'input') {
      element.value = content;
    }

    if (content && type !== 'input') {
      element.textContent = content;
    }

    if (id) {
      element.id = id;
    }

    if (classes) {
      element.classList.add(...classes);
    }

    if (attributes) {
      for (const key in attributes) {
        element.setAttribute(key, attributes[key]);
      }
    }

    if (parentNode) {
      parentNode.appendChild(element);
    }

    return element;
  }
}