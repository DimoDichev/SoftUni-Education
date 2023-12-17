package Implementation;

import entities.Employee;

import javax.persistence.EntityManager;

public class EmployeesFromDepartment {
    public static void run(EntityManager entityManager) {
        entityManager.createQuery("SELECT e FROM entities.Employee e " +
                "WHERE e.department.name = :d_name " +
                "ORDER BY e.salary, e.id", Employee.class)
                .setParameter("d_name", "Research and Development")
                .getResultStream()
                .forEach(e -> {
                    System.out.printf("%s %s from %s - $%.2f%n",
                            e.getFirstName(),
                            e.getLastName(),
                            e.getDepartment().getName(),
                            e.getSalary());
                });
    }

}
