package nekogochan.snake.model.items.snake;

import nekogochan.dev.Mutable;
import nekogochan.math.IntVec2;
import nekogochan.snake.model.items.GameItemOperation;

import java.util.ArrayList;
import java.util.List;

public class Snake extends SnakePiece {

    protected List<SnakeBody> bodyPieces;
    protected int growTimes = 0;
    protected boolean dead = false;

    public Snake(IntVec2 pos, List<SnakeBody> bodyPieces) {
        this.bodyPieces = bodyPieces;
        this.pos = pos;
    }

    public Snake(IntVec2 pos) {
        this(pos, new ArrayList<>());
    }

    public SnakeMoveAction moveAction() {
        return SnakeMoveAction.of((actionData) -> {

            var lastHandledPiecePos = pos.clone();
            var lastHandledPieceMoveDir = moveDirection;

            pos.add(moveDirection.intVec2());

            for (var snakeBody : bodyPieces) {
                if (snakeBody.pos().equals(pos)) {
                    actionData.setEatSelf();
                    return;
                }
            }


            for (var snakeBody : bodyPieces) {
                var pos = snakeBody.pos().clone();
                var md = snakeBody.moveDirection;

                snakeBody.pos().set(lastHandledPiecePos);
                snakeBody.setMoveDirection(lastHandledPieceMoveDir);

                lastHandledPiecePos = pos;
                lastHandledPieceMoveDir = md;
            }

            if (growTimes > 0) {
                var newBody = new SnakeBody(lastHandledPiecePos);
                newBody.setMoveDirection(lastHandledPieceMoveDir);
                bodyPieces.add(newBody);
                growTimes--;
                actionData.setGrown();
            }
        });
    }

    @Mutable
    public void grow() {
        growTimes++;
    }

    public boolean dead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    @Mutable
    protected void applyOperation(GameItemOperation op) {
        op.apply(this);
    }

    public List<SnakeBody> body() {
        return bodyPieces;
    }
}
