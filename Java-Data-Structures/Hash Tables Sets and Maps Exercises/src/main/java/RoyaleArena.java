import java.util.*;
import java.util.stream.Collectors;


public class RoyaleArena implements IArena {

    private final Map<Integer, Battlecard> cardById;
    private final Map<CardType, Set<Battlecard>> cardByType;

    public  RoyaleArena() {
        this.cardById = new LinkedHashMap<>();
        this.cardByType = new HashMap<>();
    }

    @Override
    public void add(Battlecard card) {
        cardById.putIfAbsent(card.getId(), card);

        cardByType.putIfAbsent(card.getType(), new TreeSet<>(Battlecard::compareTo));
        cardByType.get(card.getType()).add(card);
    }

    @Override
    public boolean contains(Battlecard card) {
        return this.cardById.containsKey(card.getId());
    }

    @Override
    public int count() {
        return this.cardById.size();
    }

    @Override
    public void changeCardType(int id, CardType type) {
        Battlecard card = this.cardById.get(id);

        if (card == null) {
            throw new IllegalArgumentException();
        }

        card.setType(type);
    }

    @Override
    public Battlecard getById(int id) {
        Battlecard card = this.cardById.get(id);

        if (card == null) {
            throw new UnsupportedOperationException();
        }

        return card;
    }

    @Override
    public void removeById(int id) {
        Battlecard card = this.cardById.remove(id);

        if (card == null) {
            throw new UnsupportedOperationException();
        }

        this.cardByType.get(card.getType()).remove(card);
    }

    @Override
    public Iterable<Battlecard> getByCardType(CardType type) {
        Set<Battlecard> cardSet = this.cardByType.get(type);

        if (cardSet == null || cardSet.isEmpty()) {
            throw new UnsupportedOperationException();
        }

        return cardSet;
    }

    @Override
    public Iterable<Battlecard> getByTypeAndDamageRangeOrderedByDamageThenById(CardType type, int lo, int hi) {
        List<Battlecard> cards = this.cardByType
                .get(type)
                .stream()
                .filter(c -> c.getDamage() > lo && c.getDamage() < hi)
                .sorted(Battlecard::compareTo)
                .collect(Collectors.toList());

        if (cards.isEmpty()) {
            throw new UnsupportedOperationException();
        }

        return cards;
    }

    @Override
    public Iterable<Battlecard> getByCardTypeAndMaximumDamage(CardType type, double damage) {
        List<Battlecard> cards = this.cardByType
                .get(type)
                .stream()
                .filter(c -> c.getDamage() <= damage)
                .sorted(Battlecard::compareTo)
                .collect(Collectors.toList());

        if (cards.isEmpty()) {
            throw new UnsupportedOperationException();
        }

        return cards;
    }

    @Override
    public Iterable<Battlecard> getByNameOrderedBySwagDescending(String name) {
        List<Battlecard> cards = this.cardById
                .values()
                .stream()
                .filter(c -> c.getName().equals(name))
                .sorted(Comparator.comparingDouble(Battlecard::getSwag)
                        .reversed()
                        .thenComparingInt(Battlecard::getId))
                .collect(Collectors.toList());

        if (cards.isEmpty()) {
            throw new UnsupportedOperationException();
        }

        return cards;
    }

    @Override
    public Iterable<Battlecard> getByNameAndSwagRange(String name, double lo, double hi) {
        List<Battlecard> cards = this.cardById
                .values()
                .stream()
                .filter(c -> c.getName().equals(name)
                        && c.getSwag() >= lo
                        && c.getSwag() < hi)
                .sorted(Comparator.comparingDouble(Battlecard::getSwag)
                        .reversed()
                        .thenComparingInt(Battlecard::getId))
                .collect(Collectors.toList());

        if (cards.isEmpty()) {
            throw new UnsupportedOperationException();
        }

        return cards;
    }

    @Override
    public Iterable<Battlecard> getAllByNameAndSwag() {
        Map<String, Battlecard> cards = new LinkedHashMap<>();

        this.cardById
                .values()
                .forEach(c -> {
                    Battlecard card = cards.get(c.getName());

                    if (card == null) {
                        cards.put(c.getName(), c);
                    } else if (c.getSwag() > card.getSwag()) {
                        cards.put(c.getName(), c);
                    }
                });

        return cards.values();
    }

    @Override
    public Iterable<Battlecard> findFirstLeastSwag(int n) {
        if (n > this.count()) {
            throw new UnsupportedOperationException();
        }

        return this.cardById
                .values()
                .stream()
                .sorted(Comparator.comparingDouble(Battlecard::getSwag).thenComparingInt(Battlecard::getId))
                .limit(n)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Battlecard> getAllInSwagRange(double lo, double hi) {
        return this.cardById
                .values()
                .stream()
                .filter(c -> c.getSwag() >= lo && c.getSwag() <= hi)
                .sorted(Comparator.comparingDouble(Battlecard::getSwag))
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Battlecard> iterator() {
        return this.cardById.values().iterator();
    }

}
