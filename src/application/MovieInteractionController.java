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

public class MovieInteractionController 
{
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	AnchorPane pane;
	@FXML
	TextField searchbar;
	@FXML
	TextField viewbar;
	@FXML
	Button searchButton;
	@FXML
	Button viewButton;
	@FXML
	Button backButton;
	@FXML
	TextArea resultArea;
	@FXML
	RadioButton currentButton;
	@FXML
	RadioButton upcomingButton;
	@FXML
	Label errorLabel;
	
	public void quickSet()
	{
		resultArea.clear();
		resultArea.setEditable(false);
		errorLabel.setVisible(false);
	}
	
	public void searchButtonPressed(ActionEvent event) throws IOException
	{
		resultArea.clear();
		viewbar.clear();
		errorLabel.setVisible(false);
		if(Movie.searchAndDisplayMovie(searchbar.getText()).equals("INVALID"))
			resultArea.setText("No Movie Found!");
		else
			resultArea.setText(Movie.searchAndDisplayMovie(searchbar.getText().trim()));
		searchbar.clear();
	}
	
	public void keyHit(KeyEvent event) throws IOException
	{
		if(event.getCode() == KeyCode.ENTER)
		{
			resultArea.clear();
			errorLabel.setVisible(false);
			if(Movie.searchAndDisplayMovie(searchbar.getText()).equals("INVALID"))
				resultArea.setText("No Movie Found!");
			else
				resultArea.setText(Movie.searchAndDisplayMovie(searchbar.getText().trim()));
			searchbar.clear();
		}
	}
	
	public void choice(ActionEvent event) throws IOException
	{
		resultArea.clear();
		searchbar.clear();
		viewbar.clear();
		errorLabel.setVisible(false);
		
		if(currentButton.isSelected())
		{
			resultArea.setText(Movie.displayCurrentMovies()); 
		}
		if(upcomingButton.isSelected())
		{
			resultArea.setText(Movie.displayUpcomingMovies()); 
		}
	}
	
	public void viewButtonClicked(ActionEvent event) throws IOException
	{
		if(Movie.searchAndDisplayMovie(viewbar.getText()).equals("INVALID"))
		{
			errorLabel.setVisible(true);
			viewbar.clear();
		}
		else
		{
			errorLabel.setVisible(false);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewMovie.fxml"));
			root = loader.load();
			ViewMovieController tst = loader.getController();
			tst.setBox(viewbar.getText().trim(), Movie.getCode(viewbar.getText()));
			
			scene = new Scene(root);
			stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
			scene.getStylesheets().add(getClass().getResource("viewMovie.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}
		searchbar.clear();
	}
	
	public void keyHit2(KeyEvent event) throws IOException
	{
		if(event.getCode() == KeyCode.ENTER)
		{
			if(Movie.searchAndDisplayMovie(viewbar.getText()).equals("INVALID"))
			{
				errorLabel.setVisible(true);
				viewbar.clear();
			}
			else
			{
				errorLabel.setVisible(false);
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewMovie.fxml"));
				root = loader.load();
				ViewMovieController tst = loader.getController();
				tst.setBox(viewbar.getText().trim(), Movie.getCode(viewbar.getText()));
				
				scene = new Scene(root);
				stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
				scene.getStylesheets().add(getClass().getResource("viewMovie.css").toExternalForm());
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			searchbar.clear();
		}		
	}
	
	public void quickClear(ActionEvent event) throws IOException
	{
		errorLabel.setVisible(false);
	}
	
	public void backButtonPressed(ActionEvent event) throws IOException
	{
		resultArea.clear();
		searchbar.clear();
		errorLabel.setVisible(false);
		
		root = FXMLLoader.load(getClass().getResource("BrowseAndSearch.fxml"));
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
