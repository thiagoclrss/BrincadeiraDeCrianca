import java.util.concurrent.Semaphore;

public class ThreadChild extends Thread{

    private int idChild;
    private boolean ball; //define se a criança vai ser produtora ou consumidora
    private int playingTime;
    private int quietTime;
    private Basket basket;

    public ThreadChild(int idChild, boolean ball, int playingTime, int quietTime, Basket basket){

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

    public void play() {
        if(!this.ball) {
            //aq a criança n tem a bola e chama getball para pegar a bola no cesto, dps q conseguir brinca
            basket.getBall(this.getName());

            try {
                //apos conseguir a bola a criança vai brincar
                System.out.println(" Criança " + this.getName() + " está brincando. " );
                //criança brincando durante o tempo passado pelo usuario, mudar pois a thread n pode dormir brincando
                sleep(playingTime * 1000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        } else {
                //aq a criança ja tem a bola e vai brincar, dps coloca a bola no cesto com o metodo setball
            try {
                //criança brincando durante o tempo passado pelo usuario, mudar pois a thread n pode dormir brincando
                System.out.println(" Criança " + this.getName() + " está brincando. " );
                sleep(playingTime * 1000);
                basket.setBall(this.getName());
                System.out.println(" Criança " + this.getName() + " está descansando. " );
                sleep(quietTime * 1000); //tempo q a criança fica descansando apos guardar a bola

            } catch (InterruptedException e) {

                e.printStackTrace();

            }
        }
    }
}
