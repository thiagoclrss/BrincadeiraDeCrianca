import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class Basket {
   public static int qntBallsBasket = 0;
    public static Semaphore mutex, emptyBasket, fullBasket;

    public Basket(int maxBall){
        this.mutex = new Semaphore(1);
        this.emptyBasket = new Semaphore(maxBall);
        this.fullBasket = new Semaphore(0);
    }

}
