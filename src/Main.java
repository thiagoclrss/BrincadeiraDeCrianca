import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        Scanner n = new Scanner(System.in);
        System.out.println("Informe a quantidade de bolas desejadas no cesto");
        int qntBallsBasket = n.nextInt();
        int permits = 1;
        Semaphore mutex = new Semaphore(permits);
        Semaphore emptyBasket = new Semaphore(qntBallsBasket);
        Semaphore fullBasket = new Semaphore(qntBallsBasket);
        Basket basket = new Basket(qntBallsBasket, mutex, emptyBasket, fullBasket);
        System.out.println(" Para criar nova criança digite: 1 ");
        System.out.println("Caso queira encerrar o program digite: 2");
        int item = n.nextInt();
        //List<ThreadChild> childCount = new ArrayList<>();

        switch (item) {

            case 1:

                System.out.println("- Informe um número: ");
                int idThread = n.nextInt();
                System.out.println("- Criança estará com bola? (S/N) ");
                String ball = n.nextLine();
                System.out.println("- Informe quanto tempo a criança brincará: (s) ");
                int playingTime = n.nextInt();
                System.out.println("- Informe quanto tempo a criança descansará: (s) ");
                int quietTime = n.nextInt();
                AddChild addChild = new AddChild(idThread, ball, playingTime, quietTime, basket);
                addChild.newChild();

            case 2:

                break;

        }

        //Thread child = new ThreadChild(1,true, 20, 29, Semaphore basket);
        //System.out.println("Informe a quantas crianças brincarão:");
        /*
        int nChilds = n.nextInt();
        for (int i = 1; i <= nChilds; i++) {
            Thread child = new ThreadChild(pegar os atributos aq);
            //child.start();
        }
        */
    }
}