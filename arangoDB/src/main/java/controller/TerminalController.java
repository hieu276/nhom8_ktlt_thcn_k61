package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.RandomDataGenerator;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TerminalController implements Initializable {
	private final Connection connection = new Neo4jConnection().getConnection("jdbc:neo4j:bolt://localhost:11004",
			"neo4j", "123456");
	private RandomDataGenerator generator = new RandomDataGenerator();
	private double x, y;

	@FXML
	private TextField textField;

	@FXML
	private TableView<?> tableView;

	@FXML
	void dragged(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setX(event.getScreenX() - x);
		stage.setY(event.getScreenY() - y);
	}

	@FXML
	void pressed(MouseEvent event) {
		x = event.getSceneX();
		y = event.getSceneY();
	}

	@FXML
	private void min(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	private void close(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void onKeyPressed(KeyEvent event) {
		try {
			if (event.getCode().equals(KeyCode.ENTER)) {
				String query = textField.getText();
				tableView = new TableView<Object>();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialize(URL location, ResourceBundle resources) {

	}
}