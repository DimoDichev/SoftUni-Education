function printDNA(number) {
    let dna = ['A','T','C','G','T','T','A','G','G','G'];
    let indexDna = 0;
    let rowTemplate = 1;

    for (let i = 0; i < number; i++) {
        switch (rowTemplate) {
            case 1:
                console.log(`**${dna[indexDna]}${dna[indexDna + 1]}**`);
                break;
            case 2:
                console.log(`*${dna[indexDna]}--${dna[indexDna + 1]}*`);
                break;
            case 3:
                console.log(`${dna[indexDna]}----${dna[indexDna + 1]}`);
                break;
            case 4:
                console.log(`*${dna[indexDna]}--${dna[indexDna + 1]}*`);
                break;
        }

        indexDna +=2;
        rowTemplate ++;

        if (indexDna === 10) {
            indexDna = 0;
        }

        if (rowTemplate === 5) {
            rowTemplate = 1;
        }
    }
}

printDNA(4)