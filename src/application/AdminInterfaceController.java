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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class AdminInterfaceController 
{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	AnchorPane pane;
	@FXML
	Label startLabel;
	@FXML
	Label removeLabel;
	@FXML
	Label removeValidLabel;
	@FXML
	Label addValidLabel;
	@FXML
	RadioButton addRadio;
	@FXML
	RadioButton removeRadio;
	@FXML
	RadioButton upcomingRadio;
	@FXML
	RadioButton currentRadio;
	@FXML
	Button generateButton;
	@FXML
	Button confirmButton;
	@FXML
	Button addMovieButton;
	@FXML
	Button backButton;
	@FXML
	TextField removeField;
	@FXML
	TextField addName;
	@FXML
	TextField addRuntime;
	@FXML
	TextArea addSynopsisArea;
	@FXML
	TextArea addCastArea;
	
	private int upcomingOrNot;
	
	public void getDetails(ActionEvent event)
	{
		if(addRadio.isSelected())
		{
			removeLabel.setVisible(false);
			removeField.setVisible(false);
			confirmButton.setVisible(false);
			
			removeValidLabel.setVisible(false);
			
			addSynopsisArea.setVisible(true);
			addCastArea.setVisible(true);
			addName.setVisible(true);
			addRuntime.setVisible(true);
			addMovieButton.setVisible(true);
			upcomingRadio.setVisible(true);
			currentRadio.setVisible(true);
			
			addValidLabel.setVisible(false);
			
			removeField.clear();
			addSynopsisArea.clear();
			addCastArea.clear();
			addName.clear();
			addRuntime.clear();
			
		}
		if(removeRadio.isSelected())
		{
			addSynopsisArea.setVisible(false);
			addCastArea.setVisible(false);
			addName.setVisible(false);
			addRuntime.setVisible(false);
			addMovieButton.setVisible(false);
			upcomingRadio.setVisible(false);
			currentRadio.setVisible(false);
			
			addValidLabel.setVisible(false);
			
			removeLabel.setVisible(true);
			removeField.setVisible(true);
			confirmButton.setVisible(true);
			
			removeValidLabel.setVisible(false);
			
			removeField.clear();
			addSynopsisArea.clear();
			addCastArea.clear();
			addName.clear();
			addRuntime.clear();
		}
	}
	
	public void confirmPressed(ActionEvent event) throws IOException
	{
		String movieName = removeField.getText();
		if(movieName.isEmpty())
		{
			removeValidLabel.setText("Movie Invalid");
			removeValidLabel.setTextFill(Color.RED);
			removeValidLabel.setVisible(true);
			removeField.clear();
		}
		// CALL IN ELSE-IF: removeMovie(movieName)
		else
		{
			Movie.removeMovie("Movie Name: " + movieName.trim()); // Proper Structure to work!
			removeValidLabel.setText("Movie Removed");
			removeValidLabel.setTextFill(Color.GREEN);
			removeValidLabel.setVisible(true);
			removeField.clear();
		}
	}
	
	public void addMoviePressed(ActionEvent event) throws IOException
	{
		if(addSynopsisArea.getText().isEmpty() || addCastArea.getText().isEmpty() || addName.getText().isEmpty() || addRuntime.getText().isEmpty())
		{
			addValidLabel.setText("Fill All Fields");
			addValidLabel.setTextFill(Color.RED);
			addValidLabel.setVisible(true);
		}
		else
		{
			// CALL: addMovie(String addName.getText(), addSynopsisArea.getText(), addCastArea.getText(), addRuntime.getText());
			Movie.addMovie(upcomingOrNot, addName.getText().trim(), addSynopsisArea.getText().replace("\n", " ").trim(), addCastArea.getText().replace("\n", " ").trim(), addRuntime.getText().trim());
			
			addSynopsisArea.clear();
			addCastArea.clear();
			addName.clear();
			addRuntime.clear();
			
			addValidLabel.setText("Movie Added");
			addValidLabel.setTextFill(Color.GREEN);
			addValidLabel.setVisible(true);
		}
	}
	
	public void removeFieldPressed(MouseEvent event) throws IOException
	{
		removeField.clear();
		removeValidLabel.setVisible(false);
	}
	
	public void addFieldPressed(MouseEvent event) throws IOException
	{
		addValidLabel.setVisible(false);
	}
	
	public void radioDecision(ActionEvent event) throws IOException
	{
		if(upcomingRadio.isSelected())
			upcomingOrNot = 1;
			
		if(currentRadio.isSelected())
			upcomingOrNot = 0;
	}
	
	public void genPressed(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("genReport.fxml"));
		root = loader.load();
		GenerateReportController pay = loader.getController();
		pay.genReport();
		
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("generateStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}
	
	public void backButtonPressed(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("AdminCode.fxml"));
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("adminLogin.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
