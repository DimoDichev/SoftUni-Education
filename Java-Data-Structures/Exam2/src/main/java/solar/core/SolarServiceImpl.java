package solar.core;

import solar.models.Inverter;
import solar.models.PVModule;

import java.util.*;
import java.util.stream.Collectors;

public class SolarServiceImpl implements SolarService {

    private Map<String, Inverter> invertersById;
    private Map<PVModule, Inverter> pvModulsById;
    private Map<PVModule, String> pvModulesByArray; //moduls -> inverterId
    private Set<String> arraysIds;
    private Map<Inverter, List<String>> invertersArraysById; //inverter -> List<ArrayId>
    private Map<Inverter, Integer> inverterModulsCount;
    private Map<Inverter, Integer> inverterProductionCapacity;
    private Map<String, List<PVModule>> arrays;

    public SolarServiceImpl() {
        invertersById = new HashMap<>();
        pvModulsById = new LinkedHashMap<>();
        pvModulesByArray = new HashMap<>();
        arraysIds = new HashSet<>();
        invertersArraysById = new HashMap<>();
        arrays = new HashMap<>();
        inverterModulsCount = new HashMap<>();
        inverterProductionCapacity = new HashMap<>();
    }

    @Override
    public void addInverter(Inverter inverter) {
        if (containsInverter(inverter.id)) {
            throw new IllegalArgumentException();
        }
        invertersById.put(inverter.id, inverter);
        invertersArraysById.put(inverter, new ArrayList<>());
        inverterModulsCount.put(inverter, 0);
        inverterProductionCapacity.put(inverter, 0);
    }

    @Override
    public void addArray(Inverter inverter, String arrayId) {
        Inverter inverterToAdd = invertersById.get(inverter.id);

        if (inverterToAdd == null || arraysIds.contains(arrayId)) {
            throw new IllegalArgumentException();
        }

        List<String> inverterArrays = invertersArraysById.get(inverter);

        if (inverterArrays.size() >= inverter.maxPvArraysConnected) {
            throw new IllegalArgumentException();
        }

        invertersArraysById.get(inverter).add(arrayId);
        arrays.put(arrayId, new ArrayList<>());
        arraysIds.add(arrayId);
    }

    @Override
    public void addPanel(Inverter inverter, String arrayId, PVModule pvModule) {
        if (!invertersById.containsKey(inverter.id) || pvModulsById.containsKey(pvModule)) {
            throw new IllegalArgumentException();
        }
        List<String> inverterArraysIds = invertersArraysById.get(inverter);

        if (!inverterArraysIds.contains(arrayId)) {
            throw new IllegalArgumentException();
        }

        arrays.get(arrayId).add(pvModule);
        pvModulsById.put(pvModule, inverter);
        pvModulesByArray.put(pvModule, arrayId);
        inverterModulsCount.put(inverter, inverterModulsCount.get(inverter) + 1);
        inverterProductionCapacity.put(inverter, inverterProductionCapacity.get(inverter) + pvModule.maxWattProduction);
    }

    @Override
    public boolean containsInverter(String id) {
        return invertersById.containsKey(id);
    }

    @Override
    public boolean isPanelConnected(PVModule pvModule) {
        return pvModulsById.containsKey(pvModule);
    }

    @Override
    public Inverter getInverterByPanel(PVModule pvModule) {
        return pvModulsById.get(pvModule);
    }

    @Override
    public void replaceModule(PVModule oldModule, PVModule newModule) {
        if (!pvModulsById.containsKey(oldModule) || pvModulsById.containsKey(newModule)) {
            throw new IllegalArgumentException();
        }

        Inverter inverter = pvModulsById.get(oldModule);
        String arrayId = pvModulesByArray.get(oldModule);

        pvModulsById.remove(oldModule);
        pvModulsById.put(newModule, inverter);

        pvModulesByArray.remove(oldModule);
        pvModulesByArray.put(newModule, arrayId);

        arrays.get(arrayId).remove(oldModule);
        arrays.get(arrayId).add(newModule);

        int difference = newModule.maxWattProduction - oldModule.maxWattProduction;
        inverterProductionCapacity.put(inverter, inverterProductionCapacity.get(inverter) + difference);
    }

    @Override
    public Collection<Inverter> getByProductionCapacity() {
        return inverterProductionCapacity
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Inverter> getByNumberOfPVModulesConnected() {
        return inverterModulsCount
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<PVModule> getByWattProduction() {
        return pvModulsById
                .keySet()
                .stream()
                .sorted(Comparator.comparingInt((PVModule m) -> m.maxWattProduction).reversed())
                .collect(Collectors.toList());
    }
}
