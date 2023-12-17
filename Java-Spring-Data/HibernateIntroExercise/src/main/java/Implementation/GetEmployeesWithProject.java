package Implementation;

import Common.ConstantMessages;
import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetEmployeesWithProject {

    public static void run(EntityManager entityManager) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        System.out.println(ConstantMessages.ENTER_EMPLOYEE_ID);

        int id = Integer.parseInt(reader.readLine());

        Employee employee = entityManager.find(Employee.class, id);

        while (employee == null) {
            System.out.println(ConstantMessages.NO_SUCH_EMPLOYEES_EXIST);
            System.out.println(ConstantMessages.ENTER_EMPLOYEE_ID);
            id = Integer.parseInt(reader.readLine());
            employee = entityManager.find(Employee.class, id);
        }

        output.append(String.format("%s %s - %s",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle()));
        output.append(System.lineSeparator());

        employee.getProjects().stream()
                .map(Project::getName)
                .sorted()
                .forEach(p -> output.append("\t").append(p).append(System.lineSeparator()));

        System.out.println(output.toString().trim());
    }

}
