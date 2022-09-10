package nekogochan.snake.view.swing;

import nekogochan.dev.Mutable;

import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

public class SimpleSwingViewFieldInfo implements SwingViewFieldInfo {

    private final Color[][] colorTable;
    private Color emptyColor;

    public SimpleSwingViewFieldInfo(int width, int height, Color emptyColor) {
        colorTable = new Color[width][height];
        this.emptyColor = emptyColor;
    }

    @Override
    public Color emptyColor() {
        return emptyColor;
    }

    @Override
    public void setEmptyColor(Color emptyColor) {
        this.emptyColor = emptyColor;
    }

    @Override
    public int width() {
        return colorTable.length;
    }

    @Override
    public int height() {
        return colorTable.length == 0 ? 0 : colorTable[0].length;
    }

    @Override
    public Color color(int x, int y) {
        checkInBounds(x, y);
        return colorTable[x][y];
    }

    @Override
    @Mutable
    public void set(int x, int y, Color color) {
        checkInBounds(x, y);
        colorTable[x][y] = color;
    }

    @Override
    public void clear() {
        for (var row : colorTable) {
            Arrays.fill(row, null);
        }
    }

    @Override
    public void forEachCell(ForEachFn fn) {
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                fn.accept(x, y, Optional.ofNullable(colorTable[x][y]).orElse(emptyColor));
            }
        }
    }

    private void checkInBounds(int x, int y) {
        if (x < 0 || y < 0 || x >= width() || y >= height()) {
            throw new IllegalArgumentException(
                    "Illegal pos 'x'=%s and 'y'=%s for width='%s' and height = '%s'".formatted(
                            x, y, width(), height()
                    )
            );
        }
    }
}
