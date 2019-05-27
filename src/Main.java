public class Main {

	public static void main(String[] args) {
		Player.startTheTimer();
		openNewGamePanel();
	}
	
	public static void openNewGamePanel() {
		Story.findQuestion(Player.getCurrentRoom());
		GameFunctions.setNewRoundTimeMark(Player.getRemainingTime());
		new GamePanel();
	}
}
