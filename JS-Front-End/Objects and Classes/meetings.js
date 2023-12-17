function meetings(info) {
    let meetingCalendar = {};
    for (const line of info) {
        let day = line.split(' ')[0];
        let person = line.split(' ')[1];

        if(!meetingCalendar.hasOwnProperty(day)) {
            meetingCalendar[day] = person;
            console.log(`Scheduled for ${day}`);
        } else {
            console.log(`Conflict on ${day}!`);
        }
    }

    for (const key in meetingCalendar) {
        console.log(`${key} -> ${meetingCalendar[key]}`);
    }
}