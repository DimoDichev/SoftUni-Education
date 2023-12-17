package mlm.core;

import mlm.models.Seller;

import java.util.*;
import java.util.stream.Collectors;

public class MLMServiceImpl implements MLMService {

    private Set<Seller> sellers;
    private Map<Seller, Integer> sellersBySells;
    private Map<Seller, Set<Seller>> sellersByParent; // Parent -> Hires
    private Map<Seller, Seller> sellerParent; // Hire -> Parent

    public MLMServiceImpl() {
        sellers = new HashSet<>();
        sellersBySells = new HashMap<>();
        sellersByParent = new HashMap<>();
        sellerParent = new HashMap<>();
    }

    @Override
    public void addSeller(Seller seller) {
        if (exists(seller)) {
            throw new IllegalArgumentException();
        }
        sellers.add(seller);
        sellersByParent.put(seller, new HashSet<>());
        sellersBySells.put(seller, 0);
    }

    @Override
    public void hireSeller(Seller parent, Seller newHire) {
        if (!sellers.contains(parent) || sellers.contains(newHire)) {
            throw new IllegalArgumentException();
        }

        sellersByParent.get(parent).add(newHire);
        sellersByParent.put(newHire, new HashSet<>());
        sellers.add(newHire);
        sellerParent.put(newHire, parent);
        sellersBySells.put(newHire, 0);
    }

    @Override
    public boolean exists(Seller seller) {
        return sellers.contains(seller);
    }

    @Override
    public void fire(Seller seller) {
        Seller parent = sellerParent.remove(seller);
        Set<Seller> children = sellersByParent.remove(seller);
        sellersBySells.remove(seller);

        sellers.remove(seller);
        if (children != null) {
            sellersByParent.get(parent).addAll(children);
        }
    }

    @Override
    public void makeSale(Seller seller, int amount) {
        Seller parent = sellerParent.get(seller);
        int leftAmount = amount;

        while (parent != null) {
            int amountToAdd = amount * 5 / 100;
            leftAmount -= amountToAdd;
            parent.earnings = parent.earnings + amountToAdd;

            parent = sellerParent.get(parent);
        }

        seller.earnings = seller.earnings + leftAmount;
        sellersBySells.put(seller, sellersBySells.get(seller) + 1);
    }

    @Override
    public Collection<Seller> getByProfits() {
        return sellers
                .stream()
                .sorted(Comparator.comparingInt((Seller s) -> s.earnings).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Seller> getByEmployeeCount() {
        return null;
    }

    @Override
    public Collection<Seller> getByTotalSalesMade() {
        List<Seller> collect = sellersBySells
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Collections.reverse(collect);

        return collect;
    }
}
