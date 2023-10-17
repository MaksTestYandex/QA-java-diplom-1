package practikum;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import praktikum.Bun;

public class BunTest {

    @Test
    public void shouldCreateBun() {
        Bun bun = new Bun("black bun", 100);
        assertEquals("black bun", bun.getName());
        assertEquals(100, bun.getPrice(), 0);
    }

}
