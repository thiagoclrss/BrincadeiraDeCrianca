import java.util.Timer;
import java.util.concurrent.Semaphore;

public class ThreadChild extends Thread{

    private String idChild;
    private boolean ball; //define se a criança vai ser produtora ou consumidora
    private int playingTime;
    private int quietTime;
    private Basket basket;

    public enum State{
        PLAYING, QUIET;
    };
    private State childState;


    public ThreadChild(String idChild, boolean ball, int playingTime, int quietTime){
        this.idChild = idChild;
        this.ball = ball;
        this.playingTime = playingTime;
        this.quietTime = quietTime;
        //this.childState = State.QUIET;
    }

    @Override
    public void run() {
        while (true){
            if(this.ball){
                cpuBound(playingTime);
                putBall();
                this.ball = false;
                cpuBound(quietTime);

            } else {
                getBall();
                this.ball = true;
            }
        }

    }


    public void getBall(){

        try{
            Basket.fullBasket.acquire(); //o cesto precisa começar vazio
            Basket.mutex.acquire();
            System.out.println(" \t \t \t \t Criança " + idChild + " pegou a bola do cesto.");
            //System.out.println(" Criança " + idThread + " está brincando.");
            Basket.qntBallsBasket--;
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            Basket.mutex.release();
            Basket.emptyBasket.release();
        }


        //return this.qntBallsBasket;
    }

    public void putBall(){

        try{

            Basket.emptyBasket.acquire();
            Basket.mutex.acquire();
            System.out.println(" \t \t \t \tCriança " + idChild + " guardou a bola no cesto.");
            Basket.qntBallsBasket++;

        } catch (InterruptedException e){

            e.printStackTrace();


        } finally {

            Basket.mutex.release();
            Basket.fullBasket.release();

        }

        //return this.qntBallsBasket;
    }

    public void cpuBound(long tempo) {
        long auxPlayingTime = playingTime;
        long auxQuietTime = quietTime;
        if (this.ball) {
            System.out.println(" \t \t \t \t Criança " + this.idChild + " está brincando. ");
        } else {
            System.out.println(" \t \t \t \t Criança " + this.idChild + " está descansando. ");}

            long tempoAtual = System.currentTimeMillis();
            long tempoDecorrido = 0, milisegundos = 0;
            while (tempoDecorrido < tempo) {
                milisegundos = (System.currentTimeMillis() - tempoAtual);
                if ((milisegundos / 1000) > tempoDecorrido) {
                    if (ball) auxPlayingTime--;
                    else auxQuietTime--;
                    //callBack.methodToCallBack();
                }
                if (ball && auxPlayingTime == 0) {
                    break;
                }
                if (ball && auxQuietTime == 0) {
                    break;
                }
                tempoDecorrido = milisegundos / 1000;
            }

            if (ball) System.out.println("Criança " + this.idChild + "  terminou de brincar :(\n");
            else System.out.println("Criança " + this.idChild + "  não está mais quieta!\n");
        }


}
