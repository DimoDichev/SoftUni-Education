function solve(input) {
    let elements = input.shift();

    let taskBoard = {};

    for (let i = 0; i < elements; i++) {
        let currentElement = input.shift();
        let [assignee, taskId, title, status, estimatedPoints] = currentElement.split(':')
        taskBoard[assignee] = [];
        let currentTask = {
            taskId,
            title,
            status,
            estimatedPoints
        }
        taskBoard[assignee].push(currentTask)
    }



    for (let i = 0; i < input.length; i++) {
        let tokens = input[i].split(':');

        let command = tokens.shift();
        let assignee = tokens.shift();
        let taskId = tokens.shift()

        switch (command) {
            case 'Add New':
                let [title, status, estimatedPoints] = tokens;
                if (!taskBoard.hasOwnProperty(assignee)) {
                    console.log(`Assignee ${assignee} does not exist on the board!`);
                } else {
                    let currentTask = {
                        taskId,
                        title,
                        status,
                        estimatedPoints
                    }
                    taskBoard[assignee].push(currentTask)
                }
                break;

            case 'Change Status':
                let newStatus = tokens[0];

                let taskNotExist = false;
                for (const task in taskBoard[assignee]) {
                    if (task.taskId = taskId) {
                        taskNotExist = true;
                    }
                }

                if (!taskBoard.hasOwnProperty(assignee)) {
                    console.log(`Assignee ${assignee} does not exist on the board!`);
                } else if (!taskNotExist) {
                    console.log(`Task with ID ${taskId} does not exist for ${assignee}!`)
                } else {
                    let tasks = taskBoard[assignee];
                    for (let i = 0; i < tasks.length; i++) {
                        let {assignee, taskIds, title, status, estimatedPoints} =tasks[i];
                        if (taskIds = taskId) {
                            taskBoard[assignee][i].status = newStatus
                        }
                    }
                }
                break;
            case 'Remove Task':
                break;
        }

    }

    // for (const iterator in taskBoard) {
    //     console.log(iterator)
    //     for (const line of taskBoard[iterator]) {
    //         console.log(line)
    //     }
    // }

}

// solve(
//     [
//         '5',

//         'Kiril:BOP-1209:Fix Minor Bug:ToDo:3',
//         'Mariya:BOP-1210:Fix Major Bug:In Progress:3',
//         'Peter:BOP-1211:POC:Code Review:5',
//         'Georgi:BOP-1212:Investigation Task:Done:2',
//         'Mariya:BOP-1213:New Account Page:In Progress:13',

//         'Add New:Kiril:BOP-1217:Add Info Page:In Progress:5',
//         'Change Status:Peter:BOP-1290:ToDo',
//         'Remove Task:Mariya:1',
//         'Remove Task:Joro:1',
//     ]
// )

solve(
    [
        '4',
        'Kiril:BOP-1213:Fix Typo:Done:1',
        'Peter:BOP-1214:New Products Page:In Progress:2',
        'Mariya:BOP-1215:Setup Routing:ToDo:8',
        'Georgi:BOP-1216:Add Business Card:Code Review:3',
        'Add New:Sam:BOP-1237:Testing Home Page:Done:3',
        'Change Status:Georgi:BOP-1216:Done',
        'Change Status:Will:BOP-1212:In Progress',
        'Remove Task:Georgi:3',
        'Change Status:Mariya:BOP-1215:Done',
    ]
)