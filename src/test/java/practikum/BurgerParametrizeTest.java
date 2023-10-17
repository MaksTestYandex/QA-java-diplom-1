package practikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParametrizeTest {

    private final float bunPrice;
    private final float ingredientPrice;

    private final float burgerPrice;

    private final Bun bun = Mockito.mock();

    private final Ingredient ingredient = Mockito.mock();

    public BurgerParametrizeTest(float bunPrice, float ingredientPrice, float burgerPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.burgerPrice = burgerPrice;
    }

    @Parameterized.Parameters
    public static Object[][] getIngredientData() {
        return new Object[][]{
                {1, 2, 4},
                {0, 0, 0},
                {-1, 2, 0},
                {0.1f, 0.2f, 0.4f}
        };
    }

    @Test
    public void shouldGetPrice() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);

        assertEquals(burgerPrice, burger.getPrice(), 0);
    }

}
