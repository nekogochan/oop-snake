package nekogochan.snake.model.items.food;

import nekogochan.dev.Mutable;
import nekogochan.snake.model.items.snake.Snake;

public interface FoodEffect {
    void apply(@Mutable Snake snake);
}
