import ChildLogic.Basket;
import ChildLogic.ThreadChild;
import GUI.Action;
import GUI.MyFrame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MyFrame myFrame = new MyFrame();

        Scanner n = new Scanner(System.in);
        System.out.println("Informe a quantidade de bolas desejadas no cesto");
        int qntBallsBasket = n.nextInt();

        Basket basket = new Basket(qntBallsBasket);
        System.out.println("Para criar nova criança digite: 1 ");
        System.out.println("Caso queira encerrar o programa digite: 2");

        int item = n.nextInt();

        do {
            System.out.println("- Informe o nome da criança: ");
            String idThread = n.next();
            System.out.println("- Criança estará com bola? (S/N) ");
            String ball = n.next();
            System.out.println("- Informe quanto tempo a criança brincará: (s) ");
            int playingTime = n.nextInt();
            System.out.println("- Informe quanto tempo a criança descansará: (s) ");
            int quietTime = n.nextInt();

            basket.addChild(idThread, ball.equals("S"), playingTime, quietTime, myFrame.getPanel().guiInterface);

            System.out.println("Para criar nova criança digite: 1 ");
            System.out.println("Caso queira encerrar o programa digite: 2");
            item = n.nextInt();
        } while (item == 1);
    }
}



