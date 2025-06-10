import java.util.List;
import java.util.Random;

public class FoodFactory {
    private final Random random = new Random();

    public Point generateFood(List<Point> snakeBody, int gridSize) {
        Point food;
        do {
            int x = random.nextInt(gridSize);
            int y = random.nextInt(gridSize);
            food = new Point(x, y);
        } while (snakeBody.contains(food));
        return food;
    }
}
