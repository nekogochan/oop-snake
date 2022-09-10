package nekogochan.snake.model.items.snake;

import nekogochan.dev.Mutable;

import java.util.function.Consumer;

public abstract class SnakeMoveAction {

    private boolean grown = false;
    private boolean eatSelf = false;

    public boolean grown() {
        return grown;
    }

    public boolean eatSelf() {
        return eatSelf;
    }

    @Mutable
    public abstract void perform();

    public static SnakeMoveAction of(Consumer<SnakeMoveAction> performMethod) {
        return new SnakeMoveAction() {
            @Override
            public void perform() {
                performMethod.accept(this);
            }
        };
    }

    protected void setGrown() {
        this.grown = true;
    }

    protected void setEatSelf() {
        this.eatSelf = true;
    }
}
