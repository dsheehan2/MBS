package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class PaymentController 
{
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	AnchorPane pane;
	@FXML
	Button backButton;
	@FXML
	Button payNowButton;
	@FXML
	Button purchaseButton;
	@FXML
	Label paymentLabel;
	@FXML
	Label totalLabel;
	@FXML
	Label paidLabel;
	@FXML
	Label errorLabel;
	@FXML
	RadioButton cardRadio;
	@FXML
	RadioButton paypalRadio;
	@FXML
	TextField paypalUserField;
	@FXML
	TextField cardUserLabel;
	@FXML
	TextField cardNumLabel;
	@FXML
	TextField cardExpLabel;
	@FXML
	TextField cardCVVLabel;
	@FXML
	TextField cardZipLabel;
	@FXML
	PasswordField paypalPassField;
	@FXML
	TextArea movieFlow;
	
	public void displayMovieTickets()
	{
		paypalUserField.setVisible(false);
		paypalPassField.setVisible(false);
		payNowButton.setVisible(false);
		paidLabel.setVisible(false);
		errorLabel.setVisible(false);
		
		cardUserLabel.setVisible(false);
		cardNumLabel.setVisible(false);
		cardExpLabel.setVisible(false);
		cardCVVLabel.setVisible(false);
		cardZipLabel.setVisible(false);
		purchaseButton.setVisible(false);
		
		pane.setBackground(Background.fill(Color.LIGHTBLUE));
		movieFlow.setEditable(false);
		movieFlow.setText(MainMenuController.getUserInstance() + "'s Cart:\n" + MainMenuController.getMoviesBought());
		totalLabel.setText("Your Total is $" + MainMenuController.getPrice());
		/*
		OLD VERSION Using TextFlow
		---------------------------
		@FXML
		TextFlow movieFlow;
		
		movieFlow.setBorder(new Border(new BorderStroke(Color.web("#7dcfb6"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(7))));
		movieFlow.setTextAlignment(TextAlignment.CENTER);
		Label ticketLabel = new Label(MainMenuController.getUserInstance() + "'s Cart");
		ticketLabel.setFont(Font.font("Verdana", 16));
		ticketLabel.setTextFill(Color.BLACK);
		ticketLabel.setUnderline(true);
		Label moviesToBuy = new Label(MainMenuController.getMoviesBought());
		moviesToBuy.setFont(Font.font("Verdana"));
		moviesToBuy.setTextFill(Color.BLACK);
		
		totalLabel.setText("Your Total is $" + MainMenuController.getPrice());
		movieFlow.getChildren().add(ticketLabel);
		movieFlow.getChildren().add(moviesToBuy);
		*/
	}
	
	public void radioClicked(ActionEvent event) throws IOException
	{
		if(cardRadio.isSelected())
		{
			paypalUserField.setVisible(false);
			paypalPassField.setVisible(false);
			payNowButton.setVisible(false);
			paidLabel.setVisible(false);
			errorLabel.setVisible(false);
			
			paypalUserField.clear();
			paypalPassField.clear();
			
			cardUserLabel.setVisible(true);
			cardNumLabel.setVisible(true);
			cardExpLabel.setVisible(true);
			cardCVVLabel.setVisible(true);
			cardZipLabel.setVisible(true);
			purchaseButton.setVisible(true);
		}
		if(paypalRadio.isSelected()) 
		{
			cardUserLabel.setVisible(false);
			cardNumLabel.setVisible(false);
			cardExpLabel.setVisible(false);
			cardCVVLabel.setVisible(false);
			cardZipLabel.setVisible(false);
			purchaseButton.setVisible(false);
			paidLabel.setVisible(false);
			errorLabel.setVisible(false);
			
			cardUserLabel.clear();
			cardNumLabel.clear();
			cardExpLabel.clear();
			cardCVVLabel.clear();
			cardZipLabel.clear();
			
			paypalUserField.setVisible(true);
			paypalPassField.setVisible(true);
			payNowButton.setVisible(true);
			
		}
	}
	
	public void payNowButtonClicked(ActionEvent event) throws IOException
	{
		if(MainMenuController.getPrice() != 0.0 && !(paypalUserField.getText().isEmpty()) && !(paypalPassField.getText().isEmpty()))
		{
			String payPalUsername = paypalUserField.getText().trim();
			String payPalPassword = paypalPassField.getText().trim();
			
			errorLabel.setVisible(false);
			paidLabel.setVisible(true);
			paypalUserField.clear();
			paypalPassField.clear();
			
			//CALL: payByPayPal(MainMenuController.getUserInstance(), payPalUsername, payPalPassword);
			Customer.payByPaypal(MainMenuController.getUserInstance(), payPalUsername, payPalPassword);
			for(int i = 0; i < MainMenuController.getMovieList().size(); i++)
				Movie.IterateTicket(MainMenuController.getMovieList().get(i).trim());
			
			userTicketWrite();
			MainMenuController.clearPriceMovies(); // FLUSH
			
			movieFlow.setText(MainMenuController.getUserInstance() + "'s Cart:\n" + MainMenuController.getMoviesBought());
			totalLabel.setText("Your Total is $" + MainMenuController.getPrice());
		}
		else
		{
			paidLabel.setVisible(false);
			errorLabel.setVisible(true);
		}
	}
	
	public void purchaseButtonClicked(ActionEvent event) throws IOException
	{
		if(MainMenuController.getPrice() != 0.0 && !(cardUserLabel.getText().isEmpty()) && !(cardNumLabel.getText().isEmpty())
				&& !(cardExpLabel.getText().isEmpty()) && !(cardCVVLabel.getText().isEmpty()) && !(cardZipLabel.getText().isEmpty())
				&& (cardCVVLabel.getText().trim().length() == 3) && (cardZipLabel.getText().trim().length() == 5))
		{
			try
			{
				// TESTERS OF TRY-CATCH --> TESTS TO SEE IF ALL ARE INTS
				Integer.parseInt(cardNumLabel.getText().trim());
				Integer.parseInt(cardCVVLabel.getText().trim());
				Integer.parseInt(cardZipLabel.getText().trim());
				
				String name = cardUserLabel.getText().trim();
				String number = cardNumLabel.getText().trim();
				String exp = cardExpLabel.getText().trim();
				String cvv = cardCVVLabel.getText().trim();
				String zip = cardZipLabel.getText().trim();
				
				errorLabel.setVisible(false);
				paidLabel.setVisible(true);
				cardUserLabel.clear();
				cardNumLabel.clear();
				cardExpLabel.clear();
				cardCVVLabel.clear();
				cardZipLabel.clear();
				
				//CALL: payByCard(String MainMenuController.getUserInstance(), String number, String name, String exp, String cvv, String zip);
				Customer.payByCard(MainMenuController.getUserInstance(), number, name, exp, cvv, zip);
				for(int i = 0; i < MainMenuController.getMovieList().size(); i++)
					Movie.IterateTicket(MainMenuController.getMovieList().get(i).trim());
				
				userTicketWrite();
				MainMenuController.clearPriceMovies(); // FLUSH
				
				movieFlow.setText(MainMenuController.getUserInstance() + "'s Cart:\n" + MainMenuController.getMoviesBought());
				totalLabel.setText("Your Total is $" + MainMenuController.getPrice());
			}
			catch(Exception e)
			{
				paidLabel.setVisible(false);
				errorLabel.setVisible(true);
			}
		}
		else
		{
			paidLabel.setVisible(false);
			errorLabel.setVisible(true);
		}
	}
	
	public void backButtonClicked(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("BrowseAndSearch.fxml"));
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void userTicketWrite() 
	{
		BufferedWriter writer = null;
		BufferedReader reader = null;;
		try 
		{
			reader = new BufferedReader(new FileReader(new File("MovieTickets.txt")));
			List<String> movies = MainMenuController.getMovieList();
			List<String> quickWrite = new ArrayList<String>();
			String str = "";
			
			while((str = reader.readLine()) != null)
				quickWrite.add(str);
			reader.close();

			StringBuilder f = new StringBuilder();
			f.append(MainMenuController.getUserInstance() + ":  ");
			for(int i = 0; i < movies.size(); i++)
				f.append(movies.get(i) + " " + getRandomNumberString() + ", ");
			
			str = f.substring(0, f.length() - 2);
			f.setLength(0);
			quickWrite.add(str);
		
			writer = new BufferedWriter(new FileWriter(new File("MovieTickets.txt")));
			str = "";
			for(int i = 0; i < quickWrite.size(); i++)
				str += quickWrite.get(i) + "\n";
			
			writer.write(str);
			writer.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// 6 Digit Number Generator to String
	public static String getRandomNumberString() 
	{
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return String.format("%06d", number);
	}
}
