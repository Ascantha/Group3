import java.util.ArrayList;

public class TicTacToeAI{
 char opPlayer;
 char enPlayer;
 final Move nullWin = new Move(-1,10);
 final Move nullLose = new Move(-1,-10);
 final Move nullTie = new Move(-1,0);

 public TicTacToeAI(){
	opPlayer = 'O';
	enPlayer = 'X';
 }
   
  public int selectSpace(char[][] inBoard){
    char[] board = new char[9];
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        board[i*3+j] = inBoard[i][j];
      }
    }
    Move bestMove = minimax(board, opPlayer);
    return bestMove.space();
  }
  
  private Move minimax(char[] board, char player){
    if(winning(board, opPlayer)){return nullWin;}
    else if(winning(board, enPlayer)){return nullLose;}
    ArrayList<Integer> avail = findSpots(board);
    if(avail.size() == 0){return nullTie;}
    
    char oplayer = 'O';
    if(player == oplayer){oplayer = 'X';}
    
    ArrayList<Move> moves = new ArrayList<Move>();
    
    for(int i = 0; i < avail.size(); i++){
      board[avail.get(i)] = player;
      Move newMove = minimax(board, player);
      newMove.space = avail.get(i);
      moves.add(newMove);
      board[avail.get(i)] = '-';
    }
    
    Move bestMove = new Move(-1,0);
    if(player == opPlayer){//max
      for(int i = 0; i < moves.size(); i++){
        if(moves.get(i).score >= bestMove.score){bestMove = moves.get(i);}
      }
    }
    else{//min
      for(int i = 0; i < moves.size(); i++){
        if(moves.get(i).score <= bestMove.score){bestMove = moves.get(i);}
      }
    }
    return bestMove;
  }
  
  private ArrayList<Integer> findSpots(char[] board){
    ArrayList<Integer> spaces = new ArrayList<Integer>();
    for(int i = 0; i < board.length; i++){if(!(board[i] == 'X' || board[i] == 'O'))spaces.add(i);}
    return spaces;
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
  
  private class Move{
    public int space;
    public int score;
    public Move(int sp, int sc){score = sc; space = sp;}
  }
  
}
// :)

