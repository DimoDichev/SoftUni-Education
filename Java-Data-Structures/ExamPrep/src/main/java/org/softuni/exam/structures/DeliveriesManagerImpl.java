package org.softuni.exam.structures;

import org.softuni.exam.entities.Deliverer;
import org.softuni.exam.entities.Package;

import java.util.*;
import java.util.stream.Collectors;

public class DeliveriesManagerImpl implements DeliveriesManager {
    private Map<String, Deliverer> deliverers = new TreeMap<>();
    private Map<String, Package> packages = new TreeMap<>();
    private Map<String, Package> unassignetPackages = new TreeMap<>();
    private Map<String, List<Package>> packagesByDeliverer = new TreeMap<>();

    @Override
    public void addDeliverer(Deliverer deliverer) {
        this.deliverers.put(deliverer.getId(), deliverer);
        this.packagesByDeliverer.put(deliverer.getId(), new ArrayList<>());
    }

    @Override
    public void addPackage(Package _package) {
        this.packages.put(_package.getId(), _package);
        this.unassignetPackages.put(_package.getId(), _package);
    }

    @Override
    public boolean contains(Deliverer deliverer) {
        return this.deliverers.containsKey(deliverer.getId());
    }

    @Override
    public boolean contains(Package _package) {
        return this.packages.containsKey(_package.getId());
    }

    @Override
    public Iterable<Deliverer> getDeliverers() {
        return this.deliverers.values();
    }

    @Override
    public Iterable<Package> getPackages() {
        return this.packages.values();
    }

    @Override
    public void assignPackage(Deliverer deliverer, Package _package) throws IllegalArgumentException {
        if (!this.deliverers.containsKey(deliverer.getId()) || !this.packages.containsKey(_package.getId())) {
            throw new IllegalArgumentException();
        }

        this.packagesByDeliverer.get(deliverer.getId()).add(_package);
        this.unassignetPackages.remove(_package.getId());
    }

    @Override
    public Iterable<Package> getUnassignedPackages() {
        return this.unassignetPackages.values();
    }

    @Override
    public Iterable<Package> getPackagesOrderedByWeightThenByReceiver() {
        return this.packages.values().stream()
                .sorted(Comparator.comparingDouble(Package::getWeight).reversed()
                        .thenComparing(Package::getReceiver))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Deliverer> getDeliverersOrderedByCountOfPackagesThenByName() {
        return this.deliverers.values().stream()
                .sorted(Comparator.comparing((Deliverer d) -> packagesByDeliverer.get(d.getId()).size()).reversed()
                        .thenComparing(Deliverer::getName))
                .collect(Collectors.toList());
    }
}
