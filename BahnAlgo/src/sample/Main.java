package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private int groesseX = 500;
    private int groesseY = 500;
    private Feld feld;
    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();
        HBox hbox = new HBox();
        Scene scene = new Scene(root,groesseX+170,groesseY+50);
        GridPane grid = new GridPane();
        grid.setHgap(0.01);
        grid.setVgap(0.01);
        grid.setPadding(new Insets(0,10,0,10));

        Button startAlgorithm = new Button("Starte Algorithmus");
        Button showPotential = new Button("Zeige Potential");
        VBox gitter = new VBox();
        VBox menu = new VBox();
        Label groesse = new Label("Größe: ");
        gitter.getChildren().add(grid);
        TextField textFieldGroesse= new TextField();
        menu.getChildren().addAll(groesse,textFieldGroesse,startAlgorithm,showPotential);
        hbox.getChildren().addAll(gitter,menu);


        feld = new Feld(groesseX,groesseY);
        for (int i = 0; i < feld.groesseX; i=i+20) {
            for (int j = 0; j < feld.groesseY; j=j+20) {
                feld.raster[i][j] = new Kaestchen(20,20,20,20,i,j);
                Kaestchen kaestchen = feld.raster[i][j];
                grid.add(kaestchen,i,j);

                kaestchen.setOnMouseClicked(event -> {
                        MouseButton mB = event.getButton();
                        if(mB.equals(MouseButton.PRIMARY)){

                             feld.linksKlick(kaestchen);
                            }
                         if(mB.equals(MouseButton.SECONDARY)){
                                feld.rechtsKlick(kaestchen);
                        }



                });
            }


        }
        root.setCenter(hbox);
        primaryStage.setTitle("Gitterpotential");
        primaryStage.setScene(scene);
        primaryStage.show();

        textFieldGroesse.setOnKeyPressed(event ->{
                if(event.getCode() == KeyCode.ENTER) {
                    System.out.println("test");
                    groesseX = Integer.parseInt(textFieldGroesse.getText());
                    groesseY = Integer.parseInt(textFieldGroesse.getText());
                    feld = new Feld(groesseX,groesseY);
                    try {
                        start(primaryStage);
                    }catch (Exception e){

                    }
                }
        });


        startAlgorithm.setOnAction((event) ->{

            for (int i = 0; i < feld.groesseX; i=i+20) {
                for (int j = 0; j < feld.groesseY; j=j+20) {
                    try {
                        feld.setzePotential(feld.raster[i][j]);
                    }catch (ArrayIndexOutOfBoundsException e){
                        //Feldgrenze
                    }

                }
            }

            for (int i = 0; i < feld.groesseX; i=i+20) {
                for (int j = 0; j < feld.groesseY; j=j+20) {
                    try {
                        feld.findeWeg(feld.raster[i][j]);
                    }catch (ArrayIndexOutOfBoundsException e){
                        //Feldgrenze
                    }

                }
            }


        });


        showPotential.setOnAction((event -> {
            for (int i = 0; i < feld.groesseX; i=i+20) {
                for (int j = 0; j < feld.groesseY; j=j+20) {
                    Kaestchen k = feld.raster[i][j];
                    grid.add(new Label(k.getWert()+""),i,j);
                }
            }

        }));
    }





    public static void main(String[] args) {
        launch(args);
    }
}
