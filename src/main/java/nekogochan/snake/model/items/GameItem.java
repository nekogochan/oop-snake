package nekogochan.snake.model.items;

import nekogochan.dev.Mutable;
import nekogochan.math.IntVec2;

public abstract class GameItem {
    protected IntVec2 pos;

    public IntVec2 pos() {
        return pos;
    }

    @Mutable
    protected abstract void applyOperation(GameItemOperation op);
}
