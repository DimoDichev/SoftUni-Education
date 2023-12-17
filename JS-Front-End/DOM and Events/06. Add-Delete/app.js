function addItem() {
    let newText = document.getElementById('newItemText').value;
    let newLi = document.createElement('li');
    let aLink = document.createElement('a');

    aLink.appendChild(document.createTextNode('[Delete]'));
    aLink.href = "#";
    aLink.addEventListener("click", deleteItem);


    newLi.appendChild(document.createTextNode(newText));
    newLi.appendChild(aLink);

    document.getElementById('items').appendChild(newLi);
    document.getElementById('newItemText').value = '';

    function deleteItem() {
        newLi.remove();
    }
}