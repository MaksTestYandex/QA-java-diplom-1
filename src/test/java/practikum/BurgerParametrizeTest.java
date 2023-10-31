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

    private final float expectedBurgerPrice;

    private final Bun bun = Mockito.mock();

    private final Ingredient ingredient = Mockito.mock();

    public BurgerParametrizeTest(float bunPrice, float ingredientPrice, float expectedBurgerPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedBurgerPrice = expectedBurgerPrice;
    }

    @Parameterized.Parameters(name = "Проверка стоимости бургера. Тестовые данные {index}: цена булочки = {0}, цена ингредиента = {1}, ожидаемая цена бургера = {2}")
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
        int expectedPriceDelta = 0;

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);

        assertEquals(expectedBurgerPrice, burger.getPrice(), expectedPriceDelta);
    }

}
