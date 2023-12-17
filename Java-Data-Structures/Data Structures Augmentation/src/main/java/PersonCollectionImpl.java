import java.util.*;
import java.util.stream.Collectors;

public class PersonCollectionImpl implements PersonCollection {

    private Map<String, Person> personByEmail;
    private Map<String, Set<Person>> personByDomain;
    private Map<String, Set<Person>> personByTown;
    private Map<Integer, Set<Person>> personByAge;

    public PersonCollectionImpl() {
        this.personByEmail = new HashMap<>();
        this.personByDomain = new HashMap<>();
        this.personByTown = new HashMap<>();
        this.personByAge = new HashMap<>();
    }

    @Override
    public boolean add(String email, String name, int age, String town) {
        if (this.personByEmail.get(email) != null) {
            return false;
        }

        Person toAdd = new Person(email, name, age, town);
        String domain = getDomainName(toAdd.getEmail());

        this.personByEmail.put(email, toAdd);

        this.personByDomain.putIfAbsent(domain, new TreeSet<>());
        this.personByDomain.get(domain).add(toAdd);

        this.personByTown.putIfAbsent(town, new TreeSet<>());
        this.personByTown.get(town).add(toAdd);

        this.personByAge.putIfAbsent(age, new TreeSet<>());
        this.personByAge.get(age).add(toAdd);

        return true;
    }

    @Override
    public int getCount() {
        return this.personByEmail.size();
    }

    @Override
    public boolean delete(String email) {
        Person removed = this.personByEmail.remove(email);

        if (removed == null) {
            return false;
        }

        this.personByDomain.get(getDomainName(removed.getEmail())).remove(removed);
        this.personByTown.get(removed.getTown()).remove(removed);
        this.personByAge.get(removed.getAge()).remove(removed);

        return true;
    }

    @Override
    public Person find(String email) {
        return this.personByEmail.get(email);
    }

    @Override
    public Iterable<Person> findAll(String emailDomain) {
        Set<Person> result = this.personByDomain.get(emailDomain);

        return result != null ? result : new ArrayList<>();
    }

    @Override
    public Iterable<Person> findAll(String name, String town) {
        Set<Person> result = this.personByTown
                .get(town);


        return result != null ? result : new ArrayList<>();
    }

    @Override
    public Iterable<Person> findAll(int startAge, int endAge) {
        Set<Person> result = new HashSet<>();

        for (Map.Entry<Integer, Set<Person>> entry : this.personByAge.entrySet()) {
            if (entry.getKey() >= startAge && entry.getKey() <= endAge) {
                result.addAll(entry.getValue());
            }
        }

        return result.stream()
                .sorted(Comparator.comparingInt(Person::getAge)
                        .thenComparing(Person::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> findAll(int startAge, int endAge, String town) {
        Set<Person> result = this.personByTown
                .get(town);

        if (result == null) {
            return new ArrayList<>();
        }

        return result
                .stream()
                .filter(person -> person.getAge() >= startAge && person.getAge() <= endAge)
                .sorted(Comparator.comparingInt(Person::getAge))
                .collect(Collectors.toList());
    }

    private String getDomainName(String email) {
        return email.substring(email.indexOf("@") + 1);
    }
}
