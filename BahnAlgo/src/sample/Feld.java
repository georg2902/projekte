package sample;

import javafx.scene.paint.Color;

/**
 * Created by Georg on 04.04.2017.
 */
public class Feld {
    int groesseX;
    int groesseY;
    private boolean startGesetzt;
    private boolean zielGesetzt;
    int potential;

    Kaestchen[][] raster;

    public Feld(int x, int y){
        this.groesseX = x;
        this.groesseY = y;
        raster = new Kaestchen[groesseX][groesseY];
        this.potential=-1;
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
