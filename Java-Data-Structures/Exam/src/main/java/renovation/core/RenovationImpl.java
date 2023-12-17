package renovation.core;

import renovation.models.Laminate;
import renovation.models.Tile;
import renovation.models.WoodType;

import java.util.*;
import java.util.stream.Collectors;

public class RenovationImpl implements Renovation {
    private final ArrayDeque<Tile> deliveredTiles = new ArrayDeque<>();
    private final ArrayDeque<Laminate> deliveredLaminates = new ArrayDeque<>();
    private double deliveredAreaTile = 0;

    @Override
    public void deliverTile(Tile tile) {
        double area = this.calculateTitleArea(tile.width, tile.height);
        if (area > 30) {
            throw new IllegalArgumentException();
        }
        deliveredAreaTile += area;
        this.deliveredTiles.push(tile);
    }

    @Override
    public void deliverFlooring(Laminate laminate) {
        this.deliveredLaminates.push(laminate);
    }

    @Override
    public double getDeliveredTileArea() {
        return deliveredAreaTile;
    }

    @Override
    public boolean isDelivered(Laminate laminate) {
        return this.deliveredLaminates.contains(laminate);
    }

    @Override
    public void returnTile(Tile tile) {
        if (!this.deliveredTiles.contains(tile)) {
            throw new IllegalArgumentException();
        }

       if (this.deliveredTiles.removeFirstOccurrence(tile)) {
           this.deliveredAreaTile -= calculateTitleArea(tile.width, tile.height);
       }

    }

    @Override
    public void returnLaminate(Laminate laminate) {
        if (!this.deliveredLaminates.contains(laminate)) {
            throw new IllegalArgumentException();
        }

        this.deliveredLaminates.removeFirstOccurrence(laminate);
    }

    @Override
    public Collection<Laminate> getAllByWoodType(WoodType wood) {
        return this.deliveredLaminates.stream()
                .filter(l -> l.woodType.equals(wood))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Tile> getAllTilesFitting(double width, double height) {
        return this.deliveredTiles.stream()
                .filter(t -> t.width <= width && t.height <= height)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Tile> sortTilesBySize() {
        return this.deliveredTiles.stream()
                .sorted(Comparator.comparing((Tile t) -> calculateTitleArea(t.width, t.height))
                        .thenComparing(t -> t.depth))
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Laminate> layFlooring() {
        return new Iterator<Laminate>() {
            @Override
            public boolean hasNext() {
                return !deliveredLaminates.isEmpty();
            }

            @Override
            public Laminate next() {
                return deliveredLaminates.pop();
            }
        };
    }

    private double calculateTitleArea(double width, double height) {
        return width * height;
    }
}
