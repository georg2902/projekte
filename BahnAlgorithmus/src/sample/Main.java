package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.lang.reflect.Array;

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
                feld.raster[i][j] = new Kaestchen(20,20,20,20);
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
