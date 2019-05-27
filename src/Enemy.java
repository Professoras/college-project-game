import java.util.Random;

public class Enemy {
	private static int reducedTime;
	public static int reduceTime() {
		//reducedTime=1+new Random().nextInt((int)Player.getRemainingTime()/2);
		reducedTime=10;
		return reducedTime;
	}

}
