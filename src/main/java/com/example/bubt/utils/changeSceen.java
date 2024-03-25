package com.example.bubt.utils;


import com.example.bubt.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class changeSceen extends Application {

    private   String file ;
    private   String title;

    public  changeSceen(String file , String title)
    {
        this.title = title;
        this.file = file;
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(Main.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
