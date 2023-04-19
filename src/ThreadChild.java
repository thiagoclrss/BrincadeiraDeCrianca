import java.util.concurrent.Semaphore;

public class ThreadChild extends Thread{

    private String idChild;
    private boolean ball; //define se a criança vai ser produtora ou consumidora
    private int playingTime;
    private int quietTime;
    private Basket basket;
    private String childState;

    public ThreadChild(String idChild, boolean ball, int playingTime, int quietTime, Basket basket){

        this.idChild = idChild;
        this.ball = ball;
        this.playingTime = playingTime;
        this.quietTime = quietTime;
        this.basket = basket;

    }

    @Override
    public void run() {
        play();
    }

    public void play () {
        if(!this.ball) {
            //aq a criança n tem a bola e chama getball para pegar a bola no cesto, dps q conseguir brinca
            System.out.println(" \t \t \t \t Criança " + this.idChild + " vai buscar a bola no cesto " );
            basket.getBall(this.idChild);

            //apos conseguir a bola a criança vai brincar
            System.out.println(" \t \t \t \t Criança " + this.idChild + " está brincando. " );
            //criança brincando durante o tempo passado pelo usuario, mudar pois a thread n pode dormir brincando
            basket.playingOrRestingTime(playingTime); //brinca
            //sleep(playingTime* 1000);
            basket.setBall(this.idChild); //guarda a bola
            //sleep(1000 * quietTime);
            basket.playingOrRestingTime(quietTime); //descansa
            play(); //vai brincar de novo

        } else {
            //aq a criança ja tem a bola e vai brincar, dps coloca a bola no cesto com o metodo setball
            //criança brincando durante o tempo passado pelo usuario, mudar pois a thread n pode dormir brincando
            System.out.println(" \t \t \t \t Criança " + this.idChild + " está brincando. " );
            basket.playingOrRestingTime(playingTime);//brincando
            basket.setBall(this.idChild);//guarda a bola
            System.out.println(" \t \t \t \t Criança " + this.idChild + " está descansando. " );
            basket.playingOrRestingTime(quietTime); //tempo q a criança fica descansando apos guardar a bola
            play();//vai brincar de novo
        }
    }
}
