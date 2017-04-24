package sample;

import com.sun.deploy.util.ArrayUtil;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
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
    Queue<Kaestchen> queue;
    Kaestchen[][] raster;

    public Feld(int x, int y) {
        this.groesseX = x;
        this.groesseY = y;
        raster = new Kaestchen[groesseX][groesseY];
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

    public void setZielGesetzt(boolean zielGesetzt) {
        this.zielGesetzt = zielGesetzt;
    }

    public void rechtsKlick(Kaestchen k) {

        if (!k.isZiel() && !k.isStart() && !startGesetzt && !zielGesetzt) {
            k.setStart(true);
            setStartGesetzt(true);
            k.setFill(Color.RED);
            System.out.println("Start gesetzt:" + startGesetzt);
            System.out.println("Ziel gesetzt: " + zielGesetzt);
            k.setBelegt(false);
        } else if (!k.isStart() && !k.isZiel() && startGesetzt && !zielGesetzt) {
            setZielGesetzt(true);
            k.setZiel(true);
            k.setFill(Color.GREEN);
            System.out.println("Ziel gesetzt: " + zielGesetzt);
            System.out.println("start:" + startGesetzt);
            k.setBelegt(false);
        } else if (!k.isZiel() && k.isStart() && startGesetzt && zielGesetzt) {
            k.setFill(Color.TRANSPARENT);
            k.setStart(false);
            setStartGesetzt(false);

        } else if (!k.isStart() && k.isZiel() && startGesetzt && zielGesetzt) {
            k.setFill(Color.TRANSPARENT);
            k.setZiel(false);
            setZielGesetzt(false);
        } else if (k.isStart() && !k.isZiel() && startGesetzt && !zielGesetzt) {
            k.setFill(Color.TRANSPARENT);
            k.setStart(false);
            setStartGesetzt(false);
        } else if (k.isZiel() && !k.isStart() && !startGesetzt && zielGesetzt) {
            k.setFill(Color.TRANSPARENT);
            k.setZiel(false);
            setZielGesetzt(false);
        } else if (!k.isZiel() && !k.isStart() && !startGesetzt && zielGesetzt) {
            k.setFill(Color.RED);
            k.setStart(true);
            setStartGesetzt(true);
            k.setBelegt(false);
        }


    }

    public void linksKlick(Kaestchen k) {

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

    public Kaestchen[] getNachbarn(Kaestchen k) {
        int i = k.getKoordI();
        int j = k.getKoordJ();
        Kaestchen[] nachbarn = new Kaestchen[4];
        nachbarn[0] = raster[i][j - 20];
        nachbarn[1] = raster[i + 20][j];
        nachbarn[2] = raster[i][j + 20];
        nachbarn[3] = raster[i - 20][j];
        return nachbarn;
    }

    public Kaestchen[] getAlleNachbarn(Kaestchen k) {
        int i = k.getKoordI();
        int j = k.getKoordJ();
        Kaestchen[] nachbarn = new Kaestchen[8];
        nachbarn[0] = raster[i][j - 20];
        nachbarn[1] = raster[i + 20][j - 20];
        nachbarn[2] = raster[i + 20][j];
        nachbarn[3] = raster[i + 20][j + 20];
        nachbarn[4] = raster[i][j + 20];
        nachbarn[5] = raster[i - 20][j + 20];
        nachbarn[6] = raster[i - 20][j];
        nachbarn[7] = raster[i - 20][j - 20];
        return nachbarn;
    }

    public void setzePotential(Kaestchen k) {
        System.out.println(k.getKoordI() + "," + k.getKoordJ());
        if (k.isZiel()) {
            addKaestchenToQueue(k);
            System.out.println("add Zielpunkt");
        } else if (!queue.isEmpty()) {
            System.out.println("queue not empty");
            while (!queue.isEmpty()) {
                queueBearbeiten();

            }

        }
    }

    public void addKaestchenToQueue(Kaestchen k) {
        try {

            Kaestchen[] nachbarn = getNachbarn(k);
            if (!nachbarn[0].isBesucht() && !nachbarn[0].isBelegt()) {
                nachbarn[0].setWert(k.getWert() + 1);
                queue.add(nachbarn[0]);
                System.out.println(" add nachbar 0");
            }
            if (!nachbarn[1].isBesucht() && !nachbarn[1].isBelegt()) {
                nachbarn[1].setWert(k.getWert() + 1);
                queue.add(nachbarn[1]);
                System.out.println("add nachbar 1");
            }
            if (!nachbarn[2].isBesucht() && !nachbarn[2].isBelegt()) {
                nachbarn[2].setWert(k.getWert() + 1);
                queue.add(nachbarn[2]);
                System.out.println("add nachbar 2");
            }
            if (!nachbarn[3].isBesucht() && !nachbarn[3].isBelegt()) {
                nachbarn[3].setWert(k.getWert() + 1);
                queue.add(nachbarn[3]);
                System.out.println("add nachbar 3");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            //Feldgrenze
        }
    }

    public void queueBearbeiten() {
        try {
            Kaestchen k = queue.poll();
            System.out.println("queue: " + k.getKoordI() + "," + k.getKoordJ());


            if (!k.isBesucht()) {
                k.setBesucht(true);
                addKaestchenToQueue(k);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            //Grenze
        }

    }

    public void findeWeg(Kaestchen k) {
        if (k.isStart()) {
            k.setAbgearbeitet(true);
            queue.add(getNextKaestchen(k));
        } else if (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                zeichnePfad();
            }

        }
    }

    public void zeichnePfad() {
        try {
            Kaestchen k = queue.poll();
            System.out.println("queue: " + k.getKoordI() + "," + k.getKoordJ());


            if (!k.isAbgearbeitet() && !k.isZiel() && !k.isBelegt() ) {
                k.setAbgearbeitet(true);
                k.setFill(Color.YELLOW);
                queue.add(getNextKaestchen(k));
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            //Grenze
        }
    }

    public int getDifferenz(Kaestchen k1, Kaestchen k2) {
        if (k1.isBelegt() || k2.isBelegt()) {
            return 0;
        } else {
            int diff = k2.getWert() - k1.getWert();
            return diff;
        }
    }

    public Kaestchen getNextKaestchen(Kaestchen k) {
        Kaestchen[] kaestchen = getAlleNachbarn(k);
        Kaestchen nextK = k;
        int maxDiff = 0;
        for (int i = 0; i < kaestchen.length; i++) {

            if (getDifferenz(k, kaestchen[i]) < maxDiff) {
                System.out.println(getDifferenz(k, kaestchen[i]));
                maxDiff = getDifferenz(k, kaestchen[i]);
                nextK = kaestchen[i];
            }
        }
        return nextK;
    }
}
