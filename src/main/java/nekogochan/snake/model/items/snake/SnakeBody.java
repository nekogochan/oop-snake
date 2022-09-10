package nekogochan.snake.model.items.snake;

import nekogochan.math.IntVec2;
import nekogochan.snake.model.items.GameItemOperation;

public class SnakeBody extends SnakePiece {

    public SnakeBody(IntVec2 pos) {
        this.pos = pos;
    }

    @Override
    protected void applyOperation(GameItemOperation op) {
        op.apply(this);
    }
}
