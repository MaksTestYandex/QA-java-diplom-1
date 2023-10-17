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
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);

        assertEquals(2, burger.ingredients.size());
        assertThat(burger.ingredients, contains(ingredient, anotherIngredient));
    }

    @Test
    public void shouldRemoveFirstIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertThat(burger.ingredients, contains(anotherIngredient));
    }

    @Test
    public void shouldRemoveMiddleIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);
        burger.addIngredient(anotherOneIngredient);

        burger.removeIngredient(1);

        assertEquals(2, burger.ingredients.size());
        assertThat(burger.ingredients, contains(ingredient, anotherOneIngredient));
    }

    @Test
    public void shouldNotRemoveNotExistIngredient() {
        Burger burger = new Burger();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.removeIngredient(0);
        });
    }

    @Test
    public void shouldMoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);
        burger.addIngredient(anotherOneIngredient);

        burger.moveIngredient(0, 2);

        assertEquals(3, burger.ingredients.size());
        assertThat(burger.ingredients, contains(anotherIngredient, anotherOneIngredient, ingredient));
    }

    @Test
    public void shouldMoveIngredientReverse() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);
        burger.addIngredient(anotherOneIngredient);

        burger.moveIngredient(2, 1);

        assertEquals(3, burger.ingredients.size());
        assertThat(burger.ingredients, contains(ingredient, anotherOneIngredient, anotherIngredient));
    }

    @Test
    public void shouldNotMoveNotExistIngredient() {
        Burger burger = new Burger();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.moveIngredient(99, 0);
        });
    }

    @Test
    public void shouldNotMoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.moveIngredient(0, 99);
        });
    }

    @Test
    public void shouldGetPrice() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);

        when(bun.getPrice()).thenReturn(10f);
        when(ingredient.getPrice()).thenReturn(5f);
        when(anotherIngredient.getPrice()).thenReturn(7f);

        assertEquals(32, burger.getPrice(), 0);
    }

    @Test
    public void shouldGetPriceWithoutIngredient() {
        Burger burger = new Burger();
        burger.setBuns(bun);

        when(bun.getPrice()).thenReturn(10f);

        assertEquals(20, burger.getPrice(), 0);
    }

    @Test
    public void shouldNotGetPriceWithoutBun() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        assertThrows(NullPointerException.class, burger::getPrice);
    }

    @Test
    public void shouldGetReceipt() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        when(bun.getPrice()).thenReturn(10f);
        when(bun.getName()).thenReturn("testBun");

        when(ingredient.getPrice()).thenReturn(5f);
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
        Burger burger = new Burger();
        burger.setBuns(bun);

        when(bun.getPrice()).thenReturn(10f);
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
