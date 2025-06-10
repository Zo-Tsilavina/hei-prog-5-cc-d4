import java.util.Scanner;

public class GameOverState implements GameState {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void tick(Game game) {
        System.out.println("GAME OVER !");
        System.out.println("Score final : " + (game.getSnake().getBody().size() - 3));
        System.out.println("1. Rejouer");
        System.out.println("2. Quitter");
        System.out.print("Choix > ");

        String input = scanner.nextLine().trim();
        switch (input) {
            case "1" -> game.setState(new MenuState());
            case "2" -> {
                System.out.println("À bientôt !");
                System.exit(0);
            }
            default -> System.out.println("Choix invalide.");
        }
    }
}
