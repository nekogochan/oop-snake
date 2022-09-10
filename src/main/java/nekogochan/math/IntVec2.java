package nekogochan.math;

import nekogochan.dev.Mutable;
import nekogochan.dev.NekoObj;

import java.util.Objects;

public class IntVec2 implements Cloneable, NekoObj<IntVec2> {
    private int x;
    private int y;

    public IntVec2() {
        this(0);
    }

    public IntVec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IntVec2(int xy) {
        this(xy, xy);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Mutable
    public void setX(int x) {
        this.x = x;
    }

    @Mutable
    public void setY(int y) {
        this.y = y;
    }

    public IntVec2 clone() {
        return new IntVec2(x, y);
    }

    @Mutable
    public void add(IntVec2 that) {
        x += that.x;
        y += that.y;
    }

    @Mutable
    public void multiply(IntVec2 that) {
        x *= that.x;
        y *= that.y;
    }

    @Mutable
    public void subtract(IntVec2 that) {
        x -= that.x;
        y -= that.y;
    }

    @Mutable
    public void divide(IntVec2 that) {
        x /= that.x;
        y /= that.y;
    }

    public IntVec2 sum(IntVec2 that) {
        return clone().apply(it -> it.add(that));
    }

    public IntVec2 product(IntVec2 that) {
        return clone().apply(it -> it.multiply(that));
    }

    public IntVec2 difference(IntVec2 that) {
        return clone().apply(it -> it.subtract(that));
    }

    public IntVec2 quotient(IntVec2 that) {
        return clone().apply(it -> it.divide(that));
    }

    @Mutable
    public void set(IntVec2 that) {
        x = that.x;
        y = that.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntVec2 intVec2 = (IntVec2) o;
        return x == intVec2.x && y == intVec2.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(%s, %s)".formatted(x, y);
    }
}
