import java.util.concurrent.Semaphore;

public class Basket {
    private int qntBallsBasket;
    private Semaphore mutex, emptyBasket, fullBasket;

    public Basket(int qntBallsBasket, Semaphore mutex, Semaphore emptyBasket, Semaphore fullBasket){

        //inicio o construtor

        this.qntBallsBasket = qntBallsBasket;
        this.mutex = mutex;
        this.emptyBasket = emptyBasket;
        this.fullBasket = fullBasket;

    }


    public int getBall(String idThread){

        try{

            fullBasket.acquire(); //o cesto precisa começar vazio
            mutex.acquire();
            System.out.println(" Criança " + idThread + " pegou a bola do cesto.");
            //System.out.println(" Criança " + idThread + " está brincando.");
            this.qntBallsBasket--;

        } catch (InterruptedException e){

            e.printStackTrace();

        } finally {

            mutex.release();
            emptyBasket.release();

        }


        return this.qntBallsBasket;
    }

    public int setBall(String idThread){

        try{

            emptyBasket.acquire();
            mutex.acquire();
            System.out.println(" Criança " + idThread + " guardou a bola no cesto.");
            this.qntBallsBasket++;

        } catch (InterruptedException e){

            e.printStackTrace();


        } finally {

            mutex.release();
            fullBasket.release();

        }

        return this.qntBallsBasket;
    }

}
