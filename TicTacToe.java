 import javafx.application.Application;
 import javafx.stage.Stage; 
 import javafx.event.ActionEvent;
 import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color; 
 import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.shape.Ellipse; 

public class TicTacToe extends Application {

	Scene gridscene;
	public static int wins = 0;
	private char whoseTurn = 'X';
			 // Create and initialize cell 
	private Cell[][] cell = new Cell[3][3];
			 // Create and initialize a status label
	private Label lblStatus = new Label("X's turn to play");	

	@Override // Override the start method in the Application class 
	public void start(Stage primaryStage) { 

	
	
//creating menu 
	
	ToggleGroup tgroup = new ToggleGroup();
	 
	Label title = new Label("Tic-Tac-Toe");
	title.setFont(Font.font("Cambria",32));
	
	//vs computer or person
	Label opponentlbl = new Label("Select Your Opponent");
	
	ToggleButton computerbtn = new ToggleButton("Computer ");
	computerbtn.setToggleGroup(tgroup);
	computerbtn.setSelected(true);
	computerbtn.setMinWidth(80);
	
	ToggleButton userbtn = new ToggleButton("User");
	userbtn.setToggleGroup(tgroup);
	userbtn.setMinWidth(80);
	
	HBox opponent = new HBox(20);
	opponent.getChildren().add(computerbtn);
	opponent.getChildren().add(userbtn);
	opponent.setAlignment(Pos.TOP_CENTER);

	
	VBox opp_vbox = new VBox(15);
	opp_vbox.getChildren().addAll(opponentlbl, opponent);
	opp_vbox.setAlignment(Pos.CENTER);
	

	//wins required
	Label wins_lbl = new Label("Amount of Wins Required: ");
	TextField wins_tf = new TextField();
	wins_tf.setPrefWidth(60);
	
	HBox wins_hbox = new HBox(10);
	wins_hbox.getChildren().addAll(wins_lbl,wins_tf);
	wins_hbox.setAlignment(Pos.CENTER);
	

    Alert a = new Alert(AlertType.WARNING); 
    a.setContentText("Must Specify Amount of Wins Required");


	//start button
	   Button startbtn = new Button("Start");
	   startbtn.setMaxHeight(200);
	   startbtn.setMaxWidth(300);
	   	
	   startbtn.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		            if (wins_tf.getText().isEmpty()) {
		        			a.show();
		        }
		        else {
		        		wins = Integer.parseInt(wins_tf.getText());
		        		//grid = true;
		        		//System.out.println(wins);
		        		grid(wins, primaryStage);
		        }
		        }
	        
	    });
	    
	 VBox vbox = new VBox(40);
	 vbox.getChildren().addAll(title,opp_vbox,wins_hbox,startbtn);
	 vbox.setAlignment(Pos.CENTER);
	 
	    
	Scene menuscene = new Scene(vbox, 500, 500);

	primaryStage.setTitle("TicTacToe");
	primaryStage.setScene(menuscene);
	primaryStage.show();

}
	
	//function for printing out grid 
	public void grid(int wins, Stage primaryStage) {
		
	    int Xscore = 1;
		int Oscore = 0;
		
		Label lblScore = new Label("Best of "+wins);
		Label lblXvO = new Label("X vs O");
		
		Label lblX = new Label(""+Xscore);
		Label lblO = new Label(""+Oscore);
		
		lblScore.setFont(Font.font("Cambria",20));
		lblXvO.setFont(Font.font("Cambria",20));
		lblX.setFont(Font.font("Cambria",20));
		lblO.setFont(Font.font("Cambria",20));

		
		HBox hboxXO = new HBox(25);
		hboxXO.getChildren().addAll(lblX,lblO);
		hboxXO.setAlignment(Pos.CENTER);
		
		VBox vboxScore = new VBox();
		vboxScore.getChildren().addAll(lblScore,lblXvO,hboxXO);
		vboxScore.setAlignment(Pos.CENTER);
		
		 // Pane to hold cell hold nine cells 
		GridPane pane = new GridPane();
		 for (int i = 0; i < 3; i++) 
			for (int j = 0; j < 3; j++)
				pane.add(cell[i][j] = new Cell(),j,i);

	  
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(lblStatus);
		borderPane.setTop(vboxScore);

		 gridscene = new Scene(borderPane, 400, 400);
		primaryStage.setScene(gridscene);

	    
	    
		
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
