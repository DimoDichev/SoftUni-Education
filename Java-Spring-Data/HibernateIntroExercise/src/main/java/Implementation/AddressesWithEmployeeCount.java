package Implementation;

import entities.Address;

import javax.persistence.EntityManager;
import java.util.List;

public class AddressesWithEmployeeCount {

    public static void run(EntityManager entityManager) {
        List<Address> addresses = entityManager.createQuery("SELECT a FROM entities.Address a " +
                        "ORDER BY size(a.employees) DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        for (Address address : addresses) {
            System.out.printf("%s, %s - %d employees%n",
                    address.getText(),
                    address.getTown().getName(),
                    address.getEmployees().size());
        }
    }

}
