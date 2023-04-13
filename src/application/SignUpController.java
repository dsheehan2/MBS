package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.*;

public class SignUpController 
{
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	AnchorPane pane;
	@FXML
	Label createLabel;
	@FXML
	Label errorLabel;
	@FXML
	TextField nameField;
	@FXML
	TextField phoneField;
	@FXML
	TextField emailField;
	@FXML
	TextField usernameField;
	@FXML
	TextField passwordField;
	@FXML
	Button confirmButton;
	@FXML
	Button backButton;
	
	public String name;
	public long phoneNumber;
	public String email;
	public String username;
	public String password;
	
	
	public void confirmAccount(ActionEvent event) throws IOException
	{
		try
		{
			phoneNumber = Long.parseLong(phoneField.getText());
			
			if(nameField.getText().isEmpty() || phoneField.getText().isEmpty() || emailField.getText().isEmpty() 
					|| usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || usernameField.getText().matches(".*\\s.*"))
			{
				errorLabel.setText("Please Correct Empty Information Above!");
				errorLabel.setVisible(true);
			}
			else
			{
				errorLabel.setVisible(false);
				
				name = nameField.getText();
				email = emailField.getText();
				username = usernameField.getText();
				password = passwordField.getText();

				// CALL: createAccount(name, email, phoneNumber, password);
				Customer.createAccount(name, username, email, Long.toString(phoneNumber), password);
				
				Alert al = new Alert(AlertType.NONE, "Account Created", ButtonType.OK);
				root = FXMLLoader.load(getClass().getResource("Main.fxml"));
				scene = new Scene(root);
				stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				al.show();
				al.showAndWait();
			}
					
		}
		catch(Exception e)
		{
			errorLabel.setText("Invalid Phone Number, Please Correct!");
			errorLabel.setVisible(true);
			phoneField.clear();
		}
	}
	
	public void whenLabelClicked(MouseEvent event)
	{
		errorLabel.setVisible(false);
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
}
