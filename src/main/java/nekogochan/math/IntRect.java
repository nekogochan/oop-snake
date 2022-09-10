package nekogochan.math;

public class IntRect {
    private int x1, y1, x2, y2;

    public IntRect(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public IntRect(IntVec2 xy1, IntVec2 xy2) {
        this(xy1.x(), xy1.y(), xy2.x(), xy2.y());
    }

    public boolean inBounds(IntVec2 pos) {
        var x = pos.x();
        var y = pos.y();
        return x >= x1 && y >= y1 && x <= x2 && y <= y2;
    }
}
