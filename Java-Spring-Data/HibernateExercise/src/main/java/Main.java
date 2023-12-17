import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("hibernate_exercise")
                .createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.getTransaction().commit();
    }

}
