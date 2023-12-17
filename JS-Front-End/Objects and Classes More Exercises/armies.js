function armies(input) {
    let leaders = {};

    for (const command of input) {
        if (command.includes('arrives')) {
            let tokens = command.split(' ');
            tokens.pop();
            let leaderName = tokens.join(' ');
            leaders[leaderName] = {};
            leaders[leaderName].totalArmy = 0;
            leaders[leaderName].armies = [];

        } else if (command.includes('defeated')) {
            let tokens = command.split(' ');
            tokens.pop();
            let leaderName = tokens.join(' ');
            delete leaders[leaderName];

        } else if (command.includes(':')) {
            let leaderName = command.split(': ')[0];
            if (leaders.hasOwnProperty(leaderName)) {
                let armyName = command.split(': ')[1].split(', ')[0];
                let armyCount = Number(command.split(': ')[1].split(', ')[1]);
                leaders[leaderName].totalArmy += armyCount;
                leaders[leaderName].armies.push({ name: armyName, count: armyCount });
            }

        } else if (command.includes('+')) {
            let armyName = command.split(' + ')[0];
            let armyCount = Number(command.split(' + ')[1]);
            for (const leader in leaders) {
                let armies = leaders[leader].armies;
                for (const army of armies) {
                    if (army.name === armyName) {
                        army.count += armyCount;
                        leaders[leader].totalArmy += armyCount;
                    }
                }
            }
        }
    }

    for (const [name, armies] of Object.entries(leaders).sort(([,a], [,b]) => b.totalArmy - a.totalArmy)) {
        console.log(`${name}: ${leaders[name].totalArmy}`)

        armies.armies.sort((a, b) => b.count - a.count).forEach((el) => {
            console.log(`>>> ${el.name} - ${el.count}`)
        })
    }
}