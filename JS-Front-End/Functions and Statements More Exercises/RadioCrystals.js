function crystalCutter(info) {
    let targetThickness = info[0];

    for (let index = 1; index < info.length; index++) {
        let crystalThickness = info[index];
        let cutCount = 0;
        let lapCount = 0;
        let grindCount = 0;
        let etchCount = 0;

        console.log(`Processing chunk ${crystalThickness} microns`);
        
        //Cut
        while (crystalThickness / 4 >= targetThickness) {
            cutCount++;
            crystalThickness = crystalThickness / 4;
        }

        if (cutCount > 0) {
            crystalThickness = Math.floor(crystalThickness);
            console.log(`Cut x${cutCount}`)
            console.log('Transporting and washing')
        }

        //Lap
        while ((crystalThickness - crystalThickness * 0.20) >= targetThickness) {
            lapCount++;
            crystalThickness = crystalThickness - crystalThickness * 0.20;
        }

        if (lapCount > 0) {
            crystalThickness = Math.floor(crystalThickness);
            console.log(`Lap x${lapCount}`)
            console.log('Transporting and washing')
        }

        //Grind
        while (crystalThickness - 20 >= targetThickness) {
            grindCount++;
            crystalThickness -= 20;
        }

        if (grindCount > 0) {
            crystalThickness = Math.floor(crystalThickness);
            console.log(`Grind x${grindCount}`)
            console.log('Transporting and washing')
        }

        //Etch
        while (crystalThickness - 2 >= targetThickness - 1) {
            etchCount++;
            crystalThickness -= 2;
        }

        if (etchCount > 0) {
            crystalThickness = Math.floor(crystalThickness);
            console.log(`Etch x${etchCount}`)
            console.log('Transporting and washing')
        }

        if (crystalThickness < targetThickness) {
            crystalThickness++;
            console.log('X-ray x1')
        }

        console.log(`Finished crystal ${crystalThickness} microns`)
    }
}