public class GameState {
	
	static char[][] gameBoard = new char[3][3];
	int turn = 0;
	int sqFilled = 0;
	int winner = -1;

	public int placePiece(int row, int col){
		if (gameBoard[row][col] != '-'){
			return -1;
		}
		if (turn == 0){
			gameBoard[row][col] = 'X';
			turn = 1;
		} else {
			gameBoard[row][col] = 'O';
			turn = 0;
		}
		sqFilled++;
		winner = checkWinner();
		if (winner != -1){
			return -1;
		} else {
			return winner;
		}
	}
	
	public char[][] getBoard() {
		return gameBoard;
	}

	public void resetBoard(){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				gameBoard[i][j] = '-';
			}
		}
		sqFilled = 0;
		winner = -1;
	}
	
	private int checkWinner(){
		char symbol;
		if (turn == 0){
			symbol = 'X';
		} else {
			symbol = 'O';
		}
		if (
			(gameBoard[0][0] == symbol && gameBoard[0][1] == symbol && gameBoard[0][2] == symbol) ||
			(gameBoard[1][0] == symbol && gameBoard[1][1] == symbol && gameBoard[1][2] == symbol) ||
			(gameBoard[2][0] == symbol && gameBoard[2][1] == symbol && gameBoard[2][2] == symbol) ||
			(gameBoard[0][0] == symbol && gameBoard[1][0] == symbol && gameBoard[2][0] == symbol) ||
			(gameBoard[0][1] == symbol && gameBoard[1][1] == symbol && gameBoard[2][1] == symbol) ||
			(gameBoard[0][2] == symbol && gameBoard[1][2] == symbol && gameBoard[2][2] == symbol) ||
			(gameBoard[0][0] == symbol && gameBoard[1][1] == symbol && gameBoard[2][2] == symbol) ||
			(gameBoard[0][2] == symbol && gameBoard[1][1] == symbol && gameBoard[2][0] == symbol)
        ) {
			return turn;
		} else if (sqFilled > 8) {
			return 2;
		} else {
			return -1;
		}
	}
}