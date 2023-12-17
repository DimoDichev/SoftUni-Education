package Implementation;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EmployeesMaximumSalaries {

    @SuppressWarnings("unchecked")
    public static void run(EntityManager entityManager) {
        List<Objects[]> rows = entityManager.createNativeQuery("SELECT d.name, MAX(e.salary) FROM employees e " +
                "JOIN departments d on e.employee_id = d.manager_id " +
                "GROUP BY d.department_id " +
                "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000")
                .getResultList();

        for (int i = 0; i < rows.size(); i++) {
            String output = Arrays.toString(rows.get(i))
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", "");
            System.out.println(output);
        }
    }

}
