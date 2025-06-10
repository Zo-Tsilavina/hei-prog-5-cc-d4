import java.util.Scanner;

public class UserInputStrategy implements MoveStrategy {
    public UserInputStrategy(Scanner scanner) {
    }

    @Override
    public Point computeNextHead(Snake snake) {
        Direction dir = snake.getDirection();
        return snake.getHead().translate(dir);
    }
}
