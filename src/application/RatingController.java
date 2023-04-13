package application;

import java.io.IOException;
import java.util.ArrayList;

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
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class RatingController 
{
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	AnchorPane pane;
	@FXML
	TextArea text;
	@FXML
	Slider rating;
	@FXML
	Button writeButton;
	@FXML
	Button backButton;
	@FXML
	Label title;
	@FXML
	Label confirmLabel;
	@FXML
	Label errorLabel;
	
	public void writeReviewPressed(ActionEvent event) throws IOException
	{
		if(Movie.dupReview(MainMenuController.getUserInstance(), ViewMovieController.getCurrMovie()) && !(text.getText().isEmpty()))
		{
			Movie.writeReview(ViewMovieController.getCurrMovie(), MainMenuController.getUserInstance(), Double.toString(rating.getValue()), text.getText().replace("\n", " "));
			confirmLabel.setVisible(true);
			errorLabel.setVisible(false);
			text.clear();
		}
		else
		{
			errorLabel.setVisible(true);
			text.clear();
		}
	}
	
	public void backButtonPressed(ActionEvent event) throws IOException
	{
		confirmLabel.setVisible(false);
		errorLabel.setVisible(false);
		text.clear();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewMovie.fxml"));
		root = loader.load();
		ViewMovieController tst = loader.getController();
		tst.setBox(ViewMovieController.getCurrMovie(), ViewMovieController.getCurrCode());
		
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("viewMovie.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
