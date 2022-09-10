package nekogochan.snake.model.items.food;

import nekogochan.snake.model.items.GameItem;

public abstract class Food extends GameItem {
    abstract public FoodEffect eatenEffect();

    public boolean special() {
        return false;
    }
}
