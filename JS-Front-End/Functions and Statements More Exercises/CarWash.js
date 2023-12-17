function calculateCarWash(commands) {
    let percentCleaning = 0;

    for (let i = 0; i < commands.length; i++) {
        let currentCommand = commands[i];

        switch (currentCommand) {
            case 'soap':
                percentCleaning += 10;
                break;
            case 'water':
                percentCleaning = percentCleaning * 1.20;
                break;
            case 'vacuum cleaner':
                percentCleaning = percentCleaning * 1.25;
                break;
            case 'mud':
                percentCleaning = percentCleaning - (percentCleaning * 0.10);
                break;
        }
    }

    console.log(`The car is ${percentCleaning.toFixed(2)}% clean.`);
}