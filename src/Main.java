import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        Scanner n = new Scanner(System.in);

        System.out.println("Informe a quantidade de bolas desejadas no cesto");
        int qntBalls = n.nextInt();
        Semaphore balls = new Semaphore(qntBalls);
        Basket basket = new Basket(balls);

        System.out.println("Informe a quantas crianças brincarão");
        int nChilds = n.nextInt();

        for (int i = 1; i <= nChilds; i++) {
            Thread child = new ThreadChild(pegar os atributos aq);
            //child.start();
        }
    }
}