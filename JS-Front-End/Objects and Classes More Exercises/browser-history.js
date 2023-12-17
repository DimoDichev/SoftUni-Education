function browserHistory(state, activity) {

    for (const line of activity) {
        let [command, ...data] = line.split(' ');

        if (command === 'Open') {
            state["Open Tabs"].push(data.join(' '));
            state["Browser Logs"].push(line);
        } else if (command === 'Close') {
            if (state["Open Tabs"].includes(data.join(' '))) {
                state["Open Tabs"].splice(state["Open Tabs"].indexOf(data.join(' ')), 1);
                state["Recently Closed"].push(data.join(' '));
                state["Browser Logs"].push(line);
            }
        } else if (command === 'Clear') {
            if ('History and Cache' === data.join(' ')) {
                state["Open Tabs"] = [];
                state["Browser Logs"] = [];
                state["Recently Closed"] = [];
            }
        }
    }

    console.log(state["Browser Name"]);
    console.log('Open Tabs: ' + state["Open Tabs"].join(', '));
    console.log('Recently Closed: ' + state["Recently Closed"].join(', '));
    console.log('Browser Logs: ' + state["Browser Logs"].join(', '))
    
}