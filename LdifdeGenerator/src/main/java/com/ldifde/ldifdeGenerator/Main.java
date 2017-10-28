package com.ldifde.ldifdeGenerator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage pStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainStage.fxml"));
		StackPane stackPane = loader.load();
		Scene scene = new Scene(stackPane, 500, 600);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Generator ldifde");
		primaryStage.show();
		this.pStage = primaryStage;
	}

	public static Stage getpStage() {
		return pStage;
	}

	public static void setpStage(Stage pStage) {
		Main.pStage = pStage;
	}

}
