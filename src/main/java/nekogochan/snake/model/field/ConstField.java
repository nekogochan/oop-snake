package nekogochan.snake.model.field;

import static java.util.function.Predicate.*;
import nekogochan.dev.Mutable;
import nekogochan.snake.model.items.GameItem;
import nekogochan.snake.model.items.food.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

public class ConstField implements Field {

    private final int width;
    private final int height;
    private final Collection<GameItem> items;

    public ConstField(int width, int height, Collection<GameItem> items) {
        this.width = width;
        this.height = height;
        this.items = items;
    }

    public ConstField(int width, int height) {
        this(width, height, new HashSet<>());
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public Stream<GameItem> items() {
        return items.stream();
    }

    @Override
    @Mutable
    public boolean haveFreeSpace() {
        return items.size() < width * height;
    }

    @Override
    @Mutable
    public void addItem(GameItem item) {
        if (items().map(GameItem::pos).noneMatch(isEqual(item.pos()))) {
            items.add(item);
        } else {
            throw new IllegalStateException("Can't add item with 'pos'=%s for items with poses=%s".formatted(
                    item.pos(),
                    items.stream().map(GameItem::pos).toList()
            ));
        }
    }

    @Override
    @Mutable
    public void clear() {
        items.clear();
    }

    @Override
    public void removeItem(Food food) {
        items.remove(food);
    }
}
