package Implementation;

import Common.ConstantMessages;
import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;

public class AddingANewAddressAndUpdating {

    public static void run(EntityManager entityManager) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(ConstantMessages.ENTER_LAST_NAME);

        String lastName = reader.readLine();
        boolean taskEndCorrectly = false;

        while (!taskEndCorrectly) {
            if (lastName.split("\\s+").length != 1) {
                System.out.println(ConstantMessages.INCORRECT_NAME_FORMAT);
                lastName = reader.readLine();
                continue;
            }

            List<Employee> employees = entityManager.createQuery("SELECT e FROM entities.Employee e " +
                            "WHERE e.lastName = :l_name", Employee.class)
                    .setParameter("l_name", lastName)
                    .getResultList();

            if (employees.isEmpty()) {
                System.out.println(ConstantMessages.NO_SUCH_EMPLOYEES_EXIST);
                System.out.println(ConstantMessages.ENTER_VALID_NAME);
                lastName = reader.readLine();
                continue;
            }

            Address address = createAddress("Vitoshka 15", "Unknown", entityManager);

            for (Employee employee : employees) {
                entityManager.getTransaction().begin();
                employee.setAddress(address);
                entityManager.getTransaction().commit();
            }

            System.out.println(ConstantMessages.AFFECTED_ENTITY + employees.size());

            taskEndCorrectly = true;
        }
    }

    private static Address createAddress(String addressText, String townName, EntityManager entityManager) {
        Address address = new Address();
        Town town = createTown(townName, entityManager);

        address.setText(addressText);
        address.setTown(town);
        address.setEmployees(new HashSet<>());

        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();

        return address;
    }

    private static Town createTown(String townName, EntityManager entityManager) {
        Town town = new Town();
        town.setName(townName);

        entityManager.getTransaction().begin();
        entityManager.persist(town);
        entityManager.getTransaction().commit();

        return town;
    }

}
