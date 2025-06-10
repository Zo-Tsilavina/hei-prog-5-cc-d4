import java.util.Scanner;

public class Game {
    private static final int GRID_SIZE = 10;

    private Snake snake;
    private Point food;
    private FoodFactory foodFactory;
    private MoveStrategy moveStrategy;
    private GameState currentState;

    private final Scanner scanner = new Scanner(System.in);

    public Game() {
        initGame();
    }

    private void initGame() {
        this.foodFactory = new FoodFactory();
        this.moveStrategy = new UserInputStrategy(scanner);
        this.snake = new SnakeBuilder()
                .startAt(new Point(5, 5))
                .withDirection(Direction.RIGHT)
                .withLength(3)
                .build();

        this.food = foodFactory.generateFood(snake.getBody(), GRID_SIZE);
        this.currentState = new MenuState();
    }

    public void run() {
        while (true) {
            clearScreen();
            currentState.tick(this);
            sleep(500);
        }
    }

    public void setState(GameState state) {
        this.currentState = state;
    }

    public void update() {
        Point nextHead = moveStrategy.computeNextHead(snake);

        boolean hitsWall = isOutOfBounds(nextHead);
        boolean hitsSelf = snake.getBody().contains(nextHead);

        if (hitsWall || hitsSelf) {
            setState(new GameOverState());
            return;
        }

        boolean hasEaten = nextHead.equals(food);
        snake.move(nextHead, hasEaten);

        if (hasEaten) {
            food = foodFactory.generateFood(snake.getBody(), GRID_SIZE);
        }
    }

    public void render() {
        char[][] grid = new char[GRID_SIZE][GRID_SIZE];

        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                grid[y][x] = '.';
            }
        }

        grid[food.y][food.x] = '@';

        for (Point p : snake.getBody()) {
            grid[p.y][p.x] = '*';
        }

        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }

        System.out.println("\nScore : " + (snake.getBody().size() - 3));
        System.out.print("Direction (WASD) > ");
    }

    public void handleInput() {
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim().toUpperCase();
            switch (input) {
                case "W" -> snake.setDirection(Direction.UP);
                case "S" -> snake.setDirection(Direction.DOWN);
                case "A" -> snake.setDirection(Direction.LEFT);
                case "D" -> snake.setDirection(Direction.RIGHT);
                default -> System.out.println("Touche inconnue !");
            }
        }
    }

    private boolean isOutOfBounds(Point p) {
        return p.x < 0 || p.y < 0 || p.x >= GRID_SIZE || p.y >= GRID_SIZE;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public Point getFood() {
        return food;
    }
}