function schoolRegister(input) {
    let register = {};

    for (let student of input) {
        let tokens = student.split(': ')
        let name = tokens[1].split(', ')[0];
        let grade = Number(tokens[2].split(', ')[0]);
        let score = Number(tokens[3]);
        
        if (score >= 3) {
            grade++;
            if (!register.hasOwnProperty(grade)) {
                register[grade] = {};
                register[grade].students = [];
                register[grade].averageScore = 0;
            }

            register[grade].students.push(name);
            register[grade].averageScore += score;
        }
    }

    for (const grade in register) {
        let students = register[grade].students;
        let score = (register[grade].averageScore / students.length).toFixed(2);
        console.log(`${grade} Grade`);
        console.log(`List of students: ${students.join(', ')}`);
        console.log(`Average annual score from last year: ${score}\n`);
    }
}