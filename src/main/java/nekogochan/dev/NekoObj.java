package nekogochan.dev;

import java.util.function.Consumer;

public interface NekoObj<Origin extends NekoObj<Origin>> {

    private Origin _this() {
        return (Origin) this;
    }

    default Origin apply(Consumer<Origin> fn) {
        fn.accept(_this());
        return _this();
    }
}
