package sample;

import javafx.scene.paint.Color;

import javax.swing.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Georg on 04.04.2017.
 */
public class Feld {
    int groesseX;
    int groesseY;
    private boolean startGesetzt;
    private boolean zielGesetzt;
    int potential;
    Queue<Kaestchen> queue;
    Kaestchen[][] raster;

    public Feld(int x, int y){
        this.groesseX = x;
        this.groesseY = y;
        raster = new Kaestchen[groesseX][groesseY];
        this.potential=0;
        this.queue = new LinkedList<>();
    }

    public boolean isStartGesetzt() {
        return startGesetzt;
    }

    public void setStartGesetzt(boolean startGesetzt) {
        this.startGesetzt = startGesetzt;
    }

    public boolean isZielGesetzt() {
        return zielGesetzt;
    }

    public int getPotential(){
        this.potential++;
        return this.potential;
    }
    public void setZielGesetzt(boolean zielGesetzt) {
        this.zielGesetzt = zielGesetzt;
    }
    public void rechtsKlick(Kaestchen k) {

        if(!k.isZiel() && !k.isStart() && !startGesetzt && !zielGesetzt){
            k.setStart(true);
            setStartGesetzt(true);
            k.setFill(Color.RED);
            System.out.println("Start gesetzt:" + startGesetzt);
            System.out.println("Ziel gesetzt: " + zielGesetzt);
            k.setBelegt(false);
        }

        else if(!k.isStart() && !k.isZiel() && startGesetzt && !zielGesetzt){
            setZielGesetzt(true);
            k.setZiel(true);
            k.setFill(Color.GREEN);
            System.out.println("Ziel gesetzt: " + zielGesetzt);
            System.out.println("start:" + startGesetzt);
            k.setBelegt(false);
        }

        else if(!k.isZiel() && k.isStart() && startGesetzt && zielGesetzt){
            k.setFill(Color.TRANSPARENT);
            k.setStart(false);
            setStartGesetzt(false);

        }else if(!k.isStart() && k.isZiel() && startGesetzt && zielGesetzt){
            k.setFill(Color.TRANSPARENT);
            k.setZiel(false);
            setZielGesetzt(false);
        }else if(k.isStart() && !k.isZiel() && startGesetzt && !zielGesetzt){
            k.setFill(Color.TRANSPARENT);
            k.setStart(false);
            setStartGesetzt(false);
        }else if(k.isZiel() && !k.isStart() && !startGesetzt && zielGesetzt){
            k.setFill(Color.TRANSPARENT);
            k.setZiel(false);
            setZielGesetzt(false);
        }else if(!k.isZiel() && !k.isStart() && !startGesetzt && zielGesetzt) {
            k.setFill(Color.RED);
            k.setStart(true);
            setStartGesetzt(true);
            k.setBelegt(false);
        }



    }

    public void linksKlick(Kaestchen k){

        if (k.isBelegt()) {
            k.setBelegt(false);
            k.setFill(Color.TRANSPARENT);
            System.out.println("nicht belegt");
        } else {
            k.setBelegt(true);
            k.setFill(Color.BLUE);
            System.out.println("belegt");
        }
    }
    public Kaestchen[] getNachbarn(Kaestchen k){
        int i = k.koordI;
        int j = k.koordJ;
        Kaestchen[] nachbarn = new Kaestchen[4];
        nachbarn[0] = raster[i][j-20];
        nachbarn[1] = raster[i+20][j];
        nachbarn[2] = raster[i][j+20];
        nachbarn[3] = raster[i-20][j];
        return nachbarn;
    }
    public void setzePotential(Kaestchen k){
        Kaestchen[] nachbarn = getNachbarn(k);
            try {
                if (k.isBesucht()) {
                    int x = potential;


                    if (!nachbarn[0].isBesucht()) {
                        nachbarn[0].setWert(x);
                        nachbarn[0].setBesucht(true);
                        queue.add(nachbarn[0]);
                    }
                    if (!nachbarn[1].isBesucht()) {
                        nachbarn[1].setWert(x);
                        nachbarn[1].setBesucht(true);
                        queue.add(nachbarn[1]);
                    }
                    if (!nachbarn[2].isBesucht()) {
                        nachbarn[2].setWert(x);
                        nachbarn[2].setBesucht(true);
                        queue.add(nachbarn[2]);

                    }
                    if (!nachbarn[3].isBesucht()) {
                        nachbarn[3].setWert(x);
                        nachbarn[3].setBesucht(true);
                        queue.add(nachbarn[3]);

                    }
                    if(!queue.isEmpty()) {
                        queueBearbeiten();
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                //Grenze
            }

    }

    public void queueBearbeiten(){
        Kaestchen k = queue.poll();
        getPotential();
        setzePotential(k);

    }
    public void setPotential(int i, int j){
        try {
            int x = getPotential();
            if (raster[i][j].isStart()) {
                raster[i][j].setWert(x);
                x = getPotential();
                raster[i + 20][j].setWert(x);
                raster[i][j + 20].setWert(x);
                raster[i - 20][j].setWert(x);
                raster[i][j - 20].setWert(x);

            }
            if(raster[i][j].isBelegt()){
                raster[i][j].setWert(-2);

            }
            if(raster[i][j].getWert() > 0){
                x = getPotential();
                raster[i + 20][j].setWert(x);
                raster[i][j + 20].setWert(x);
                raster[i - 20][j].setWert(x);
                raster[i][j - 20].setWert(x);

            }if(raster[i][j].isZiel() && raster[i][j].getWert() > -1){

            }
        }catch (ArrayIndexOutOfBoundsException e){
            //Spielfeldgrenze erreicht
        }

    }

    public void setNachbarPotential(int ii, int jj){
        int k1 = raster[ii-1][jj].getWert();
        int k2 = raster[ii][jj+1].getWert();
        int k3 = raster[ii+1][jj].getWert();
        int k4 = raster[ii][jj-1].getWert();

    }
}
