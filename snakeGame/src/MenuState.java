import java.util.Scanner;

public class MenuState implements GameState {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void tick(Game game) {
        System.out.println("=== SNAKE GAME ===");
        System.out.println("1. Démarrer le jeu");
        System.out.println("2. Quitter");
        System.out.print("Choix > ");

        String input = scanner.nextLine().trim();
        switch (input) {
            case "1" -> game.setState(new RunningState());
            case "2" -> {
                System.out.println("Au revoir !");
                System.exit(0);
            }
            default -> System.out.println("Choix invalide.");
        }
    }
}
