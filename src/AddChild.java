
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class AddChild {

    //iniciar novas crianças e fazer a logicas q permite o numero de crianças com bola ser menor doq as com bola
    //vou chamar um metodo
    private String idChild;
    private boolean ball; //define se a criança vai ser produtora ou consumidora
    private int playingTime;
    private int quietTime;
    private Basket basket;

    private int countBallChild = 0;
    private int countNoBallChild = 0;
    List<ThreadChild> childCount = new ArrayList<>();
    Scanner n = new Scanner(System.in);
    //childCount.add();

    public AddChild(String idChild, String ball, int playingTime, int quietTime, Basket basket){

        this.idChild = idChild;
        this.ball = verifyBall(ball);
        this.playingTime = playingTime;
        this.quietTime = quietTime;
        this.basket = basket;

    }

    public boolean verifyBall(String ball){
        this.ball = ball.equals("S");

        return this.ball;

    }

    public boolean verifyChild(){
        //metodo para verificar se M<N
        //so permite q M<N se o quando não existe nenhuma criança
        if(this.countNoBallChild == 0 && this.countBallChild == 0){return true;}
        else {countChild();
            return this.countBallChild < this.countNoBallChild;}
    }

    public void newChild(){
        if(verifyChild()){
            ThreadChild child = new ThreadChild(this.idChild, this.ball, this.playingTime, this.quietTime, this.basket);
            child.start();
            childCount.add(child);
        } else {
            System.out.println("O número de crianças com bola deve ser menor doq as com bola, deseja criar criança sem bola? (S/N)");
            verifyBall(n.next());
            this.ball = verifyBall(n.next());;
            newChild();
        }

    }

    public void countChild(){
        //metodo para contar crianças com bola e sem bola
        if(this.ball) this.countBallChild++;
        else {this.countNoBallChild++;}

    }
}
//avaliar a possibilidade de fazer duas listas com as crianças com e sem a bola para comprar o tamanho delas
//terminar a logica q torna verdade M<N, fazer contador pra thread n dormir enquando brinca ou espera e estestar o código