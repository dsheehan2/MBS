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

public class ViewMovieController 
{
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	AnchorPane pane;
	@FXML
	TextArea info;
	@FXML
	TextArea ratings;
	@FXML
	Slider slider;
	@FXML
	Button purchaseButton;
	@FXML
	Button writeButton;
	@FXML
	Label ticketLabel;
	@FXML
	Label reviewLabel;
	@FXML
	Label complete;
	
	private static String currMovie;
	private static int currCode;
	
	public void setBox(String mov, int code)
	{
		currCode = code;
		currMovie = mov;
		info.setText(Movie.viewMovie(mov));
		if(code == 0)
		{
			ticketLabel.setVisible(true);
			slider.setVisible(true);
			purchaseButton.setVisible(true);
			reviewLabel.setVisible(true);
			ratings.setVisible(true);
			writeButton.setVisible(true);
			complete.setVisible(false);
		}
		if(code == 1)
		{
			ticketLabel.setVisible(false);
			slider.setVisible(false);
			purchaseButton.setVisible(false);
			reviewLabel.setVisible(false);
			ratings.setVisible(false);
			writeButton.setVisible(false);
			complete.setVisible(false);
		}
		
		String str = "";
		ArrayList<String> it = Movie.reviewsForMovie(mov);
		
		for(int i = 0; i < it.size(); i++)
		{
			str += it.get(i);
		}
			
		try {
		ratings.setText("Average Rating: " + Movie.findRating(mov) + "\n\n" + str);
		}
		catch(IOException e){}
	}
	
	public void grabItemCount(ActionEvent event) throws IOException
	{
		double val = slider.getValue();
		for(int i = 0; i < val; i++)
		{
			MainMenuController.addMoviesBought(currMovie);
			MainMenuController.accumPrice();
		}
		complete.setVisible(true);
	}
	
	public void writeMoviePressed(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("RatingView.fxml"));
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("ratingStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void backButtonPressed(ActionEvent event) throws IOException
	{
		complete.setVisible(false);
		currMovie = "";
		currCode = -1;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FullMovie.fxml"));
		root = loader.load();
		MovieInteractionController ik = loader.getController();
		ik.quickSet();
		
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("interaction.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void delLabel(MouseEvent event) throws IOException
	{
		complete.setVisible(false);
	}
	
	public static String getCurrMovie()
	{
		return currMovie;
	}
	
	public static int getCurrCode()
	{
		return currCode;
	}
}
