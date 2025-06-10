import java.util.ArrayList;
import java.util.List;

public class SnakeBuilder {
    private Point start;
    private Direction direction;
    private int length;

    public SnakeBuilder startAt(Point start) {
        this.start = start;
        return this;
    }

    public SnakeBuilder withDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    public SnakeBuilder withLength(int length) {
        this.length = length;
        return this;
    }

    public Snake build() {
        List<Point> body = new ArrayList<>();
        Point current = start;
        for (int i = 0; i < length; i++) {
            body.add(current);
            current = current.translate(opposite(direction));
        }
        return new Snake(body, direction);
    }

    private Direction opposite(Direction dir) {
        return switch (dir) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case LEFT -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
        };
    }
}
