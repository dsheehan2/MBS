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

public class GenerateReportController 
{
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	AnchorPane pane;
	@FXML
	Label currentLabel;
	@FXML
	TextArea currentArea;
	@FXML
	Button backButton;
	
	public void genReport()
	{
		// CALL: generateReport()
		// AND:  Create new FXML doc
		currentArea.setEditable(false);
		currentArea.setText(Movie.generateReport());
	}
	
	public void backButtonPressed(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("AdminManage.fxml"));
		scene = new Scene(root);
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene.getStylesheets().add(getClass().getResource("adminInterface.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
