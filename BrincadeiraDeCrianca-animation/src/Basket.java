import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class Basket /*Declaração da classe Basket, usada como buffer durante a execução*/
{
    public static int qntBallsBasket = 0; /*Variável que armazena a quantidade de bolas no cesto*/
    public static Semaphore mutex, emptyBasket, fullBasket; /*Declaração dos semáforos usados*/

    public Basket(int maxBall)
    {
        this.mutex = new Semaphore(1); /*Semáforo usado em regiões críticas*/
        this.emptyBasket = new Semaphore(maxBall); /*Espaço restante no cesto*/
        this.fullBasket = new Semaphore(0); /*Espaço ocupado no cesto*/
    }
}
