package Implementation;

import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {

    public static void run(EntityManager entityManager) {
        List<String> departments = List.of("Engineering",
                "Tool Design",
                "Marketing",
                "Information Services");

        List<Employee> employees = entityManager.createQuery("SELECT e FROM entities.Employee e " +
                        "WHERE e.department.name IN (:d_name)", Employee.class)
                .setParameter("d_name", departments)
                .getResultList();

        entityManager.getTransaction().begin();
        employees.forEach(e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12))));
        entityManager.getTransaction().commit();

        employees.forEach(e -> System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary()));
    }

}
