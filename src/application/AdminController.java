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

public class AdminController 
{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	AnchorPane anchor;
	@FXML
	Label adminLabel;
	@FXML
	Label invalidLabel;
	@FXML
	Button backButton;
	@FXML
	Button goButton;
	@FXML
	PasswordField passField;
	
	
	public void backClick(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void goPressed(ActionEvent event) throws IOException
	{
		if(passField.getText().equals("5421"))
		{
			root = FXMLLoader.load(getClass().getResource("AdminManage.fxml"));
			scene = new Scene(root);
			stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
			scene.getStylesheets().add(getClass().getResource("adminInterface.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}
		else
		{
			passField.clear();
			invalidLabel.setVisible(true);
		}	
	}
	
	public void keyHit(KeyEvent event) throws IOException
	{
		if(event.getCode() == KeyCode.ENTER)
		{
			if(passField.getText().equals("5421"))
			{
				root = FXMLLoader.load(getClass().getResource("AdminManage.fxml"));
				scene = new Scene(root);
				stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
				scene.getStylesheets().add(getClass().getResource("adminInterface.css").toExternalForm());
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			else
			{
				passField.clear();
				invalidLabel.setVisible(true);
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
		invalidLabel.setVisible(false);
	}
}
