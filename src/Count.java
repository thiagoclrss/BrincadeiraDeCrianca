import java.util.TimerTask;

public class Count extends TimerTask {
    private int timeCount;

    public Count(int playingResting) {
        this.timeCount = playingResting;
    }


    @Override
    public void run() {
        System.out.print("\r" + timeCount + "\r");
        regressiveCount();
    }

    public void regressiveCount(){
        if(timeCount == 0 ) cancel();
        this.timeCount--;
    }
}
