 import javafx.application.Application;
 import javafx.stage.Stage; 
 import javafx.scene.Scene;
 import javafx.scene.control.Label; 
 import javafx.scene.layout.BorderPane; 
 import javafx.scene.layout.GridPane; 
 import javafx.scene.layout.Pane; 
 import javafx.scene.paint.Color; 
 import javafx.scene.shape.Line; 
 import javafx.scene.shape.Ellipse; 

public class TicTacToe extends Application {

	private char whoseTurn = 'X';
			 // Create and initialize cell 
	private Cell[][] cell = new Cell[3][3];
			 // Create and initialize a status label
	private Label lblStatus = new Label("X's turn to play");
	@Override // Override the start method in the Application class 
	public void start(Stage primaryStage) { 
			 // Pane to hold cell hold nine cells 
	GridPane pane = new GridPane();
	 for (int i = 0; i < 3; i++) 
		for (int j = 0; j < 3; j++)
			pane.add(cell[i][j] = new Cell(),j,i);
	 
	BorderPane borderPane = new BorderPane();
	borderPane.setCenter(pane);
	borderPane.setBottom(lblStatus);
	
	Scene scene = new Scene(borderPane, 400, 400);
	primaryStage.setTitle("TicTacToe");
	primaryStage.setScene(scene);
	primaryStage.show();

	
	
}
public class Cell extends Pane {
	
	private char token = ' ';
	
	public Cell() {
		setStyle("-fx-border-color:black");
		this.setPrefSize(500, 800);
		//this.setOnMouseClicked(e->handleMouseClick());
	}
	public char getToken() {
		return token;
	}
}
public static void main(String[] args) {
	Application.launch(args);
}

}
