package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Georg on 30.03.2017.
 */
public class Kaestchen extends Rectangle {
    private boolean belegt;
    private boolean start;
    private boolean ziel;
    private int wert;
    private boolean wertGesetzt;

    public boolean isWertGesetzt() {
        return wertGesetzt;
    }

    public void setWertGesetzt(boolean wertGesetzt) {
        this.wertGesetzt = wertGesetzt;
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
        this.wertGesetzt = true;
    }

    public void setZiel(boolean ziel) {
        this.ziel = ziel;
    }

    public boolean isBelegt() {
        return belegt;
    }

    public void setBelegt(boolean belegt) {
        this.belegt = belegt;
    }

    public Kaestchen(int x1, int y1, int x2, int y2) {
        this.setX(x1);
        this.setY(y1);
        this.setHeight(x2);
        this.setWidth(y2);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(1);
        this.wert = -1;


    }



}
