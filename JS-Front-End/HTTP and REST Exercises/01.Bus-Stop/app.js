function getInfo() {
    let baseURL = 'http://localhost:3030/jsonstore/bus/businfo/';
    let stopId = document.getElementById('stopId').value;
    let stopName = document.getElementById('stopName');
    let busesList = document.getElementById('buses');

    busesList.innerHTML = '';

    fetch(`${baseURL}${stopId}`)
        .then((res) => res.json())
        .then((data) => {
            let { name, buses } = data;
            stopName.textContent = name;
            for (const busId in buses) {
                let li = document.createElement('li');
                li.textContent = `Bus ${busId} arrives in ${buses[busId]} minutes`
                busesList.appendChild(li);
            }
        })
        .catch(() => stopName.textContent = 'Error');
}