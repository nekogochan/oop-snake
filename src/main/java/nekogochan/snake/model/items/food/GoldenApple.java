package nekogochan.snake.model.items.food;

import nekogochan.dev.Mutable;
import nekogochan.math.IntVec2;
import nekogochan.snake.model.items.GameItemOperation;

public class GoldenApple extends Food {
    private final int growTimes;
    private final int lifetime;

    public GoldenApple(int growTimes, int lifetime, IntVec2 pos) {
        this.growTimes = growTimes;
        this.pos = pos;
        this.lifetime = lifetime;
    }

    @Override
    @Mutable
    protected void applyOperation(GameItemOperation op) {
        op.apply(this);
    }

    @Override
    public FoodEffect eatenEffect() {
        return snake -> {
            for (int i = 0; i < 3; i++) {
                snake.grow();
            }
        };
    }

    @Override
    public boolean special() {
        return true;
    }
}
