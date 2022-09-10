package nekogochan.snake.engine;

import nekogochan.math.IntVec2;
import nekogochan.snake.controller.Controller;
import nekogochan.snake.controller.SwingKeyboardController;
import nekogochan.snake.model.SnakeModel;
import static nekogochan.snake.model.SnakeModel.State.*;
import nekogochan.snake.model.field.ConstField;
import nekogochan.snake.model.items.MoveDirection;
import nekogochan.snake.view.swing.SimpleSwingViewFieldInfo;
import nekogochan.snake.view.swing.SwingDesktopView;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

public class SwingSnakeEngine extends Engine {

    private static final int WIDTH = 20, HEIGHT = 20;

    private final SnakeModel model;
    private final SwingDesktopView view;
    private final Controller controller;

    public SwingSnakeEngine() {
        var model = new SnakeModel(
                new ConstField(WIDTH, HEIGHT)
        );
        var view = new SwingDesktopView(
                new IntVec2(WIDTH, HEIGHT).product(new IntVec2(40)),
                new SimpleSwingViewFieldInfo(WIDTH, HEIGHT, Color.WHITE)
        );
        var controller = new SwingKeyboardController();

        controller.applyTo(view.frame());
        view.frame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SwingSnakeEngine.this.stop();
            }
        });

        this.model = model;
        this.view = view;
        this.controller = controller;
    }

    @Override
    protected void handleInput() {
        if (model.state() == InProgress) {
            Map.of(
                    // Down and Up reversed (because of draw mechanism)
                    controller.left(), MoveDirection.Left,
                    controller.up(), MoveDirection.Down,
                    controller.right(), MoveDirection.Right,
                    controller.down(), MoveDirection.Up
            ).forEach((keyInput, moveDirection) -> {
                if (keyInput.active()) {
                    model.setSnakeMoveDirection(moveDirection);
                }
            });
        }

        if (controller.enter().active()) {
            if (model.state() == SnakeDied || model.state() == NotStarted) {
                model.start();
            }
        }
    }

    @Override
    protected void update() {
        if (model.state() == InProgress) {
            model.act();
        }
        controller.resetAll();
    }

    @Override
    protected void render() {
        view.clear();
        model.field().items().forEach(view::draw);
        view.repaint();
    }

    @Override
    public void start() {
        view.frame().setVisible(true);
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        view.frame().setVisible(false);
    }
}
