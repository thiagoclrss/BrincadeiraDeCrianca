
import java.util.ArrayList;
import java.util.List;

public class AddChild {

    //iniciar novas crianças e fazer a logicas q permite o numero de crianças com bola ser menor doq as com bola
    //vou chamar um metodo
    private  int idChild;
    private boolean ball; //define se a criança vai ser produtora ou consumidora
    private int playingTime;
    private int quietTime;
    private Basket basket;
    List<ThreadChild> childCount = new ArrayList<>();
    //childCount.add();

    public AddChild(int idChild, String ball, int playingTime, int quietTime, Basket basket){

        this.idChild = idChild;
        this.ball = verifyBall(ball);
        this.playingTime = playingTime;
        this.quietTime = quietTime;
        this.basket = basket;

    }

    public boolean verifyBall(String ball){
        if(ball.equals("S")){

            this.ball = true;

        } else {

            this.ball = false;

        }

        return this.ball;

    }

    public boolean verifyChild(){
        childCount.size();
        return true;//ajeitar
    }

    public void newChild(){
        if(verifyChild()){
            ThreadChild child = new ThreadChild(1,ball, 20, 29, this.basket);
            child.start();
            childCount.add(child);
        } else {
            System.out.println("O número de crianças com bola deve ser menor doq a metade, por favor crie uma criança sem bola");
        }

    }
}
//avaliar a possibilidade de fazer duas listas com as crianças com e sem a bola para comprar o tamanho delas
//terminar a logica q torna verdade M<N, fazer contador pra thread n dormir enquando brinca ou espera e estestar o código