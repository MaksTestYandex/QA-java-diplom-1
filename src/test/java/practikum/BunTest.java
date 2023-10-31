package practikum;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import praktikum.Bun;

public class BunTest {

    @Test
    public void shouldCreateBun() {
        int expectedPriceDelta = 0;
        int expectedPrice = 100;

        Bun bun = new Bun("black bun", 100);
        assertEquals("black bun", bun.getName());
        assertEquals(expectedPrice, bun.getPrice(), expectedPriceDelta);
    }

}
