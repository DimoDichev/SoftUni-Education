package Implementation;

import Common.ConstantMessages;
import entities.Employee;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FindEmployeesByFirstName {

    public static void run(EntityManager entityManager) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(ConstantMessages.ENTER_FIRST_NAME_PATTERN);
        String pattern = reader.readLine();
        String editedPattern = pattern + "%";

        List<Employee> employees = entityManager.createQuery("SELECT e FROM entities.Employee e " +
                        "WHERE e.firstName LIKE :f_pattern", Employee.class)
                .setParameter("f_pattern", editedPattern)
                .getResultList();

        employees.forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                e.getFirstName(),
                e.getLastName(),
                e.getJobTitle(),
                e.getSalary()));
    }

}
