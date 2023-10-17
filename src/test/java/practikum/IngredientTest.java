package practikum;

import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void shouldCreateSauceIngredient() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        assertEquals(100, ingredient.getPrice(), 0);
        assertEquals("hot sauce", ingredient.getName());
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    @Test
    public void shouldCreateFillingIngredient() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "dinosaur", 200);
        assertEquals(200, ingredient.getPrice(), 0);
        assertEquals("dinosaur", ingredient.getName());
        assertEquals(IngredientType.FILLING, ingredient.getType());
    }

}

