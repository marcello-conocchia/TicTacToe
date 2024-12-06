package application;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Controller {

	@FXML
	private Button btn1;

	@FXML
	private Button btn2;

	@FXML
	private Button btn3;

	@FXML
	private Button btn4;

	@FXML
	private Button btn5;

	@FXML
	private Button btn6;

	@FXML
	private Button btn7;

	@FXML
	private Button btn8;

	@FXML
	private Button btn9;

	@FXML
	private GridPane gameGrid;

	@FXML
	private Button startButton;

	@FXML
	private Label playerLabel;
	
	@FXML
	private Label red;
	@FXML
	private Label blue;
	

	private List<Button> buttons;

	Player player1 = new Player("Player1", "O");
	Player player2 = new Player("Player2", "X");
	Player currentPlayer = player1;
	Player winner = new Player();
	int[][] winningComb = {
			{0, 1, 2},
			{3, 4, 5},
			{6, 7, 8},
			{0, 3, 6},
			{1, 4, 7},
			{2, 5, 8},
			{0, 4, 8},
			{2, 4, 6}
			
	};
	
	
	
	public void initialize() {
		buttons = Arrays.asList(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);
	    buttons.forEach(button -> button.setOnAction(event -> handleButtonClick(event)));
	    
	}

	private void handleButtonClick(ActionEvent event) {
		Button clickedButton = (Button) event.getSource();
		if (clickedButton.getText().isEmpty()) {
			clickedButton.setText(currentPlayer.getSymbol());
			switchPlayer();
			checkWinner(winner);
			
			
		} else if(clickedButton.getText().isEmpty()){
			System.out.println("Das Feld ist schon belegt! Wähle ein anderes");
		}
	}

	public void switchPlayer() {
		if(currentPlayer == player1) {
			currentPlayer = player2;
			
		
		}else if(currentPlayer == player2) {
			currentPlayer = player1;
		}
	}
	
	public Player checkWinner(Player winner) {
		
		for (int[] combo : winningComb) {
			int a = combo[0];
			int b = combo[1];
			int c = combo[2];
			
			if(!buttons.get(a).getText().isEmpty() &&
				buttons.get(a).getText().equals(buttons.get(b).getText()) &&
				buttons.get(a).getText().equals(buttons.get(c).getText()) &&
				buttons.get(b).getText().equals(buttons.get(c).getText())) {
				if(buttons.get(a).getText().equals(player1.getSymbol())) {
					
					 winner.setName(player1.name); 
					 System.out.println();
					 gewinner();
					 resetGame();
					 break;
				
		}else if(buttons.get(a).getText().equals(player2.getSymbol())) {
			winner.setName(player2.name);
			 gewinner();
			 resetGame();
			 break;
			
		}	
				
			}
			
			
		}return winner;
		
		
	}
	
	public void resetGame() {
		for(int i = 0; i < buttons.size();i++) {
			if(!buttons.get(i).getText().isEmpty()) {
				buttons.clear();
			}
		}
		
	}
	
	
	public void gewinner() {


	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("TicTacToe");
	alert.setHeaderText("Spiel beendet"); // oder null
	alert.setContentText("Der Gewinner ist: " + winner.name);
	alert.showAndWait();


	}

}
