package ChildLogic;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Basket {
    public static int qntBallsBasket = 0;
    public static Semaphore mutex, emptyBasket, fullBasket;

    public static ArrayList<ThreadChild> children;

    public int countBallChild = 0;
    public int countNoBallChild = 0;

    public Basket(int maxBall) {
        children = new ArrayList<>();
        mutex = new Semaphore(1);
        emptyBasket = new Semaphore(maxBall);
        fullBasket = new Semaphore(0);
    }

    public void addChild(String idChild, boolean ball, int playingTime, int quietTime, GUIInterface guiInterface) {
        ThreadChild child = new ThreadChild(idChild, ball, playingTime, quietTime, guiInterface);

        if(!child.isBall()) {
            System.out.println("Criança " + idChild + " sem bola criada com sucesso");
            children.add(child);
            child.start();
            countNoBallChild++;
            return;
        }

        if(child.isBall() && countNoBallChild > countBallChild) {
            System.out.println("Criança " + idChild + " com bola criada com sucesso");
            children.add(child);
            child.start();
            countBallChild++;
            return;
        }

        if(countNoBallChild == 0  && countBallChild == 0) {
            children.add(child);
            child.start();
            if(child.isBall()) {
                System.out.println("Criança " + idChild + " com bola criada com sucesso");
                countBallChild++;
            } else {
                countNoBallChild++;
                System.out.println("Criança " + idChild + " sem bola criada com sucesso");
            }
            return;
        }

        System.out.println("Criança " + idChild + " não pode ser criada");
    }
}
