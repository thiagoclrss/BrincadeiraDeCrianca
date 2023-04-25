import java.util.Scanner;

public class Main /*Classe principal do programa*/
{
    public static void main(String[] args) /*Função principal do programa*/
    {
        Scanner n = new Scanner(System.in); /*Variável usada para armazenar entradas*/
        System.out.println("Informe a quantidade de bolas desejadas no cesto");
        int qntBallsBasket = n.nextInt(); /*Buffer que recebe int*/

        Basket basket = new Basket(qntBallsBasket); /*Criação do cesto*/
        System.out.println("Para criar nova criança digite: 1 ");
        System.out.println("Caso queira encerrar o programa digite: 2");
        int item = n.nextInt();

        do
        {
            System.out.println("- Informe o nome da criança: ");
            String idThread = n.next();
            System.out.println("- Criança estará com bola? (S/N) ");
            String ball = n.next();
            System.out.println("- Informe quanto tempo a criança brincará: (s) ");
            int playingTime = n.nextInt();
            System.out.println("- Informe quanto tempo a criança descansará: (s) ");
            int quietTime = n.nextInt();
            ChildFactory addChild = new ChildFactory(idThread, ball, playingTime, quietTime); /*instancia uma Thread*/
            addChild.newChild();

            System.out.println("Para criar nova criança digite: 1 ");
            System.out.println("Caso queira encerrar o programa digite: 2");
            item = n.nextInt();
        } while (item == 1);
    }
}



