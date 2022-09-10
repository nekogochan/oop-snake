package nekogochan.snake.view.swing;

import static java.awt.image.BufferedImage.*;
import static javax.swing.WindowConstants.*;
import nekogochan.dev.Mutable;
import nekogochan.math.IntVec2;
import nekogochan.snake.model.items.GameItem;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SwingDesktopView {
    private final JFrame windowFrame;
    private final SwingViewFieldInfo fieldInfo;
    private final BufferedImage image;
    private final Graphics2D gx;
    private final SimpleSwingViewFieldInfoDrawGameItemOperation drawGameItemOperation;

    public SwingDesktopView(IntVec2 windowSize, SwingViewFieldInfo fieldInfo) {
        this.fieldInfo = fieldInfo;
        this.image = new BufferedImage(windowSize.x(), windowSize.y(), TYPE_INT_RGB);
        this.gx = image.createGraphics();
        this.drawGameItemOperation = new SimpleSwingViewFieldInfoDrawGameItemOperation(fieldInfo);

        windowFrame = new JFrame();
        windowFrame.setLayout(new FlowLayout());
        windowFrame.setSize(windowSize.x(), windowSize.y());

        var label = new JLabel();
        label.setIcon(new ImageIcon(image));
        windowFrame.add(label);
        windowFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Mutable
    public void clear() {
        fieldInfo.clear();
    }

    public void repaint() {
        var tmp = new BufferedImage(fieldInfo.width(), fieldInfo.height(), TYPE_INT_RGB);
        fieldInfo.forEachCell((x, y, color) -> {
            tmp.setRGB(x, y, color.getRGB());
        });
        var tmpScaled = tmp.getScaledInstance(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
        image.getGraphics().drawImage(tmpScaled, 0, 0, null);
        windowFrame.repaint();
    }

    public JFrame frame() {
        return windowFrame;
    }

    @Mutable
    public void draw(GameItem gameItem) {
        drawGameItemOperation.apply(gameItem);
    }
}
