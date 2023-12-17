function addItem() {
    let newItemText = document.getElementById('newItemText').value;
    let newItemValue = document.getElementById('newItemValue').value;
    let menu = document.getElementById('menu');

    let newOption = document.createElement('option');
    newOption.textContent = newItemText;
    newOption.value = newItemValue;

    menu.appendChild(newOption);

    document.getElementById('newItemText').value = '';
    document.getElementById('newItemValue').value = '';
}