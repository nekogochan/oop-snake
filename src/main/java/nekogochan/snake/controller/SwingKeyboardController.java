package nekogochan.snake.controller;

import static java.util.Arrays.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class SwingKeyboardController implements Controller {

    private KeyInput left, up, right, down, enter;

    public SwingKeyboardController() {
        left = new KeyInput.Exclusive(37, () -> up, () -> right, () -> down);
        up = new KeyInput.Exclusive(38, () -> left, () -> right, () -> down);
        right = new KeyInput.Exclusive(39, () -> left, () -> up, () -> down);
        down = new KeyInput.Exclusive(40, () -> left, () -> up, () -> right);
        enter = new KeyInput(10);
    }


    private Stream<KeyInput> all() {
        return Stream.of(left, up, right, down, enter);
    }

    private Optional<KeyInput> keyByCode(int code) {
        return all().filter(key -> key.code == code).findAny();
    }

    @Override
    public KeyInput left() {
        return left;
    }

    @Override
    public KeyInput up() {
        return up;
    }

    @Override
    public KeyInput right() {
        return right;
    }

    @Override
    public KeyInput down() {
        return down;
    }

    @Override
    public KeyInput enter() {
        return enter;
    }

    @Override
    public void resetAll() {
        all().forEach(key -> key.active = false);
    }

    public void applyTo(JFrame frame) {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // ignore
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyByCode(e.getKeyCode()).ifPresent(KeyInput::setActive);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // ignore
            }
        });
    }

    public static class KeyInput implements Controller.Key {
        private boolean active = false;
        private final int code;

        KeyInput(int code) {
            this.code = code;
        }

        public boolean active() {
            return active;
        }

        protected void setActive() {
            active = true;
        }

        protected void setInactive() {
            active = false;
        }

        public static class Exclusive extends KeyInput {
            private final Supplier<KeyInput>[] exclusives;

            Exclusive(int code, Supplier<KeyInput>... exclusives) {
                super(code);
                this.exclusives = exclusives;
            }

            @Override
            protected void setActive() {
                super.setActive();
                stream(exclusives).map(Supplier::get).forEach(KeyInput::setInactive);
            }
        }
    }
}
