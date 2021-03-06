
import java.util.concurrent.TimeUnit;

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
  public static int winsNeeded = 0;
  public static int winsCount = 0;
  public static int XwinsCount = 0;
  public static int OwinsCount = 0;
  public static String oppchoice = "";
  public static String diffchoice = "";
  ToggleButton easybtn = new ToggleButton("Easy");
  ToggleButton mediumbtn = new ToggleButton("Medium");
  ToggleButton hardbtn = new ToggleButton("Hard");
  Scene menuscene;
  
  public int sqFilled;
  private char whoseTurn = 'X';
  int Xscore = 0;
  int Oscore = 0;
  Label lblX = new Label();
  Label lblO = new Label();
  GridPane pane = new GridPane();


  // Create and initialize cell 
  private Cell[][] cell = new Cell[3][3];
  // Create and initialize a status label
  private Label lblStatus = new Label("X's turn to play");

  @Override // Override the start method in the Application class 
  public void start(Stage primaryStage) {



   //creating menu 

   ToggleGroup tgroup = new ToggleGroup();

   Label title = new Label("Tic-Tac-Toe");
   title.setFont(Font.font("Cambria", 32));

   //vs computer or person
   Label opponentlbl = new Label("Select Opponent");

   ToggleButton computerbtn = new ToggleButton("Computer ");
   computerbtn.setUserData("Computer");
   computerbtn.setToggleGroup(tgroup);
   computerbtn.setSelected(true);
   computerbtn.setMinWidth(80);
   computerbtn.setOnAction(e->{
	   easybtn.setDisable(false);
	   mediumbtn.setDisable(false);
	   hardbtn.setDisable(false);
   });


   ToggleButton userbtn = new ToggleButton("User");
   userbtn.setUserData("User");
   userbtn.setToggleGroup(tgroup);
   userbtn.setMinWidth(80);
   userbtn.setOnAction(e->{
	   easybtn.setDisable(true);
	   mediumbtn.setDisable(true);
	   hardbtn.setDisable(true);
   });


   HBox opponent = new HBox(20);
   opponent.getChildren().add(computerbtn);
   opponent.getChildren().add(userbtn);
   opponent.setAlignment(Pos.TOP_CENTER);
   
   
   Label lblDiff = new Label("Select Difficulty");
   
   ToggleGroup tdifgroup = new ToggleGroup();

   easybtn.setUserData("Easy");
   easybtn.setToggleGroup(tdifgroup);
   easybtn.setMinWidth(80);
   easybtn.setSelected(true);
   
   mediumbtn.setUserData("Medium");
   mediumbtn.setToggleGroup(tdifgroup);
   mediumbtn.setMinWidth(80);
   
   
   hardbtn.setUserData("Hard");
   hardbtn.setToggleGroup(tdifgroup);
   hardbtn.setMinWidth(80);
   
   
   
   HBox diffbuttons = new HBox(20);
   diffbuttons.getChildren().addAll(easybtn,mediumbtn,hardbtn);
   diffbuttons.setAlignment(Pos.TOP_CENTER);
   
   VBox difficulty_vbox = new VBox(20);
   difficulty_vbox.getChildren().addAll(lblDiff,diffbuttons);
   difficulty_vbox.setAlignment(Pos.CENTER);
   

   VBox opp_vbox = new VBox(15);
   opp_vbox.getChildren().addAll(opponentlbl, opponent);
   opp_vbox.setAlignment(Pos.CENTER);


   //wins required
   Label wins_lbl = new Label("Amount of Wins Required: ");
   TextField wins_tf = new TextField("1");
   wins_tf.setPrefWidth(60);

   HBox wins_hbox = new HBox(10);
   wins_hbox.getChildren().addAll(wins_lbl, wins_tf);
   wins_hbox.setAlignment(Pos.CENTER);


   Alert a = new Alert(AlertType.WARNING);
   a.setContentText("Must Specify Amount of Wins Required");
   
  
   
   //start button
   Button startbtn = new Button("Start");
   startbtn.setMaxHeight(200);
   startbtn.setMaxWidth(300);

   startbtn.setOnAction(new EventHandler < ActionEvent > () {

    @Override
    public void handle(ActionEvent event) {
     if (wins_tf.getText().isEmpty()) {
      a.show();
     } else {

      winsNeeded = Integer.parseInt(wins_tf.getText());
      grid(winsNeeded, primaryStage);
      oppchoice = tgroup.getSelectedToggle().getUserData().toString();
      diffchoice = tdifgroup.getSelectedToggle().getUserData().toString();

     }
    }

   });

   VBox vbox = new VBox(40);
   vbox.getChildren().addAll(title, opp_vbox, difficulty_vbox,wins_hbox, startbtn);
   vbox.setAlignment(Pos.CENTER);


    menuscene = new Scene(vbox, 500, 500);

   primaryStage.setTitle("TicTacToe");
   primaryStage.setScene(menuscene);
   primaryStage.show();

  }

  //function for printing out grid 
  public void grid(int wins, Stage primaryStage) {

	  BorderPane borderPane = new BorderPane();

   Label lblScore = new Label("First to " + wins);
   
   
   Button backbtn = new Button("Back");
   backbtn.setMaxHeight(50);
   backbtn.setMaxWidth(100);
   
   backbtn.setOnAction(e->{
	   primaryStage.setScene(menuscene);
	   BorderPane borderpane1 = new BorderPane();
	   gridscene = new Scene(borderpane1);
	   resetBoard();
	   Xscore = 0;
	   Oscore = 0;
	   whoseTurn = 'X';
	   XwinsCount = OwinsCount = 0;
       lblStatus.setText(whoseTurn + "'s turn");

   });
   
   HBox top_hbox = new HBox(20);
   top_hbox.getChildren().addAll(lblScore,backbtn);
   top_hbox.setAlignment(Pos.TOP_RIGHT);
   top_hbox.setMargin(backbtn, new Insets(5,60,0,30));
   
   Label lblXvO = new Label("X vs O");

   lblX.setText("" + Xscore);
   lblO.setText("" + Oscore);

   lblScore.setFont(Font.font("Cambria", 20));
   lblXvO.setFont(Font.font("Cambria", 20));
   lblX.setFont(Font.font("Cambria", 20));
   lblO.setFont(Font.font("Cambria", 20));


   HBox hboxXO = new HBox(25);
   hboxXO.getChildren().addAll(lblX, lblO);
   hboxXO.setAlignment(Pos.CENTER);

   VBox vboxScore = new VBox();
   vboxScore.getChildren().addAll(top_hbox, lblXvO, hboxXO);
   vboxScore.setAlignment(Pos.CENTER);

   // Pane to hold cell hold nine cells 
   pane.setStyle("-fx-background-color: white;");

   for (int i = 0; i < 3; i++)
    for (int j = 0; j < 3; j++)
     pane.add(cell[i][j] = new Cell(), j, i);

   borderPane.setCenter(pane);
   borderPane.setBottom(lblStatus);
   borderPane.setAlignment(lblStatus, Pos.CENTER);

   borderPane.setTop(vboxScore);
   borderPane.setStyle("-fx-background-color: white;");


   gridscene = new Scene(borderPane, 400, 450);

   lblStatus.setFont(Font.font("Cambria", 20));

   primaryStage.setScene(gridscene);



  }

  private boolean checkWinner() {
   char symbol;
   if (XwinsCount == winsNeeded || OwinsCount == winsNeeded) {
    return true;
   }

   if (whoseTurn == 'X') {
    symbol = 'X';
   } else {
    symbol = 'O';
   }
   if (
    (cell[0][0].getToken() == symbol && cell[0][1].getToken() == symbol && cell[0][2].getToken() == symbol) ||
    (cell[1][0].getToken() == symbol && cell[1][1].getToken() == symbol && cell[1][2].getToken() == symbol) ||
    (cell[2][0].getToken() == symbol && cell[2][1].getToken() == symbol && cell[2][2].getToken() == symbol) ||
    (cell[0][0].getToken() == symbol && cell[1][0].getToken() == symbol && cell[2][0].getToken() == symbol) ||
    (cell[0][1].getToken() == symbol && cell[1][1].getToken() == symbol && cell[2][1].getToken() == symbol) ||
    (cell[0][2].getToken() == symbol && cell[1][2].getToken() == symbol && cell[2][2].getToken() == symbol) ||
    (cell[0][0].getToken() == symbol && cell[1][1].getToken() == symbol && cell[2][2].getToken() == symbol) ||
    (cell[0][2].getToken() == symbol && cell[1][1].getToken() == symbol && cell[2][0].getToken() == symbol)
   ) {
    setScore();
    if (symbol == 'X')
     XwinsCount++;
    else if (symbol == 'O')
     OwinsCount++;
    return true;
   } else {
    return false;
   }
  }

  public void setScore() {
   if (whoseTurn == 'X') {
    Xscore++;
    lblX.setText("" + Xscore);
   } else if (whoseTurn == 'O') {
    Oscore++;
    lblO.setText("" + Oscore);
   }
  }

  public boolean isFull() {
   for (int i = 0; i < 3; i++)
    for (int j = 0; j < 3; j++)
     if (cell[i][j].getToken() == ' ')
      return false;
   return true;
  }

  public void resetBoard() {
   for (int i = 0; i < 3; i++)
    for (int j = 0; j < 3; j++) {
     if (cell[i][j].getToken() == 'X') {
      cell[i][j].getChildren().remove(0);
      cell[i][j].getChildren().remove(0);
      cell[i][j].setToken('c'); //clear token 
     } else if (cell[i][j].getToken() == 'O')
      cell[i][j].getChildren().remove(0);
     cell[i][j].setToken('c'); //clear token 

    }
   sqFilled = 0;
  }



  public class Cell extends Pane {
   private TicTacToeAI ai = new TicTacToeAI();
   private char token = ' ';


   public Cell() {
    setStyle("-fx-border-color:black");
    this.setPrefSize(500, 800);
    this.setOnMouseClicked(e -> handleMouseClick());
   }
   
   public void alert() {
	   Alert game_alert = new Alert(AlertType.INFORMATION);
	   game_alert.setContentText("Round Over! Please Select 'OK' to Continue");
 	   game_alert.show();
 
   }

   private void handleMouseClick() {
	  
    
     if (token == ' ' && whoseTurn != ' ') {

      setToken(whoseTurn); // Set token in the cell
      // Check game status


      if (checkWinner()) {
       if (XwinsCount == winsNeeded || OwinsCount == winsNeeded) {
        lblStatus.setText(whoseTurn + " won! The game is over");
        whoseTurn = ' '; // Game is over
        return;
       } else if (XwinsCount < winsNeeded && OwinsCount < winsNeeded) {
    	   alert();   	     
    	   resetBoard();
        lblStatus.setText(whoseTurn + " won the round. Play again");
        
        whoseTurn = 'X';
		return;
       }
      } else if (isFull()) {
       lblStatus.setText("Draw! Redo");
       resetBoard();
	   whoseTurn = 'X';
	   return;
      } else {
       // Change the turn
       whoseTurn = (whoseTurn == 'X') ? 'O' : 'X'; // Display whose turn 
       lblStatus.setText(whoseTurn + "'s turn");
      }
     
	 if(oppchoice.equals("Computer") && whoseTurn != 'X'){
		 //AI Stuff
		 
		 //Make board into char array
		 char[][] n_board = new char[3][3];
		 for(int i = 0; i < 3; i++){
			 for(int j = 0; j < 3; j++){
				 n_board[i][j] = cell[i][j].getToken();
			 }
		 }
		 
		 //Call selectSpace
		 int r;
		 if (diffchoice.equals("Hard")) {
			 r = ai.selectSpaceHard(n_board);
		 }
		 else if (diffchoice.equals("Medium")) {
			 r= ai.selectSpaceMedium(n_board);
		 }
		 else {
			 r = ai.selectSpaceEasy(n_board);
		 }
		 
		 //turn retun into useable val
		 int x = r % 3;
		 int y = r / 3;
		 
		 //place piece
		 cell[y][x].setToken(whoseTurn);
		 
		 //check conditions
		 if (checkWinner()) {
       if (XwinsCount == winsNeeded || OwinsCount == winsNeeded) {
        lblStatus.setText(whoseTurn + " won! The game is over");
        whoseTurn = ' '; // Game is over
        return;
       } 
       else if (XwinsCount < winsNeeded && OwinsCount < winsNeeded) {
    	   
    	   	//game_alert.show();
        resetBoard();
      

        lblStatus.setText(whoseTurn + " won the round. Play again...");
        whoseTurn = 'X';
		return;
       }
      } else if (isFull()) {
  	   //	game_alert.show();
       lblStatus.setText("Draw! Redo");
       resetBoard();
	   whoseTurn = 'X';
	   return;
      } else {
       // Change the turn
       whoseTurn = (whoseTurn == 'X') ? 'O' : 'X'; // Display whose turn 
       lblStatus.setText(whoseTurn + "'s turn");
      }
		 
	 }
    }
   }

   public char getToken() {
    return token;
   }


   public void setToken(char c) {
 
    token = c;


    if (token == 'X') {
     Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
     line1.setStrokeWidth(5);
     line1.endXProperty().bind(this.widthProperty().subtract(10));
     line1.endYProperty().bind(this.heightProperty().subtract(10));
     Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
     line2.setStrokeWidth(5);
     line2.startYProperty().bind(this.heightProperty().subtract(10));
     line2.endXProperty().bind(this.widthProperty().subtract(10));
     // Add the lines to the pane
     this.getChildren().addAll(line1, line2);
    } else if (token == 'O') {
     Ellipse ellipse = new Ellipse(this.getWidth() / 2,
      this.getHeight() / 2, this.getWidth() / 2 - 10,
      this.getHeight() / 2 - 10);
     ellipse.centerXProperty().bind(
      this.widthProperty().divide(2));
     ellipse.centerYProperty().bind(
      this.heightProperty().divide(2));
     ellipse.radiusXProperty().bind(
      this.widthProperty().divide(2).subtract(10));
     ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
     ellipse.setStroke(Color.BLACK);
     ellipse.setFill(Color.RED);
     getChildren().add(ellipse); // Add the ellipse to the pane
    } else {
     token = ' ';
    }
    sqFilled++;

   }
  }
  public static void main(String[] args) {
   Application.launch(args);
  }

 }
