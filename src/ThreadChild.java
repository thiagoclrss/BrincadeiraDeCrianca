import java.util.concurrent.Semaphore;

public class ThreadChild extends Thread{

    private int idChild;
    private boolean ball;
    private int playingTime;
    private int quietTime;

    public ThreadChild(int idChild, boolean Ball, int playingTime, int quietTime){
        this.idChild = idChild;
        this.ball = ball;
        this.playingTime = playingTime;
        this.quietTime = quietTime;
    }

    @Override
    public void run() {
        brincar();
    }

    public void brincar(){
        if(!this.ball){
            getBall();
        }else{
         //brincar (ver como fazer)
        }
    }

    Basket basket = new Basket();

    public void getBall(){

    }
}
