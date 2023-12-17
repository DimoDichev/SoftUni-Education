function editElement(reference, matchText, replaceer) {

    while (reference.textContent.includes(matchText)) {
        reference.textContent = reference.textContent.replace(matchText, replaceer);
    }

    console.log(reference.textContent);
}