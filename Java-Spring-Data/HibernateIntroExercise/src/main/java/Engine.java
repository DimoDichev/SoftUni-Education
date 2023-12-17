import Common.ConstantMessages;
import Implementation.*;
import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine implements Runnable {

    private EntityManager entityManager;
    private BufferedReader reader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        System.out.println(ConstantMessages.CHOSE_TASK_MASSAGES);
        System.out.println(ConstantMessages.CHOSE_TASKS_NUMBER);

        try {
            String command = reader.readLine().trim();

            while (!"end".equalsIgnoreCase(command)) {
                if (command.trim().isEmpty()) {
                    System.out.println(ConstantMessages.CHOSE_TASK_MASSAGES);
                    System.out.println(ConstantMessages.CHOSE_TASKS_NUMBER);
                    command = reader.readLine();
                    continue;
                }

                int exNumber = Integer.parseInt(command);
                switch (exNumber) {
                    case 2 -> ChangeCasing.run(entityManager);
                    case 3 -> ContainsEmployee.run(entityManager);
                    case 4 -> EmployeeWithSalaryOver50000.run(entityManager);
                    case 5 -> EmployeesFromDepartment.run(entityManager);
                    case 6 -> AddingANewAddressAndUpdating.run(entityManager);
                    case 7 -> AddressesWithEmployeeCount.run(entityManager);
                    case 8 -> GetEmployeesWithProject.run(entityManager);
                    case 9 -> FindTheLatest10Projects.run(entityManager);
                    case 10 -> IncreaseSalaries.run(entityManager);
                    case 11 -> FindEmployeesByFirstName.run(entityManager);
                    case 12 -> EmployeesMaximumSalaries.run(entityManager);
                    case 13 -> RemoveTowns.run(entityManager);
                    default -> System.out.println(ConstantMessages.NO_SUCH_EXERCISE);
                }

                System.out.println();
                System.out.println(ConstantMessages.CHOSE_ANOTHER_TASK_OR_END);
                command = reader.readLine().trim();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

}
