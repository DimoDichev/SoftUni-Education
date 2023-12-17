import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PersonCollectionSlowImpl implements PersonCollection {

    private List<Person> persons;

    public PersonCollectionSlowImpl() {
        this.persons = new ArrayList<>();
    }

    @Override
    public boolean add(String email, String name, int age, String town) {
        for (Person person : this.persons) {
            if (person.getEmail().equals(email)) {
                return false;
            }
        }

        Person person = new Person(email, name, age, town);
        return persons.add(person);
    }

    @Override
    public int getCount() {
        return this.persons.size();
    }

    @Override
    public boolean delete(String email) {
        return this.persons.removeIf(person -> person.getEmail().equals(email));
    }

    @Override
    public Person find(String email) {
        return this.persons
                .stream()
                .filter(person -> person.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Iterable<Person> findAll(String emailDomain) {
        return this.persons
                .stream()
                .filter(person -> person.getEmail().endsWith("@" + emailDomain))
                .sorted(Comparator.comparing(Person::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> findAll(String name, String town) {
        return this.persons
                .stream()
                .filter(persons ->persons.getName().equals(name)
                        && persons.getTown().equals(town))
                .sorted(Comparator.comparing(Person::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> findAll(int startAge, int endAge) {
        return this.persons
                .stream()
                .filter(persons -> persons.getAge() >= startAge && persons.getAge() <=endAge)
                .sorted(Comparator.comparingInt(Person::getAge)
                        .thenComparing(Comparator.comparing(Person::getEmail)))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> findAll(int startAge, int endAge, String town) {
        return this.persons
                .stream()
                .filter(person -> person.getTown().equals(town)
                        && person.getAge() >= startAge
                        && person.getAge() <= endAge)
                .collect(Collectors.toList());
    }
}
