package Implementation;

import Common.ConstantMessages;
import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class RemoveTowns {

    public static void run(EntityManager entityManager) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(ConstantMessages.ENTER_TOWN_NAME_FOR_DELETE);
        String townName = reader.readLine();

        List<Town> town = entityManager.createQuery("SELECT t FROM entities.Town t " +
                        "WHERE t.name = :t_name", Town.class)
                .setParameter("t_name", townName)
                .getResultList();

        if (town.isEmpty()) {
            System.out.println(ConstantMessages.NO_SUCH_TOWN_EXIST);
            return;
        }

        int removedAddresses = removeAddresses(town.get(0).getId(), entityManager);

        entityManager.getTransaction().begin();
        entityManager.remove(town.get(0));
        entityManager.getTransaction().commit();

        System.out.printf("%d %s in %s deleted%n",
                removedAddresses,
                removedAddresses > 1 ? "addresses" : "address",
                townName);
    }

    private static int removeAddresses(int townId, EntityManager entityManager) {
        List<Address> addresses = entityManager.createQuery("SELECT a FROM Address a " +
                        "WHERE a.town.id = :t_id", Address.class)
                .setParameter("t_id", townId)
                .getResultList();

        entityManager.getTransaction().begin();
        addresses.forEach(entityManager::remove);
        entityManager.getTransaction().commit();

        return addresses.size();
    }

}
