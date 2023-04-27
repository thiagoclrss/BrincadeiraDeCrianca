package ChildLogic;

import GUI.MyFrame;

import java.util.Timer;
import java.util.concurrent.Semaphore;

public class ThreadChild extends Thread{

    private String idChild;
    private boolean ball; //define se a criança vai ser produtora ou consumidora
    private int playingTime;
    private int quietTime;
    private GUIInterface guiInterface;

    public ThreadChild(String idChild, boolean ball, int playingTime, int quietTime, GUIInterface guiInterface){
        this.idChild = idChild;
        this.ball = ball;
        this.playingTime = playingTime;
        this.quietTime = quietTime;
        this.guiInterface = guiInterface;
    }

    @Override
    public void run() {
        guiInterface.newChildAnimation();
        while (true) {
            if(this.ball) {
                guiInterface.play(idChild);
                playOrRest(playingTime);
                guiInterface.putBall(idChild);
                putBall();
                guiInterface.rest(idChild);
                playOrRest(quietTime);
            } else {
                guiInterface.getBall(idChild);
                getBall();
            }
        }
    }


    public void getBall(){
        try {
            Basket.fullBasket.acquire(); //o cesto precisa começar vazio
            Basket.mutex.acquire();
            System.out.println("Criança " + idChild + " pegou bola no cesto.");
            Basket.qntBallsBasket--;
            this.ball = true;
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            Basket.mutex.release();
            Basket.emptyBasket.release();
        }
    }

    public void putBall(){
        try{
            Basket.emptyBasket.acquire();
            Basket.mutex.acquire();
            System.out.println("Criança " + idChild + " guardou a bola no cesto.");
            Basket.qntBallsBasket++;
            this.ball = false;
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            Basket.mutex.release();
            Basket.fullBasket.release();
        }
    }

    public void playOrRest(long tempo) {
        long auxPlayingTime = playingTime;
        long auxQuietTime = quietTime;

        if (this.ball) {
            System.out.println("Criança " + this.idChild + " está brincando. ");
        } else {
            System.out.println("Criança " + this.idChild + " está descansando. ");
        }

        long tempoAtual = System.currentTimeMillis();
        long tempoDecorrido = 0, milisegundos = 0;
        while (tempoDecorrido < tempo) {
            milisegundos = (System.currentTimeMillis() - tempoAtual);
            if ((milisegundos / 1000) > tempoDecorrido) {
                if (ball) auxPlayingTime--;
                else auxQuietTime--;
            }
            if (ball && auxPlayingTime == 0) {
                break;
            }
            if (ball && auxQuietTime == 0) {
                break;
            }
            tempoDecorrido = milisegundos / 1000;
        }
    }

    public GUIInterface getGuiInterface() {
        return guiInterface;
    }

    public void setGuiInterface(GUIInterface guiInterface) {
        this.guiInterface = guiInterface;
    }

    public String getIdChild() {
        return idChild;
    }

    public void setIdChild(String idChild) {
        this.idChild = idChild;
    }

    public boolean isBall() {
        return ball;
    }

    public void setBall(boolean ball) {
        this.ball = ball;
    }

    public int getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(int playingTime) {
        this.playingTime = playingTime;
    }

    public int getQuietTime() {
        return quietTime;
    }

    public void setQuietTime(int quietTime) {
        this.quietTime = quietTime;
    }
}
