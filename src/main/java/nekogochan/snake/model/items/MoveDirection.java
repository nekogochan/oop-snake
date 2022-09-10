package nekogochan.snake.model.items;

import nekogochan.math.IntVec2;

public enum MoveDirection {
    Left,
    Up,
    Right,
    Down;

    public IntVec2 intVec2() {
        return switch (this) {
            case Left -> new IntVec2(-1, 0);
            case Up -> new IntVec2(0, 1);
            case Right -> new IntVec2(1, 0);
            case Down -> new IntVec2(0, -1);
        };
    }

    public MoveDirection opposite() {
        return switch (this) {
            case Left -> Right;
            case Up -> Down;
            case Right -> Left;
            case Down -> Up;
        };
    }
}
