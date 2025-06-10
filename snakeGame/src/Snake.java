import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Snake {
    private LinkedList<Point> body;
    private Direction currentDirection;

    public Snake(List<Point> initialBody, Direction direction) {
        this.body = new LinkedList<>(initialBody);
        this.currentDirection = direction;
    }

    public void move(Point newHead, boolean hasEaten) {
        body.addFirst(newHead);
        if (!hasEaten) body.removeLast();
    }

    public boolean hitsItself() {
        return new HashSet<>(body).size() != body.size();
    }

    public Point getHead() {
        return body.getFirst();
    }

    public List<Point> getBody() {
        return Collections.unmodifiableList(body);
    }

    public void setDirection(Direction dir) {
        this.currentDirection = dir;
    }

    public Direction getDirection() {
        return currentDirection;
    }
}
