package application;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainMenuController 
{
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	AnchorPane pane;
	@FXML
	Label mainMenuLabel;
	@FXML
	Button logoutButton;
	@FXML
	Button checkoutButton;
	@FXML
	Button browseButton;
	@FXML
	Button viewTicketsButton;
	
	private static String username = "";
	private static List<String> moviesToBuy = new ArrayList<String>();
	private static double totalPrice = 0.0;
	
	public void logOutPressed(ActionEvent event) throws IOException
	{
		Alert error = new Alert(AlertType.CONFIRMATION);
		error.setTitle("Are You Sure?");
		error.setHeaderText("You Are About to Log Out!");
		error.setContentText("Your Cart will be cleared once you log out!");

		Window alertWindow = error.getDialogPane().getScene().getWindow();
		alertWindow.setOnCloseRequest(e -> e.consume()); // Calls abstract method handle(WindowEvent e);
		
		if(error.showAndWait().get() == ButtonType.OK)
		{
			setUserInstance("");
			clearPriceMovies();
			
			root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			scene = new Scene(root);
			stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}
	}
	
	
	// DIRECT METHODS TO FILL
	
	public void checkOutPressed(ActionEvent event) throws IOException
	{
		// TEST WORKS: System.out.println(MainMenuController.getUserInstance());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Payment.fxml"));
		root = loader.load();
		PaymentController pay = loader.getController();
		pay.displayMovieTickets();
		
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("paymentStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void showTicketsPressed(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketScreen.fxml"));
		root = loader.load();
		ShowTicketController show = loader.getController();
		show.displayShowTickets();
		
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("ticketShow.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void browseMoviesPressed(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FullMovie.fxml"));
		root = loader.load();
		MovieInteractionController quick = loader.getController();
		quick.quickSet();
		
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("interaction.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();		
	}
	
	
	
	// NEEDED METHODS
	
	public static String getUserInstance()
	{
		return username.trim();
	}
	
	public static void setUserInstance(String str)
	{
		username = str.trim();
	}
	
	public static String getMoviesBought()
	{
		String temp = "";
		for(String movie: moviesToBuy)
			temp = temp + movie + " Ticket\n";
		
		return temp;
	}
	
	public static List<String> getMovieList()
	{
		return moviesToBuy;
	}
	
	public static void addMoviesBought(String str)
	{
		moviesToBuy.add(str);
	}
	
	public static void accumPrice()
	{
		Random random = new Random();
		totalPrice = totalPrice + random.nextDouble(10) + 10;
		String dbl = new DecimalFormat("#.##").format(totalPrice);
		totalPrice = Double.parseDouble(dbl);
	}
	
	public static double getPrice()
	{
		return totalPrice;
	}
	
	public static void clearPriceMovies()
	{
		totalPrice = 0.0;
		moviesToBuy.clear();
	}
}
