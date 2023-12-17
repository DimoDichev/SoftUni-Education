package org.softuni.exam.structures;

import org.softuni.exam.entities.Airline;
import org.softuni.exam.entities.Flight;

import java.util.*;
import java.util.stream.Collectors;

public class AirlinesManagerImpl implements AirlinesManager {
    private Map<String, Airline> airlines = new TreeMap<>();
    private Map<String, List<Flight>> flightsByAirline = new TreeMap<>();
    private Map<String, Flight> flights = new TreeMap<>();
    private Map<String, Flight> completedFlights = new TreeMap<>();

    @Override
    public void addAirline(Airline airline) {
        this.airlines.put(airline.getId(), airline);
        this.flightsByAirline.put(airline.getId(), new ArrayList<>());
    }

    @Override
    public void addFlight(Airline airline, Flight flight) {
        if (!this.airlines.containsKey(airline.getId())) {
            throw new IllegalArgumentException();
        }

        this.flights.put(flight.getId(), flight);
        this.flightsByAirline.get(airline.getId()).add(flight);
        if (flight.isCompleted()) {
            completedFlights.put(flight.getId(), flight);
        }
    }

    @Override
    public boolean contains(Airline airline) {
        return this.airlines.containsKey(airline.getId());
    }

    @Override
    public boolean contains(Flight flight) {
        return this.flights.containsKey(flight.getId());
    }

    @Override
    public void deleteAirline(Airline airline) throws IllegalArgumentException {
        if (!this.airlines.containsKey(airline.getId())) {
            throw new IllegalArgumentException();
        }

        for (Flight flight : this.flightsByAirline.get(airline.getId())) {
            this.flights.remove(flight.getId());
            this.completedFlights.remove(flight.getId());
        }
        this.airlines.remove(airline.getId());
        this.flightsByAirline.remove(airline.getId());
    }

    @Override
    public Iterable<Flight> getAllFlights() {
        return this.flights.values();
    }

    @Override
    public Flight performFlight(Airline airline, Flight flight) throws IllegalArgumentException {
        if (!this.airlines.containsKey(airline.getId()) || !this.flights.containsKey(flight.getId())) {
            throw new IllegalArgumentException();
        }

        this.completedFlights.get(flight.getId()).setCompleted(true);

        return flight;
    }

    @Override
    public Iterable<Flight> getCompletedFlights() {
        return this.completedFlights.values();
    }

    @Override
    public Iterable<Flight> getFlightsOrderedByNumberThenByCompletion() {
        return this.flights.values().stream()
                .sorted(Comparator.comparing(Flight::isCompleted)
                        .thenComparing(Flight::getNumber))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Airline> getAirlinesOrderedByRatingThenByCountOfFlightsThenByName() {
        return this.airlines.values().stream()
                .sorted(Comparator.comparing(Airline::getRating).reversed()
                        .thenComparing((Airline a) -> this.flightsByAirline.get(a.getId()).size()).reversed()
                        .thenComparing(Airline::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Airline> getAirlinesWithFlightsFromOriginToDestination(String origin, String destination) {
        List<Airline> result = new ArrayList<>();

        for (Map.Entry<String, List<Flight>> airlines : flightsByAirline.entrySet()) {
            if (!airlines.getValue().isEmpty()) {
                for (Flight flight : airlines.getValue()) {
                    if (!flight.isCompleted() && flight.getOrigin().equals(origin) && flight.getDestination().equals(destination)) {
                        result.add(this.airlines.get(airlines.getKey()));
                        continue;
                    }
                }
            }
        }

        return result;
    }
}
