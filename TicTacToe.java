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

  public int sqFilled;
  private char whoseTurn = 'X';
  int Xscore = 0;
  int Oscore = 0;
  Label lblX = new Label();
  Label lblO = new Label();
  GridPane pane = new GridPane();
  BorderPane borderPane = new BorderPane();


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
   Label opponentlbl = new Label("Select Your Opponent");

   ToggleButton computerbtn = new ToggleButton("Computer ");
   computerbtn.setUserData("Computer");
   computerbtn.setToggleGroup(tgroup);
   computerbtn.setMinWidth(80);

   ToggleButton userbtn = new ToggleButton("User");
   userbtn.setUserData("User");
   userbtn.setToggleGroup(tgroup);
   userbtn.setMinWidth(80);
   userbtn.setSelected(true);


   HBox opponent = new HBox(20);
   opponent.getChildren().add(computerbtn);
   opponent.getChildren().add(userbtn);
   opponent.setAlignment(Pos.TOP_CENTER);


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
      System.out.print(oppchoice);

     }
    }

   });

   VBox vbox = new VBox(40);
   vbox.getChildren().addAll(title, opp_vbox, wins_hbox, startbtn);
   vbox.setAlignment(Pos.CENTER);


   Scene menuscene = new Scene(vbox, 500, 500);

   primaryStage.setTitle("TicTacToe");
   primaryStage.setScene(menuscene);
   primaryStage.show();

  }

  //function for printing out grid 
  public void grid(int wins, Stage primaryStage) {


   Label lblScore = new Label("First to " + wins);
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
   vboxScore.getChildren().addAll(lblScore, lblXvO, hboxXO);
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

   private char token = ' ';

   public Cell() {
    setStyle("-fx-border-color:black");
    this.setPrefSize(500, 800);
    this.setOnMouseClicked(e -> handleMouseClick());
   }

   private void handleMouseClick() {
    if (oppchoice == "Computer") {
     //Nathan's AI 
     //
     //
    }


    if (oppchoice == "User") {
     if (token == ' ' && whoseTurn != ' ') {

      setToken(whoseTurn); // Set token in the cell
      // Check game status


      if (checkWinner()) {
       if (XwinsCount == winsNeeded || OwinsCount == winsNeeded) {
        lblStatus.setText(whoseTurn + " won! The game is over");
        whoseTurn = ' '; // Game is over
        return;
       } else if (XwinsCount < winsNeeded && OwinsCount < winsNeeded) {
        lblStatus.setText(whoseTurn + " won the round. Play again");
        resetBoard();
        whoseTurn = 'X';
       }
      } else if (isFull()) {
       lblStatus.setText("Draw! Redo");
       resetBoard();
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
    //		if (this.getToken() != ' ') {
    //			return;
    //		}
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