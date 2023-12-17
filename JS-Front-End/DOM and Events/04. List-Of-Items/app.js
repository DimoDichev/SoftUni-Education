function addItem() {
    let text = document.getElementById('newItemText').value;
    let newLi = document.createElement("li");
    newLi.appendChild(document.createTextNode(text));
    document.getElementById('items').appendChild(newLi);
    document.getElementById('newItemText').value = '';
}