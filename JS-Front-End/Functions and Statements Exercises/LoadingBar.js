function printLoadingBar(number) {
    if (number === 100) {
        console.log('100% Complete!');
        console.log('[%%%%%%%%%%]')
    } else {
        let percentRepeater = '%'.repeat(number / 10);
        let dotRepeater = '.'.repeat(10 - (number / 10));
        console.log(`${number}% [${percentRepeater}${dotRepeater}]`);
        console.log('Still loading...');
    }
}