package practikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientParametrizedTest {
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientParametrizedTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Проверка ингредиентов. Тестовые данные {index} : тип = {0}, наименование = {1}, ценв = {2}")
    public static Object[][] getIngredientData() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 10.10f},
                {IngredientType.SAUCE, "sour cream", 200},
                {IngredientType.SAUCE, "chili sauce", 300},
                {IngredientType.FILLING, "cutlet", -100},
                {IngredientType.FILLING, "dinosaur", 200},
                {IngredientType.FILLING, "sausage", 0}
        };
    }

    @Test
    public void shouldCreateIngredients() {
        int priceDelta = 0;

        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), priceDelta);
        assertEquals(name, ingredient.getName());
        assertEquals(type, ingredient.getType());
    }

}
