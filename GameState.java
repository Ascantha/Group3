public class GameState {
	
	static char[][] gameBoard = new char[3][3];
	int turn = 0;
	
	public int placePiece(int row, int col){
		if (gameBoard[row][col] != '-'){
			return -1;
		}
		if (turn == 0){
			gameBoard[row][col] = 'X';
		} else {
			gameBoard[row][col] = 'O';
		}
		checkWinner();
	}
	
	public int resetBoard(){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				gameBoard[i][j] = '-';
			}
		}
	}
	
	private int checkWinner(){
	}
}