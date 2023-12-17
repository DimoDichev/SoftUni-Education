package Implementation;

import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class EmployeeWithSalaryOver50000 {

    public static void run(EntityManager entityManager) {
        entityManager.createQuery("SELECT e FROM entities.Employee e " +
                        "WHERE e.salary > :e_salary", Employee.class)
                .setParameter("e_salary", BigDecimal.valueOf(50000L))
                .getResultStream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);
    }

}
