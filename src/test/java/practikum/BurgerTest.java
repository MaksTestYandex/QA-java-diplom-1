package practikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Bun bun;

    @Mock
    private Bun anotherBun;

    @Mock
    private Ingredient ingredient;

    @Mock
    private Ingredient anotherIngredient;

    @Mock
    private Ingredient anotherOneIngredient;

    @Test
    public void shouldSetBun() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void shouldReSetBun() {
        Burger burger = new Burger();
        burger.setBuns(bun);

        burger.setBuns(anotherBun);
        assertNotEquals(bun, burger.bun);
        assertEquals(anotherBun, burger.bun);
    }

    @Test
    public void shouldAddIngredients() {
        int expectedIngredientsListSize = 2;

        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);

        assertEquals(expectedIngredientsListSize, burger.ingredients.size());
        assertThat(burger.ingredients, contains(ingredient, anotherIngredient));
    }

    @Test
    public void shouldRemoveFirstIngredient() {
        int ingredientIndex = 0;
        int expectedIngredientsListSize = 1;

        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);
        burger.removeIngredient(ingredientIndex);
        assertEquals(expectedIngredientsListSize, burger.ingredients.size());
        assertThat(burger.ingredients, contains(anotherIngredient));
    }

    @Test
    public void shouldRemoveMiddleIngredient() {
        int ingredientIndex = 1;
        int expectedIngredientsListSize = 2;

        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);
        burger.addIngredient(anotherOneIngredient);
        burger.removeIngredient(ingredientIndex);
        assertEquals(expectedIngredientsListSize, burger.ingredients.size());
        assertThat(burger.ingredients, contains(ingredient, anotherOneIngredient));
    }

    @Test
    public void shouldNotRemoveNotExistIngredient() {
        int ingredientIndex = 0;

        Burger burger = new Burger();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.removeIngredient(ingredientIndex);
        });
    }

    @Test
    public void shouldMoveIngredient() {
        int ingredientIndex = 0;
        int ingredientNewIndex = 2;
        int expectedIngredientsListSize = 3;

        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);
        burger.addIngredient(anotherOneIngredient);
        burger.moveIngredient(ingredientIndex, ingredientNewIndex);

        assertEquals(expectedIngredientsListSize, burger.ingredients.size());
        assertThat(burger.ingredients, contains(anotherIngredient, anotherOneIngredient, ingredient));
    }

    @Test
    public void shouldMoveIngredientReverse() {
        int ingredientIndex = 2;
        int ingredientNewIndex = 1;
        int expectedIngredientsListSize = 3;

        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);
        burger.addIngredient(anotherOneIngredient);

        burger.moveIngredient(ingredientIndex, ingredientNewIndex);

        assertEquals(expectedIngredientsListSize, burger.ingredients.size());
        assertThat(burger.ingredients, contains(ingredient, anotherOneIngredient, anotherIngredient));
    }

    @Test
    public void shouldNotMoveNotExistIngredient() {
        int ingredientIndex = 99;
        int ingredientNewIndex = 0;

        Burger burger = new Burger();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.moveIngredient(ingredientIndex, ingredientNewIndex);
        });
    }

    @Test
    public void shouldNotMoveIngredient() {
        int ingredientIndex = 0;
        int ingredientNewIndex = 99;

        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.moveIngredient(ingredientIndex, ingredientNewIndex);
        });
    }

    @Test
    public void shouldGetPrice() {
        float bunPrice = 10f;
        float ingredientPrice = 5f;
        float anotherIngredientPrice = 7f;
        float expectedBurgerPrice = 32;
        int expectedPriceDelta = 0;

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);

        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);
        when(anotherIngredient.getPrice()).thenReturn(anotherIngredientPrice);

        assertEquals(expectedBurgerPrice, burger.getPrice(), expectedPriceDelta);
    }

    @Test
    public void shouldGetPriceWithoutIngredient() {
        float bunPrice = 10f;
        float expectedBurgerPrice = 20;
        int expectedPriceDelta = 0;

        Burger burger = new Burger();
        burger.setBuns(bun);

        when(bun.getPrice()).thenReturn(bunPrice);

        assertEquals(expectedBurgerPrice, burger.getPrice(), expectedPriceDelta);
    }

    @Test
    public void shouldNotGetPriceWithoutBun() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        assertThrows(NullPointerException.class, burger::getPrice);
    }

    @Test
    public void shouldGetReceipt() {
        float bunPrice = 10f;
        float ingredientPrice = 5f;

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        when(bun.getPrice()).thenReturn(bunPrice);
        when(bun.getName()).thenReturn("testBun");

        when(ingredient.getPrice()).thenReturn(ingredientPrice);
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("testIngredient");

        String result = burger.getReceipt();
        assertNotNull(result);
        assertTrue(result.contains("testBun"));
        assertTrue(result.contains("testIngredient"));
        assertTrue(result.contains("sauce"));
        assertTrue(result.contains("Price: 25,000000"));
    }

    @Test
    public void shouldGetReceiptWithoutIngredient() {
        float bunPrice = 10f;

        Burger burger = new Burger();
        burger.setBuns(bun);

        when(bun.getPrice()).thenReturn(bunPrice);
        when(bun.getName()).thenReturn("testBun");

        String result = burger.getReceipt();
        assertNotNull(result);
        assertTrue(result.contains("testBun"));
        assertTrue(result.contains("Price: 20,000000"));
    }

    @Test
    public void shouldNotGetReceiptWithoutBun() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        assertThrows(NullPointerException.class, burger::getReceipt);
    }

}
