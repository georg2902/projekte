package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

/**
 * Created by Georg on 30.03.2017.
 */
public class Kaestchen extends Rectangle implements Serializable{
    private boolean belegt;
    private boolean start;
    private boolean ziel;
    private boolean abgearbeitet;
    private int wert;
    private boolean besucht;
    private int koordI;
    private int koordJ;

    public int getKoordI() {
        return koordI;
    }

    public int getKoordJ() {
        return koordJ;
    }


    public boolean isBesucht() {
        return besucht;
    }

    public void setBesucht(boolean besucht) {
        this.besucht = besucht;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;

    }

    public boolean isZiel() {
        return ziel;
    }

    public int getWert() {
        return wert;
    }

    public void setWert(int wert) {
        this.wert = wert;

    }

    public boolean isAbgearbeitet() {
        return abgearbeitet;
    }

    public void setAbgearbeitet(boolean abgearbeitet) {
        this.abgearbeitet = abgearbeitet;
    }

    public void setZiel(boolean ziel) {
        this.ziel = ziel;
        this.besucht = true;
        this.wert = 0;
    }

    public boolean isBelegt() {
        return belegt;
    }

    public void setBelegt(boolean belegt) {
        this.belegt = belegt;
    }

    public Kaestchen(int x1, int y1, int x2, int y2, int i, int j) {
        this.setX(x1);
        this.setY(y1);
        this.setHeight(x2);
        this.setWidth(y2);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(1);
        this.wert = -1;
        this.koordI = i;
        this.koordJ = j;

    }


}
