function solve() {
    let baseURL = 'http://localhost:3030/jsonstore/bus/schedule/';
        let nextStopID = 'depot';
        let info = document.querySelector('#info span');
        let departBtn = document.getElementById('depart');
        let arriveBtn = document.getElementById('arrive');
        let currentStop = null;

    function depart() {
        fetch(`${baseURL}${nextStopID}`)
            .then((res) => res.json())
            .then((data) => {
                let { name, next } = data;
                info.textContent = `Next stop ${name}`
                currentStop = name;
                nextStopID = next;
                departBtn.disabled = true;
                arriveBtn.disabled = false;
            })
            .catch(() => {
                departBtn.disabled = true;
                arriveBtn.disabled = true;
                info.textContent = 'Error'
            })
    }

    async function arrive() {
        info.textContent = `Arriving at ${currentStop}`;
        departBtn.disabled = false;
        arriveBtn.disabled = true;
    }

    return {
        depart,
        arrive
    };
}

let result = solve();