public class RunningState implements GameState {
    @Override
    public void tick(Game game) {
        game.render();
        game.handleInput();
        game.update();
    }
}
