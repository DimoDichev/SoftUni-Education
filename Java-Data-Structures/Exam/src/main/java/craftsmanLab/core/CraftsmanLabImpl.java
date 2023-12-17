package craftsmanLab.core;

import craftsmanLab.models.ApartmentRenovation;
import craftsmanLab.models.Craftsman;

import java.util.*;
import java.util.stream.Collectors;

public class CraftsmanLabImpl implements CraftsmanLab {
    private final Map<String, ApartmentRenovation> apartments = new LinkedHashMap<>();
    private final Map<String, Craftsman> craftsmans = new LinkedHashMap<>();
    private final Map<String, Craftsman> contracts = new LinkedHashMap<>();
    private Craftsman leastProfitableCraftsman = null;

    @Override
    public void addApartment(ApartmentRenovation job) {
        if (this.apartments.containsKey(job.address)) {
            throw new IllegalArgumentException();
        }
        this.apartments.put(job.address, job);
    }

    @Override
    public void addCraftsman(Craftsman craftsman) {
        if (this.craftsmans.containsKey(craftsman.name)) {
            throw new IllegalArgumentException();
        }

        this.craftsmans.put(craftsman.name, craftsman);

        if (leastProfitableCraftsman == null) {
            leastProfitableCraftsman = craftsman;
        } else if (leastProfitableCraftsman.hourlyRate > craftsman.hourlyRate) {
            leastProfitableCraftsman = craftsman;
        }

    }

    @Override
    public boolean exists(ApartmentRenovation job) {
        return this.apartments.containsKey(job.address);
    }

    @Override
    public boolean exists(Craftsman craftsman) {
        return this.craftsmans.containsKey(craftsman.name);
    }

    @Override
    public void removeCraftsman(Craftsman craftsman) {
        if (!this.craftsmans.containsKey(craftsman.name) || this.contracts.containsValue(craftsman)) {
            throw new IllegalArgumentException();
        }

        if (leastProfitableCraftsman.equals(craftsman)) {
            leastProfitableCraftsman = findLessProfitable();
        }

        this.craftsmans.remove(craftsman.name);
    }

    @Override
    public Collection<Craftsman> getAllCraftsmen() {
        return this.craftsmans.values();
    }

    @Override
    public void assignRenovations() {
        for (ApartmentRenovation ap : this.apartments.values()) {
            if (this.contracts.containsKey(ap.address)) {
                continue;
            }

            Craftsman toAssign = findCraftsman();
            if (toAssign != null) {
                toAssign.totalEarnings = toAssign.totalEarnings + (ap.workHoursNeeded * toAssign.hourlyRate);
                this.contracts.put(ap.address, toAssign);
            } else {
                break;
            }
        }
    }

    @Override
    public Craftsman getContractor(ApartmentRenovation job) {
        if (!this.contracts.containsKey(job.address)) {
            throw new IllegalArgumentException();
        }
        return this.contracts.get(job.address);
    }

    @Override
    public Craftsman getLeastProfitable() {
        if (this.craftsmans.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return leastProfitableCraftsman;
    }

    @Override
    public Collection<ApartmentRenovation> getApartmentsByRenovationCost() {
        Map<ApartmentRenovation, Double> result = new HashMap<>();
        for (var entry : this.apartments.entrySet()) {
            if (this.contracts.containsKey(entry.getKey())) {
                result.put(entry.getValue(),
                        calculateRenovation(entry.getValue().workHoursNeeded, this.contracts.get(entry.getKey()).hourlyRate));
            } else {
                result.put(entry.getValue(), entry.getValue().workHoursNeeded);
            }
        }
        return result.entrySet().stream()
                .sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<ApartmentRenovation> getMostUrgentRenovations(int limit) {
        return this.apartments.values().stream()
                .sorted(Comparator.comparing((ApartmentRenovation a) -> a.deadline).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    private Craftsman findCraftsman() {
        return this.craftsmans.values().stream()
                .min(Comparator.comparing(c -> c.totalEarnings))
                .orElse(null);
    }

    private double calculateRenovation(double workHoursNeeded, double hourlyRate) {
        return workHoursNeeded * hourlyRate;
    }

    private Craftsman findLessProfitable() {
                return this.craftsmans.values().stream()
                .min(Comparator.comparing(c -> c.hourlyRate))
                .orElse(null);
    }

}
