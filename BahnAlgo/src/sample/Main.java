package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,540,540);
        GridPane grid = new GridPane();
        GridPane grid2 = new GridPane();
        grid.setHgap(0.01);
        grid.setVgap(0.01);
        grid.setPadding(new Insets(0,10,0,10));
        Menu menu = new Menu("Aktion");
        MenuBar menuBar = new MenuBar();
        MenuItem item1 = new MenuItem("Starte Algorithmus");
        MenuItem item2 = new MenuItem("Zeige Potential");
        menu.getItems().addAll(item1,item2);
        menuBar.getMenus().add(menu);

        Feld feld = new Feld(500,500);
        for (int i = 0; i < 500; i=i+20) {
            for (int j = 0; j < 500; j=j+20) {
                feld.raster[i][j] = new Kaestchen(20,20,20,20,i,j);
                Kaestchen kaestchen = feld.raster[i][j];
                //grid.add(new Label(kaestchen.getWert()+""),i,j);
                grid.add(kaestchen,i,j);
                kaestchen.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        MouseButton mB = event.getButton();
                         if(mB.equals(MouseButton.PRIMARY)){
                               feld.linksKlick(kaestchen);
                            }
                         if(mB.equals(MouseButton.SECONDARY)){
                                feld.rechtsKlick(kaestchen);
                        }


                    }
                });
            }


        }
        root.setCenter(grid);
        root.setTop(menuBar);
        primaryStage.setTitle("Gitterpotential");
        primaryStage.setScene(scene);
        primaryStage.show();

        item1.setOnAction((event) ->{

            for (int i = 0; i < 500; i=i+20) {
                for (int j = 0; j < 500; j=j+20) {
                    try {
                        feld.setzePotential(feld.raster[i][j]);
                    }catch (ArrayIndexOutOfBoundsException e){
                        //Feldgrenze
                    }

                }
            }

        });


        item2.setOnAction((event -> {
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
