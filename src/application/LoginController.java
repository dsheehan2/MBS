package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginController 
{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	AnchorPane anchor;
	@FXML
	Button loginButton;
	@FXML
	TextField username;
	@FXML
	PasswordField password;
	@FXML
	Label userLabel;
	@FXML
	Label passLabel;
	@FXML
	Label signUpLabel;
	@FXML
	Label newCustomerLabel;
	@FXML
	Label incorrectLabel;
	@FXML
	Label adminLabel;
	@FXML
	Label newAdminLabel;
	
	// RESET CSS SHEET FOR EACH SCENE SWITCH
	
	// CREATE MOVIE DATABASE OBJECT HERE
	
//	public String user;
//	public String pass;
	
	public void signUp(MouseEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("newSignUp.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void adminClick(MouseEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("AdminCode.fxml"));
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("adminLogin.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void loginPressed(ActionEvent event) throws IOException
	{
		// CALL IN IF: checkUserAndPass(user, pass);
		// AND: Set field variables to user and pass
		//if("user".equals(username.getText()) && "password".equals(password.getText()))
		if(Customer.checkUserAndPass(username.getText(), password.getText()))
		{
			MainMenuController.setUserInstance(username.getText());	
			
			root = FXMLLoader.load(getClass().getResource("BrowseAndSearch.fxml"));
			scene = new Scene(root);
			stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
			scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}
		
		else
		{
			username.clear();
			password.clear();
			incorrectLabel.setVisible(true);
		}
		
	}
	
	public void keyHit(KeyEvent event) throws IOException
	{
		if(event.getCode() == KeyCode.ENTER)
		{
			if(Customer.checkUserAndPass(username.getText(), password.getText()))
			{
				MainMenuController.setUserInstance(username.getText());	
				
				root = FXMLLoader.load(getClass().getResource("BrowseAndSearch.fxml"));
				scene = new Scene(root);
				stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
				scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			else
			{
				username.clear();
				password.clear();
				incorrectLabel.setVisible(true);
			}
		}
	}
	
	public void whenHover(MouseEvent event)
	{
		scene = ((Node) event.getSource()).getScene();
		scene.setCursor(Cursor.HAND);
	}
	
	public void whenLeave(MouseEvent event)
	{
		scene = ((Node) event.getSource()).getScene();
		scene.setCursor(Cursor.DEFAULT);
	}
	
	public void whenLabelClicked(MouseEvent event)
	{
		incorrectLabel.setVisible(false);
	}
}
