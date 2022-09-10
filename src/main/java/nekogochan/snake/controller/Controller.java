package nekogochan.snake.controller;

import nekogochan.dev.Mutable;

public interface Controller {

    Key left();

    Key up();

    Key right();

    Key down();

    Key enter();

    @Mutable
    void resetAll();

    interface Key {
        boolean active();
    }
}
