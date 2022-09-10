package nekogochan.snake.model.field;

import nekogochan.dev.Mutable;
import nekogochan.math.IntVec2;
import nekogochan.snake.model.items.GameItem;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.function.Predicate.isEqual;
import static nekogochan.snake.model.field.Field_Static.random;
import nekogochan.snake.model.items.food.Food;

class Field_Static {
    public static Random random = new Random();
}

public interface Field {
    int width();

    int height();

    Stream<GameItem> items();

    boolean haveFreeSpace();

    @Mutable void addItem(GameItem item);

    @Mutable void clear();

    default Optional<IntVec2> randomFreePos() {
        if (!haveFreeSpace()) {
            return Optional.empty();
        }
        while (true) {
            var x = random.nextInt(width());
            var y = random.nextInt(height());
            var vec = new IntVec2(x, y);
            if (items().map(GameItem::pos).noneMatch(isEqual(vec))) {
                return Optional.of(vec);
            }
        }
    }

    default boolean inBounds(IntVec2 pos) {
        var x = pos.x();
        var y = pos.y();
        return x >= 0 && y >= 0 && x < width() && y < height();
    }

    void removeItem(Food food);
}
