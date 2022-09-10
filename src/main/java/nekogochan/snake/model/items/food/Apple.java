package nekogochan.snake.model.items.food;

import nekogochan.dev.Mutable;
import nekogochan.math.IntVec2;
import nekogochan.snake.model.items.GameItemOperation;
import nekogochan.snake.model.items.snake.Snake;

public class Apple extends Food {

    public Apple(IntVec2 pos) {
        this.pos = pos;
    }

    @Override
    @Mutable
    protected void applyOperation(GameItemOperation op) {
        op.apply(this);
    }

    @Override
    public FoodEffect eatenEffect() {
        return Snake::grow;
    }
}
