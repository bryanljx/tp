package seedu.foodrem.testutil;

import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.item.Item;

/**
 * A utility class to help with building FoodRem objects.
 * Example usage: <br>
 * {@code FoodRem foodRem = new FoodRemBuilder().withItem("Potatoes", "Carrots").build();}
 */
public class FoodRemBuilder {
    private final FoodRem foodRem;

    public FoodRemBuilder() {
        foodRem = new FoodRem();
    }

    public FoodRemBuilder(FoodRem foodRem) {
        this.foodRem = foodRem;
    }

    /**
     * Adds a new {@code Item} to the {@code FoodRem} that we are building.
     */
    public FoodRemBuilder withItem(Item item) {
        foodRem.addItem(item);
        return this;
    }

    public FoodRem build() {
        return foodRem;
    }
}
