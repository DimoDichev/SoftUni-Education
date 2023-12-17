package Implementation;

import Common.ConstantMessages;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContainsEmployee {

    public static void run(EntityManager entityManager) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(ConstantMessages.ENTER_FULL_NAME);

        String inputLine = reader.readLine();
        boolean taskEndCorrectly = false;

        while (!taskEndCorrectly) {
            String[] fullName = inputLine.split("\\s+");

            if (fullName.length != 2) {
                System.out.println(ConstantMessages.INCORRECT_NAME_FORMAT);
                inputLine = reader.readLine();
                continue;
            }

            String firstName = fullName[0];
            String lastName = fullName[1];

            long count = entityManager.createQuery("SELECT COUNT(e.id) FROM entities.Employee e " +
                            "WHERE e.firstName = :f_name AND e.lastName = :l_name", Long.class)
                    .setParameter("f_name", firstName)
                    .setParameter("l_name", lastName)
                    .getSingleResult();

            System.out.println(count == 0 ? "No" : "Yes");
            taskEndCorrectly = true;
        }

    }

}
