import java.util.ArrayList;

//Ver fin

public class TicTacToeAI{
 char opPlayer;
 char enPlayer;
 final Move nullWin = new Move(-1,-10);
 final Move nullLose = new Move(-1,10);
 final Move nullTie = new Move(-1,0);

 public TicTacToeAI(){
	opPlayer = 'O';
	enPlayer = 'X';
 }
   
  public int selectSpaceHard(char[][] inBoard){
    char[] board = new char[9];
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        board[i*3+j] = inBoard[i][j];
      }
    }
		
    Move bestMove = minimax(board, opPlayer);
    return bestMove.space;
  }
  
  private Move minimax(char[] board, char player){
    if(winning(board, opPlayer)){return new Move(nullWin.space, nullWin.score);}
    if(winning(board, enPlayer)){return new Move(nullLose.space, nullLose.score);}
    ArrayList<Integer> avail = findSpots(board);
    if(avail.size() == 0){return new Move(nullTie.space, nullTie.score);}
    char oplayer = 'O';
    if(player == oplayer){oplayer = 'X';}
    
    ArrayList<Move> moves = new ArrayList<Move>();
    
    for(int i = 0; i < avail.size(); i++){
      board[avail.get(i)] = player;
      Move newMove = minimax(board, oplayer);
      newMove.space = avail.get(i);
	  if(newMove.score > 0){newMove.score -= 1;}
	  if(newMove.score < 0){newMove.score += 1;}
      moves.add(newMove);
      board[avail.get(i)] = ' ';
    }
    
	
    Move bestMove = moves.get(0);
    if(player == opPlayer){//max
      for(int i = 0; i < moves.size(); i++){
        if(moves.get(i).score <= bestMove.score){bestMove = moves.get(i);}
      }
    }
    else{//min
      for(int i = 0; i < moves.size(); i++){
        if(moves.get(i).score >= bestMove.score){bestMove = moves.get(i);}
      }
    }
    return bestMove;
  }
  
  private ArrayList<Integer> findSpots(char[] board){
    ArrayList<Integer> spaces = new ArrayList<Integer>();
	for(int i = 0; i < board.length; i++){if(board[i] == ' ')spaces.add(i);}
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
  
  public int selectSpaceEasy(char[][] inBoard){
	char[] board = new char[9];
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        board[i*3+j] = inBoard[i][j];
      }
    }
	ArrayList<Integer> avail = findSpots(board);
	int sel = (int)(Math.random() * avail.size());
	return avail.get(sel);
  }
  
  public int selectSpaceMedium(char[][] inBoard){
	char[] board = new char[9];
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        board[i*3+j] = inBoard[i][j];
      }
    }
		
    Move bestMove = maximax(board, opPlayer);
    return bestMove.space;
  }
  
  private Move maximax(char[] board, char player){
    if(winning(board, opPlayer)){return new Move(nullWin.space, nullWin.score);}
    if(winning(board, enPlayer)){return new Move(nullLose.space, nullLose.score);}
    ArrayList<Integer> avail = findSpots(board);
    if(avail.size() == 0){return new Move(nullTie.space, nullTie.score);}
    char oplayer = 'O';
    if(player == oplayer){oplayer = 'X';}
    
    ArrayList<Move> moves = new ArrayList<Move>();
    
    for(int i = 0; i < avail.size(); i++){
      board[avail.get(i)] = player;
      Move newMove = minimax(board, opPlayer);
      newMove.space = avail.get(i);
	  if(newMove.score > 0){newMove.score -= 1;}
	  if(newMove.score < 0){newMove.score += 1;}
      moves.add(newMove);
      board[avail.get(i)] = ' ';
    }
    
	
    Move bestMove = moves.get(0);
    if(player == opPlayer){//max
      for(int i = 0; i < moves.size(); i++){
        if(moves.get(i).score <= bestMove.score){bestMove = moves.get(i);}
      }
    }
    else{//min
      for(int i = 0; i < moves.size(); i++){
        if(moves.get(i).score >= bestMove.score){bestMove = moves.get(i);}
      }
    }
    return bestMove;
  }
  
  
  
  
  private class Move{
    public int space;
    public int score;
    public Move(int sp, int sc){score = sc; space = sp;}
  }
  
}
// :)

