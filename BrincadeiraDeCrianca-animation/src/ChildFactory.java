
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChildFactory
{
    /*Iniciar novas crianças e fazer as lógicas que permite o número de crianças com bola seja menor do que as sem bola*/
    private final String idChild;
    private boolean ball; //Define se a criança vai ser produtora ou consumidora
    private final int playingTime;
    private final int quietTime;

    private int countBallChild = 0;
    private int countNoBallChild = 0;
    List<ThreadChild> childCount = new ArrayList<>();
    Scanner n = new Scanner(System.in);

    public ChildFactory(String idChild, String ball, int playingTime, int quietTime)
    {

        this.idChild = idChild;
        this.ball = verifyBall(ball);
        this.playingTime = playingTime;
        this.quietTime = quietTime;

    }

    public boolean verifyBall(String ball) /*Função que verifica se a criança possui ou não a bola*/
    {
        return ball.equals("S");
    }

    public boolean verifyChild()
    /*Método para verificar se M<N; Só permite que M>N se não haviam crianças anteriormente*/
    {
        if(this.countNoBallChild == 0 && this.countBallChild == 0){return true;}
        else
        {
            countChild();
            return this.countBallChild < this.countNoBallChild;
        }
    }

    public void newChild() /*Cria uma nova Thread "criança", dadas as condições satisfeitas*/
    {
        if(verifyChild()) /*Caso seja possível criar uma thread nova, usa as credenciais dadas pelo usuário durante a execução*/
        {
            ThreadChild child = new ThreadChild(this.idChild, this.ball, this.playingTime, this.quietTime); /*Instancia uma thread*/
            child.start();
            //childCount.add(child);
        }
        else /*Caso não seja possível, pede para que tente novamente*/
        {
            System.out.println("O número de crianças com bola deve ser menor do que as com bola, deseja criar criança sem bola? (S/N)");
            verifyBall(n.next());
            this.ball = verifyBall(n.next());
            newChild();
        }

    }

    public void countChild()/*Método para contar crianças com bola e sem bola*/
    {
        if(this.ball) this.countBallChild++;
        else {this.countNoBallChild++;}

    }
}
//avaliar a possibilidade de fazer duas listas com as crianças com e sem a bola para comprar o tamanho delas
//terminar a logica q torna verdade M<N, fazer contador pra thread n dormir enquando brinca ou espera e estestar o código