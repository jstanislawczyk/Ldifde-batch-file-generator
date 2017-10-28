package com.ldifde.ldifdeGenerator;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class MenuController {
	
	private MainController mainController;
	
	@FXML
	public void openGeneratorStage() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/GeneratorStage.fxml"));
		Pane pane = null;

		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		GeneratorController generatorController = loader.getController();
		generatorController.setMainController(this.mainController);
		mainController.setScreen(pane);
	}
	
	@FXML
	private void exitApplication() {
		Platform.exit();
	}
	
	public void setMainController(MainController mainController){
		this.mainController = mainController;
	}	
}
