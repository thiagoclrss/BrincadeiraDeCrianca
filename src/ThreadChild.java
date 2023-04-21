import java.util.Timer;
import java.util.concurrent.Semaphore;

public class ThreadChild extends Thread{

    private String idChild;
    private boolean ball; //define se a criança vai ser produtora ou consumidora
    private int playingTime;
    private int quietTime;
    private Basket basket;
    private String childState;


    public ThreadChild(String idChild, boolean ball, int playingTime, int quietTime){
        this.idChild = idChild;
        this.ball = ball;
        this.playingTime = playingTime;
        this.quietTime = quietTime;
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

    /*
    public void play () {
        if(!this.ball) {
            //aq a criança n tem a bola e chama getball para pegar a bola no cesto, dps q conseguir brinca
            System.out.println(" \t \t \t \t Criança " + this.idChild + " vai buscar a bola no cesto " );
            basket.getBall(this.idChild);
            this.ball = true;

            //mutex.acquire();
            //apos conseguir a bola a criança vai brincar
            //System.out.println(" \t \t \t \t Criança " + this.idChild + " está brincando. " );
            //criança brincando durante o tempo passado pelo usuario, mudar pois a thread n pode dormir brincando
            // basket.mutex.acquire();
            //basket.playingOrRestingTime(playingTime); //brinca
            //mutex.release();
            //sleep(playingTime* 1000);
            //basket.setBall(this.idChild); //guarda a bola
            //mutex.acquire();
            //sleep(1000 * quietTime);
            //basket.playingOrRestingTime(quietTime); //descansa
            //mutex.release();
            //play(); //vai brincar de novo



        } else {
            //aq a criança ja tem a bola e vai brincar, dps coloca a bola no cesto com o metodo setball
            //criança brincando durante o tempo passado pelo usuario, mudar pois a thread n pode dormir brincando
            //this.mutex.acquire();
            System.out.println(" \t \t \t \t Criança " + this.idChild + " está brincando. " );
            basket.playingOrRestingTime(playingTime);//brincando
            //this.mutex.release();
            basket.setBall(this.idChild);//guarda a bola
            this.ball =  false;
            //this.mutex.acquire();
            System.out.println(" \t \t \t \t Criança " + this.idChild + " está descansando. " );
            basket.playingOrRestingTime(quietTime); //tempo q a criança fica descansando apos guardar a bola
            // this.mutex.release();
            //play();//vai brincar de novo

        }
    }
    */

    /*
    public void playingOrRestingTime(int playingResting){
        if(this.ball){
            System.out.println(" \t \t \t \t Criança " + this.idChild + " está brincando. " );
        } else { System.out.println(" \t \t \t \t Criança " + this.idChild + " está descansando. " );}

        //usar um get time pegando os segundos pra fazer o contador
        //final long playingResting = (long)teste;
        int timeCount = playingResting;
        Timer timer = new Timer();
        //final long seconds = (1000 * playingResting);
        Count count = new Count(playingResting);
        timer.scheduleAtFixedRate(count,0, 1000);


    }
    */

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
