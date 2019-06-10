import java.util.Random;

public class Enemy {
	
	private static int reducedTime;
	//Reduce the time of the Player after a correct answer with a chance
	public static int reduceTime() {
		reducedTime = 1 + new Random().nextInt(6);
		return reducedTime;
	}

}
