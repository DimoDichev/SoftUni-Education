package Implementation;

import entities.Project;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class FindTheLatest10Projects {

    public static void run(EntityManager entityManager) {
        StringBuilder output = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<Project> projects = entityManager.createQuery("SELECT p FROM entities.Project p " +
                        "ORDER BY p.startDate DESC ", Project.class)
                .setMaxResults(10)
                .getResultList();

        projects.stream().sorted(Comparator.comparing(Project::getName))
                .forEach(p -> {
                    output.append(String.format("Project name: %s%n" +
                            "\tProject Description: %s%n" +
                            "\tProject Start Date: %s%n" +
                            "\tProject End Date: %s%n",
                            p.getName(),
                            p.getDescription(),
                            p.getStartDate().format(formatter),
                            p.getEndDate() == null ? null : p.getEndDate().format(formatter)));
                });

        System.out.println(output.toString().trim());
    }

}
