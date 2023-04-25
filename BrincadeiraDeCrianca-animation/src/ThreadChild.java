import java.util.Timer;
import java.util.concurrent.Semaphore;

public class ThreadChild extends Thread
{
    private String idChild;
    private boolean ball; /*Define se a criança vai ser produtora ou consumidora*/
    private int playingTime;
    private int quietTime;
    private Basket basket;

    public enum State /*Define se a criança está brincando ou se está descansando*/
    {
        PLAYING, QUIET;
    }
    private State childState;

    public ThreadChild(String idChild, boolean ball, int playingTime, int quietTime) /*Instancia uma thread*/
    {
        this.idChild = idChild;
        this.ball = ball;
        this.playingTime = playingTime;
        this.quietTime = quietTime;
        //this.childState = State.QUIET;
    }

    @Override
    public void run()
    {
        while (true)
        {
            if(this.ball)
            {
                cpuBound(playingTime); /*Irá consumir um tempo definido pelo usuário em CPU-bound*/
                putBall(); /*Depois de terminar de brincar, a criança tenta colocar a bola de volta no cesto*/
                this.ball = false; /*Ao colocar a bola no cesto, a criança não possui mais tal bola*/
                cpuBound(quietTime); /*A criança fica quieta, descansando*/

            }
            else
            {
                getBall(); /*Vai até o cesto e tenta pegar uma bola*/
                this.ball = true; /*Com a bola em mãos, a criança a possui*/
            }
        }
    }


    public void getBall() /*Função pegar bola*/
    {
        try
        {
            Basket.fullBasket.acquire(); /*Dá um "down();" no semáforo fullBasket*/
            Basket.mutex.acquire(); /*Dá um "down();" no semáforo mutex*/
            System.out.println(" \t \t \t \tCriança " + idChild + " pegou a bola do cesto.");
            Basket.qntBallsBasket--; /*Remove uma bola do cesto*/
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            Basket.mutex.release();
            Basket.emptyBasket.release();
            /*Dá um up(); nos semáforos*/
        }
        //return this.qntBallsBasket;
    }

    public void putBall() /*A criança, se houver espaço no cesto, coloca a bola de volta*/
    {
        try
        {
            Basket.emptyBasket.acquire(); /*Dá um "down();" no semáforo emptyBasket*/
            Basket.mutex.acquire(); /*Dá um "down();" no semáforo mutex*/
            System.out.println(" \t \t \t \tCriança " + idChild + " guardou a bola no cesto.");
            Basket.qntBallsBasket++; /*Coloca uma bola no cesto*/
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            Basket.mutex.release();
            Basket.fullBasket.release();
            /*Dá um up(); nos semáforos*/
        }
        //return this.qntBallsBasket;
    }
    public void cpuBound(long tempo) /*Função que faz a passagem de tempo de forma CPU-bound*/
    {
        long auxPlayingTime = playingTime;
        long auxQuietTime = quietTime;
        if (this.ball)
        {
            System.out.println(" \t \t \t \tCriança " + this.idChild + " está brincando. ");
        }
        else
        {
            System.out.println(" \t \t \t \tCriança " + this.idChild + " está descansando. ");
        }
            long tempoAtual = System.currentTimeMillis();
            long tempoDecorrido = 0, milisegundos = 0;
            while (tempoDecorrido < tempo)
            {
                milisegundos = (System.currentTimeMillis() - tempoAtual);
                if ((milisegundos / 1000) > tempoDecorrido)
                {
                    if (ball) auxPlayingTime--;
                    else auxQuietTime--;
                    //callBack.methodToCallBack();
                }
                if (ball && auxPlayingTime == 0)
                {
                    break;
                }
                if (ball && auxQuietTime == 0)
                {
                    break;
                }
                tempoDecorrido = milisegundos / 1000;
            }
            if (ball) System.out.println("Criança " + this.idChild + "  terminou de brincar :(\n");
            else System.out.println("Criança " + this.idChild + "  não está mais quieta!\n");
    }
}
