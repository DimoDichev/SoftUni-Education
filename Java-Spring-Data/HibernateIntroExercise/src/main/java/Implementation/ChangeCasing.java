package Implementation;

import Common.ConstantMessages;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ChangeCasing {

    public static void run(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("UPDATE entities.Town t " +
                "SET t.name = UPPER(t.name) " +
                "WHERE length(t.name) <= :t_length")
                .setParameter("t_length", 5);

        int affectedEntity = query.executeUpdate();

        System.out.println(ConstantMessages.AFFECTED_ENTITY + affectedEntity);

        entityManager.getTransaction().commit();
    }

}
