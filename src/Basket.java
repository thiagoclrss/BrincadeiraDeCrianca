import java.util.concurrent.Semaphore;

public class Basket {
    private Semaphore balls;
    public Basket(Semaphore balls){
        this.balls = balls;
    }
}
