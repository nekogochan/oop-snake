package nekogochan.snake.model.items.snake;

import nekogochan.dev.Mutable;
import nekogochan.math.IntVec2;
import nekogochan.snake.model.items.GameItem;
import nekogochan.snake.model.items.MoveDirection;

public abstract class SnakePiece extends GameItem {
    protected MoveDirection moveDirection;

    public MoveDirection moveDirection() {
        return moveDirection;
    }

    public void setMoveDirection(MoveDirection moveDirection) {
        this.moveDirection = moveDirection;
    }
}
