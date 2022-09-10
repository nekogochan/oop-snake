package nekogochan.snake.view.swing;

import nekogochan.snake.model.items.GameItemOperation;
import nekogochan.snake.model.items.GameItem;
import nekogochan.snake.model.items.food.Apple;
import nekogochan.snake.model.items.food.GoldenApple;
import nekogochan.snake.model.items.snake.SnakeBody;
import nekogochan.snake.model.items.snake.Snake;

import java.awt.*;

import static java.awt.Color.*;

public class SimpleSwingViewFieldInfoDrawGameItemOperation implements GameItemOperation {

    private final SwingViewFieldInfo view;

    public SimpleSwingViewFieldInfoDrawGameItemOperation(SwingViewFieldInfo view) {
        this.view = view;
    }

    private void setViewData(Color color, GameItem gameItem) {
        view.set(gameItem.pos().x(), gameItem.pos().y(), color);
    }

    @Override
    public void apply(Apple apple) {
        setViewData(RED, apple);
    }

    @Override
    public void apply(GoldenApple goldenApple) {
        setViewData(YELLOW, goldenApple);
    }

    @Override
    public void apply(SnakeBody snakeBody) {
        setViewData(GREEN, snakeBody);
    }

    @Override
    public void apply(Snake snake) {
        setViewData(GREEN.darker(), snake);
    }
}
