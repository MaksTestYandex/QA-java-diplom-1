package practikum;

import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void shouldCreateSauceIngredient() {
        int expectedPriceDelta = 0;
        float expectedIngredientPrice = 100;

        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        assertEquals(expectedIngredientPrice, ingredient.getPrice(), expectedPriceDelta);
        assertEquals("hot sauce", ingredient.getName());
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    @Test
    public void shouldCreateFillingIngredient() {
        int expectedPriceDelta = 0;
        float expectedIngredientPrice = 200;

        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "dinosaur", 200);
        assertEquals(expectedIngredientPrice, ingredient.getPrice(), expectedPriceDelta);
        assertEquals("dinosaur", ingredient.getName());
        assertEquals(IngredientType.FILLING, ingredient.getType());
    }

}

