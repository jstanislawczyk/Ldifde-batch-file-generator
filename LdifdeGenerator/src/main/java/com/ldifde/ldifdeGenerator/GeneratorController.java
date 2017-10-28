package com.ldifde.ldifdeGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class GeneratorController {
	
	private MainController mainController;
	
	@FXML
	private void goToMenuStage() {
		mainController.loadMenuStage();
	}
	
	@FXML
	private void generateFile() {    
		File file = chooseFileDestination();     
        
        if(file != null){
            SaveFile(file);
            setLabelInvisible(errorLabel);
            setLabelVisible(successLabel);
        }else {
        	setLabelVisible(errorLabel);
        }
	}
	
	private File chooseFileDestination() {
		 FileChooser fileChooser = new FileChooser();
       
         FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
         fileChooser.getExtensionFilters().add(extentionFilter);

         File file = null;
         
         try {
        	 file = fileChooser.showSaveDialog(Main.getpStage());
         }catch(Exception e) {
        	 setLabelVisible(errorLabel);
         }   

         return file;
	}
	
	 private void SaveFile(File file){
		 try {
			saveAccountsToFile(file);
	     } catch (Exception e) {    	 
	    	 setLabelVisible(errorLabel);
	    	 e.printStackTrace();
	     }          
	}	
	 
	private void saveAccountsToFile(File file) {
		PrintWriter writer = null;
		 
		try {
			writer = new PrintWriter(file);
			
			String activeDirectoryPathValue = activeDirectoryPath.getText();
			String firstNameValue = firstName.getText();
			String initialsValue = initials.getText()+" ";
			String lastNameValue = lastName.getText();
			String emailValue = email.getText();
			String loginNameValue = loginName.getText();
			String accountDescriptionValue = accountDescription.getText();
			boolean isAccountActiveValue = isAccountActive.isSelected();
			String fullName=firstNameValue+" "+initialsValue+lastNameValue;			
			int firstAccountNumberValue = 1;
			int numberOfGeneratedAccountsValue = 1;
			
			try {
				firstAccountNumberValue = Integer.parseInt(firstAccountNumber.getText());
			}catch(Exception e) {
				firstAccountNumberValue=1;
			}
			
			try {
				numberOfGeneratedAccountsValue = Integer.parseInt(numberOfGeneratedAccounts.getText());
			}catch(Exception e) {
				numberOfGeneratedAccountsValue=1;
			}
			
			if(firstAccountNumberValue<1) {
				firstAccountNumberValue=1;
			}
			
			if(numberOfGeneratedAccountsValue<1) {
				numberOfGeneratedAccountsValue=1;
			}
			
			
			for(int i=firstAccountNumberValue;i<=numberOfGeneratedAccountsValue;i++) {
				if(!activeDirectoryPathValue.equals("")) {
					writer.println("DN: CN="+fullName+i+", "+activeDirectoryPathValue);
					writer.println("changetype: add");
					writer.println("CN: "+fullName+i);
				}	
					
				if(!accountDescriptionValue.equals("")) {	
					writer.println("description: "+accountDescriptionValue);
				}
				
					writer.println("objectClass: user");
					
				if(isAccountActiveValue) {
					writer.println("userAccountControl: 544");
				}else {
					writer.println("userAccountControl: 546");
				}
				
				writer.println("userPrincipalName: "+loginNameValue+i);
				writer.println("samAccountName: "+loginNameValue+i);
				writer.println("givenName: "+firstNameValue);
				writer.println("sn: "+lastNameValue+i);
				
				if(!emailValue.equals("")) {
					writer.println("mail: "+emailValue);
				}
				
				writer.println("");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 	
		writer.close();
	}
	
	private void setLabelVisible(Label label){
		label.setVisible(true);
	}
	 
	private void setLabelInvisible(Label label){
		label.setVisible(false);
	}
	
	public void setMainController(MainController mainController){
		this.mainController = mainController;
	}
	
	//All form fields
	@FXML private TextField activeDirectoryPath;
	@FXML private TextField firstName;
	@FXML private TextField initials;
	@FXML private TextField lastName;
	@FXML private TextField email;
	@FXML private TextField loginName;
	@FXML private TextField firstAccountNumber;
	@FXML private TextField numberOfGeneratedAccounts;
	@FXML private TextField accountDescription;
	@FXML private CheckBox isAccountActive;
	
	//other fxml objects
	@FXML private Button buttonSave;
	@FXML private Label errorLabel;	
	@FXML private Label successLabel;
}
