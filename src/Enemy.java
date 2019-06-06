import java.util.Random;

public class Enemy {
	private static int reducedTime;
	public static int reduceTime() {
		reducedTime=1+new Random().nextInt(6);
		return reducedTime;
	}

}
