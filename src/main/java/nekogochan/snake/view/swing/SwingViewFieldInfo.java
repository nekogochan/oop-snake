package nekogochan.snake.view.swing;

import nekogochan.dev.Mutable;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public interface SwingViewFieldInfo {
    int width();

    int height();

    Color color(int x, int y);

    Color emptyColor();

    @Mutable
    void setEmptyColor(Color color);

    @Mutable
    void set(int x, int y, Color color);

    @Mutable
    void clear();

    interface ForEachFn {
        void accept(int x, int y, Color color);
    }
    void forEachCell(ForEachFn fn);
}
