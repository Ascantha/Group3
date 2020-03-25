public class TicTacToeAI
 final int X_WIN = 10;
 final int O_WIN = -10;
 final int TIE = 0;
 //Given a board state and player to select for, choose a space.
  public static int selectSpace(char[][] inBoard, char player){
    //Construct a nice board for minimax stuffs
    char[] board = ['0', '1', '2', '3', '4', '5', '6', '7', '8'];
    //Fill it with the right values
    for(int i = 0; i < 9; i++){if(inBoard[i%3][i/3] != ' '){board[i] = inBoard[i%3][i/3];}}
    //Run the minimax algorithm
    return minimax(board, player);
  }
  
  private int minimax(char[] board, char player){
    //Find available places
    int[] avail = findSpots(board);
    //Check to see if we've won
    if(winning(board, 'X')){
      return 10;
    }
    if(winning(board, 'O')){
      return -10;
    }
    if(avail.length == 0){
      return 0;
    }
    
    int[] moveScores = new int[avail.length];
    
    for(int i = 0; i < avail.length; i++){
      
    }
    
    
  }
  
  //return free spots on the board
  private int[] findSpots(char[] board){
    int c = 0;
    for(int i = 0; i < 9; i++){if((board[i] == 'X')||(board[i] == 'O'))c++;}
    int[] spots = new int[c];
    int j = 0;
    for(int i = 0; i < 9; i++){if((board[i] == 'X')||(board[i] == 'O')){spots[j] = i; j++;}}
    return spots;
  }
  
  private boolean winning(char[] board, char player){
    if (
        (board[0] == player && board[1] == player && board[2] == player) ||
        (board[3] == player && board[4] == player && board[5] == player) ||
        (board[6] == player && board[7] == player && board[8] == player) ||
        (board[0] == player && board[3] == player && board[6] == player) ||
        (board[1] == player && board[4] == player && board[7] == player) ||
        (board[2] == player && board[5] == player && board[8] == player) ||
        (board[0] == player && board[4] == player && board[8] == player) ||
        (board[2] == player && board[4] == player && board[6] == player)
        ) {
        return true;
    } else {
        return false;
    }
  }
}


