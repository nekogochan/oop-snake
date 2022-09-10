package nekogochan.snake.model.items;

import nekogochan.snake.model.items.food.Apple;
import nekogochan.snake.model.items.food.GoldenApple;
import nekogochan.snake.model.items.snake.SnakeBody;
import nekogochan.snake.model.items.snake.Snake;

public interface GameItemOperation {

    default void apply(GameItem gameItem) {
        gameItem.applyOperation(this);
    }

    void apply(Apple apple);

    void apply(SnakeBody snakeBody);

    void apply(Snake snake);

    void apply(GoldenApple goldenApple);
}
