import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;    
    @BeforeEach
    public void set_before_each(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Penne Pasta", 239);
        restaurant.selectItem("Sweet corn soup");
    }

    @Test
    public void after_selecting_an_item_the_length_of_list_should_increase_by_1() throws itemNotFoundException{
        int initialSelectionSize = restaurant.selectedItemsFromMenu();
        restaurant.selectItem("Vegetable lasagne");
        assertEquals(initialSelectionSize+1,restaurant.selectedItemsFromMenu());

    }

    //>>>>>>>>>>>>>>>>>>>>>>Customer: Selected menu<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void display_the_sum_of_all_items_selected_from_the_menu() throws itemNotFoundException {
        restaurant.selectItem("Vegetable lasagne");
        assertEquals(restaurant.selectedPrice(), 388);
    }

    //<<<<<<<<<<<<<<<<<<<<Customer: Selected menu>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        //tested this at 22:49 
        assertFalse(restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
