package nekogochan.snake.model;

import nekogochan.dev.Mutable;
import nekogochan.math.IntVec2;
import nekogochan.snake.model.field.Field;
import nekogochan.snake.model.items.MoveDirection;
import nekogochan.snake.model.items.food.Apple;
import nekogochan.snake.model.items.food.Food;
import nekogochan.snake.model.items.food.GoldenApple;
import nekogochan.snake.model.items.snake.Snake;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.Math.random;
import static nekogochan.snake.model.SnakeModel.State.*;

public class SnakeModel {
    private final Field field;
    private final Snake snake;
    private State state = NotStarted;

    public SnakeModel(Field field, Snake snake) {
        this.field = field;
        this.snake = snake;
        this.field.addItem(snake);
    }

    public SnakeModel(Field field) {
        this(field, new Snake(new IntVec2()));
    }

    public Field field() {
        return field;
    }

    @Mutable
    public void setSnakeMoveDirection(MoveDirection moveDirection) {
        if (snake.moveDirection() != moveDirection.opposite()) {
            snake.setMoveDirection(moveDirection);
        }
    }

    @Mutable
    public void start() {
        field.clear();
        field.addItem(snake);
        snake.pos().set(new IntVec2(field.width() / 2, field.height() / 2));
        for (int i = 0; i < 3; i++) {
            addFoodWithRandomPos(Apple::new);
        }
        snake.setMoveDirection(MoveDirection.Right);
        state = InProgress;
    }

    @Mutable
    public void act() {
        if (state != InProgress) {
            throw new IllegalStateException("Can't act in non progress state. Current state %s".formatted(state));
        }

        var snakeMoveAction = snake.moveAction();
        snakeMoveAction.perform();

        if (snakeMoveAction.eatSelf() || !field.inBounds(snake.pos())) {
            snake.setDead(true);
        }
        if (snakeMoveAction.grown()) {
            field.addItem(snake.body().get(snake.body().size() - 1));
        }

        ifSnakeEatSomething(food -> {
            field.removeItem(food);
            food.eatenEffect().apply(snake);
            if (!food.special()) {
                addFoodWithRandomPos(Apple::new);
                if (random() > 0.8) {
                    addFoodWithRandomPos(pos -> new GoldenApple(3, 20, pos));
                }
            }
        });

        if (snake.dead()) {
            state = SnakeDied;
        }
    }

    @Mutable
    private void addFoodWithRandomPos(Function<IntVec2, Food> constructor) {
        if (!field.haveFreeSpace()) {
            return;
        }
        field.randomFreePos().map(constructor).ifPresent(field::addItem);
    }

    private void ifSnakeEatSomething(Consumer<Food> then) {
        field.items().filter(Food.class::isInstance)
                .map(Food.class::cast)
                .filter(x -> x.pos().equals(snake.pos()))
                .findAny()
                .ifPresent(then);
    }

    public State state() {
        return state;
    }

    public enum State {
        NotStarted,
        InProgress,
        SnakeDied,
    }
}
