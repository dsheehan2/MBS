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

public class ShowTicketController 
{
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	AnchorPane pane;
	@FXML
	TextArea tickets;
	@FXML
	Button backButton;
	@FXML
	Label ticketLabel;
	@FXML
	Label statusLabel;
	
	public void displayShowTickets()
	{
		tickets.setEditable(false);
		
		List<String> tik = userTicketRead();
		String str = "";
		if(tik == null)
			tickets.setText("No Tickets Purchased for " + MainMenuController.getUserInstance());
		else
		{
			for(int i = 0; i < tik.size(); i++)
				str+= tik.get(i) + "\n";
			tickets.setText(str);
		}
	}
	
	// YOU CANNOT REMOVE ALL YOUR TICKETS! Only from file
	public List<String> userTicketRead()
	{
		BufferedReader reader;
		List<String> finTicketsFINAL = new ArrayList<String>();
			
		try
		{
			reader = new BufferedReader(new FileReader(new File("MovieTickets.txt")));
			String str = "";
			
			while((str = reader.readLine()) != null)
			{
				if(str.contains(MainMenuController.getUserInstance()))
				{
					Scanner src = new Scanner(str);
					src.next();
					String cutter = "";
					while(src.hasNext())
						cutter += src.next() + " ";
					
					String[] finTickets = cutter.split(", ");
					for(int j = 0; j < finTickets.length; j++)
						finTicketsFINAL.add(finTickets[j]);
					
					src.close();
				}
			}
			reader.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return finTicketsFINAL;
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
}
