package renovation.core;

import renovation.models.Laminate;
import renovation.models.Tile;
import renovation.models.WoodType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RenovationCorrectnessTests {

    private Renovation renovationService;

    @Before
    public void setup() {
        this.renovationService = new RenovationImpl();
    }

    @Test
    public void testDeliveredTileAreIncreasesWhenDeliveringTiles() {
        assertThat(this.renovationService.getDeliveredTileArea(), is(0.0));

        Tile t1 = new Tile(2, 2, 0.5);
        Tile t2 = new Tile(3, 3, 0.5);
        this.renovationService.deliverTile(t1);
        this.renovationService.deliverTile(t2);

        assertThat(this.renovationService.getDeliveredTileArea(), is(13.0));
    }

    @Test
    public void testReturnTileThrowsExceptionWhenMissing() {
        Tile t1 = new Tile(2, 2, 0.5);
        Tile t2 = new Tile(3, 3, 0.5);
        this.renovationService.deliverTile(t1);

        assertThrows(IllegalArgumentException.class, () -> this.renovationService.returnTile(t2));
    }

    @Test
    public void testGetAllByWoodType() {
        Laminate l1 = new Laminate(2.2, 0.2, WoodType.OAK);
        Laminate l2 = new Laminate(2.2, 0.3, WoodType.OAK);
        Laminate l3 = new Laminate(2.5, 0.2, WoodType.CHERRY);

        this.renovationService.deliverFlooring(l1);
        this.renovationService.deliverFlooring(l2);
        this.renovationService.deliverFlooring(l3);

        Collection<Laminate> allByWoodType = this.renovationService.getAllByWoodType(WoodType.OAK);

        assertThat(allByWoodType, hasItems(l1, l2));
    }

    @Test
    public void testTotalTileArea() {
        verifyCorrectness();
        renovationService = new RenovationImpl();

        for (int i = 0; i < 10000; i++) {
            double value = i * 0.000001;
            this.renovationService.deliverTile(new Tile(value, value, value));
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            this.renovationService.getDeliveredTileArea();
        }
        long stop = System.currentTimeMillis();

        assertTrue(stop - start < 10);
    }

    private void verifyCorrectness() {
        Tile t1 = new Tile(0.5, 0.5, 0.5);

        for (int i = 0; i < 79; i++) {
            this.renovationService.deliverTile(t1);
        }

        assertEquals(19.75, this.renovationService.getDeliveredTileArea(), 0.0);
    }

    @Test
    public void testReturnTile() {
        verifyCorrectnessTwo();
        renovationService = new RenovationImpl();

        List<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            double value = i * 0.000001;
            tiles.add(new Tile(value, value, value));

            this.renovationService.deliverTile(tiles.get(i));
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            this.renovationService.returnTile(tiles.get(i));
        }
        long stop = System.currentTimeMillis();

        assertTrue(stop - start < 150);
    }

    private void verifyCorrectnessTwo() {
        Tile t1 = new Tile(2, 2, 0.5);

        this.renovationService.deliverTile(t1);

        assertEquals(4, this.renovationService.getDeliveredTileArea(), 0.0);

        this.renovationService.returnTile(t1);

        assertEquals(0, this.renovationService.getDeliveredTileArea(), 0.0);
    }

    @Test
    public void testDeliverTile() {
        verifyCorrectnessTree();
        renovationService = new RenovationImpl();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            double value = i * 0.000001;
            this.renovationService.deliverTile(new Tile(value, value, value));
        }
        long stop = System.currentTimeMillis();

        assertTrue(stop - start < 10);
    }

    private void verifyCorrectnessTree() {
        Tile t1 = new Tile(2, 2, 0.5);

        this.renovationService.deliverTile(t1);

        assertEquals(4, this.renovationService.getDeliveredTileArea(), 0.0);
    }

}
