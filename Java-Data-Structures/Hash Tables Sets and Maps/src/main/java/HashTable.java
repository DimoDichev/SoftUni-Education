import java.util.*;

public class HashTable<K, V> implements Iterable<KeyValue<K, V>> {

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTORY = 0.80;

    private LinkedList<KeyValue<K, V>>[] slots;
    private int count;
    private int capacity;

    public HashTable() {
        this(INITIAL_CAPACITY);
    }

    public HashTable(int capacity) {
        this.slots = new LinkedList[capacity];
        this.capacity = capacity;
    }

    public void add(K key, V value) {
        this.growIfNeeded();
        int slotNumber = this.findSlotNumber(key);

        if (this.slots[slotNumber] == null) {
            this.slots[slotNumber] = new LinkedList<>();
        }

        for (KeyValue<K, V> element : slots[slotNumber]) {
            if (element.getKey().equals(key)) {
                throw new IllegalArgumentException("Key already exist");
            }
        }

        KeyValue<K, V> kvp = new KeyValue<>(key, value);
        this.slots[slotNumber].addLast(kvp);
        this.count++;
    }

    private int findSlotNumber(K key) {
       return Math.abs(key.hashCode()) % this.slots.length;
    }

    private void growIfNeeded() {
        if ((double) (this.size() + 1) / this.capacity > LOAD_FACTORY) {
            this.grow();
        }
    }

    private void grow() {
        this.capacity *= 2;
        HashTable<K, V> newHashTable = new HashTable<>(this.capacity);

        for (LinkedList<KeyValue<K, V>> element : this.slots) {
            if (element != null) {
                for (KeyValue<K, V> keyValue : element) {
                    newHashTable.add(keyValue.getKey(), keyValue.getValue());
                }
            }
        }

        this.slots = newHashTable.slots;
        this.count = newHashTable.count;
    }

    public int size() {
        return this.count;
    }

    public int capacity() {
        return this.capacity;
    }

    public boolean addOrReplace(K key, V value) {
        int slotNumber = this.findSlotNumber(key);

        if (this.slots[slotNumber] == null) {
            this.slots[slotNumber] = new LinkedList<>();
        }

        for (KeyValue<K, V> element : slots[slotNumber]) {
            if (element.getKey().equals(key)) {
                element.setValue(value);
                return true;
            }
        }

        this.growIfNeeded();

        KeyValue<K, V> kvp = new KeyValue<>(key, value);
        this.slots[slotNumber].addLast(kvp);
        this.count++;

        return true;
    }

    public V get(K key) {
        KeyValue<K, V> element = this.find(key);

        if (element == null) {
            throw new IllegalArgumentException();
        }

        return element.getValue();
    }

    public KeyValue<K, V> find(K key) {
        int slotNumber = findSlotNumber(key);
        LinkedList<KeyValue<K, V>> elements = this.slots[slotNumber];

        if (elements != null) {
            for (KeyValue<K, V> element : elements) {
                if (element.getKey().equals(key)) {
                    return element;
                }
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        return this.find(key) != null;
    }

    public boolean remove(K key) {
        int slotNumber = findSlotNumber(key);
        LinkedList<KeyValue<K, V>> slot = this.slots[slotNumber];

        if (this.slots[slotNumber] != null) {
            for (KeyValue<K, V> kvp : slot) {
                if (kvp.getKey().equals(key)) {
                    slot.remove(kvp);
                    this.count--;
                    return true;
                }
            }
        }

        return false;
    }

    public void clear() {
        this.count = 0;
        this.capacity = INITIAL_CAPACITY;
        this.slots = new LinkedList[capacity];
    }

    public Iterable<K> keys() {
        List<K> keys = new ArrayList<>();

        for (LinkedList<KeyValue<K, V>> slot : this.slots) {
            if (slot != null) {
                for (KeyValue<K, V> kvp : slot) {
                    keys.add(kvp.getKey());
                }
            }
        }

        return keys;
    }

    public Iterable<V> values() {
        List<V> values = new ArrayList<>();

        for (LinkedList<KeyValue<K, V>> slot : this.slots) {
            if (slot != null) {
                for (KeyValue<K, V> kvp : slot) {
                    values.add(kvp.getValue());
                }
            }
        }

        return values;
    }

    @Override
    public Iterator<KeyValue<K, V>> iterator() {
        return new Iterator<KeyValue<K, V>>() {
            Deque<KeyValue<K, V>> pairs = getPairs();

            @Override
            public boolean hasNext() {
                return !pairs.isEmpty();
            }

            @Override
            public KeyValue<K, V> next() {
                return pairs.peek();
            }
        };
    }

    private  Deque<KeyValue<K, V>> getPairs() {
        Deque<KeyValue<K, V>> pairs = new ArrayDeque<>();

        for (LinkedList<KeyValue<K, V>> slot : this.slots) {
            if (slot != null) {
                pairs.addAll(slot);
            }
        }

        return pairs;
    }
}
