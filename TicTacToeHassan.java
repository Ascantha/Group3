package ticTacToe;

public class TicTacToe {

	private char[][] board;
    private char currentPlayerMark;	
    
public TicTacToe() {//the constructor. It will be responsible for ensuring the board gets initialized properly, and for setting who the first player will be.

    board = new char[3][3];
    currentPlayerMark = 'x';
    initializeBoard();
}

//Gives us access to currentPlayerMark
public char getCurrentPlayerMark()
{
    return currentPlayerMark;
}

public void initializeBoard() {//This method will initialize the board variable such that all slots are empty.
	
	//loop through rows
for (int i=0; i<3; i++) {
	
	//loop through columns
for (int j=0; j<3; j++) {
	board[i][j]= '-';
}
}
}

public void printBoard() {//This method will print the Tic-Tac-Toe board to standard output.
	System.out.println("-------------");
	
	for (int i=0; i<3; i++) {
		System.out.println("| ");
		for (int j=0; j<3; j++) {
			System.out.print(board[i][j] + " |  ");
		}
		System.out.println();
		System.out.println("-------------");
	}
}

public boolean isBoardFull() {//this method will check whether or not the board is full. It will return true if the board is full and a false otherwise.
	boolean isFull = true;

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (board[i][j] == '-') {
                isFull = false;
            }
        }
    }

    return isFull;
}

public boolean checkForWin() {//This method will check to see if a player has won, and if so, it will return true.

	
return checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin();

}
private boolean checkRowsForWin() {//This method will specifically check the rows for a win.
    
	for (int i=0; i<3; i++) {
		if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
			return true;
	}
	}
	return false;

}
private boolean checkColumnsForWin() { //This method will specifically check the columns for a win.
	for (int i=0; i<3; i++) {
		if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
			return true;
	}
	}
	return false;

}

private boolean checkDiagonalsForWin() { //This method will specifically check the diagonals for a win.

	 return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
}
	

private boolean checkRowCol(char ch1, char ch2, char ch3) { //This method will check the characters taken in to see if all three are the same ‘x’ or ‘o’ letter. If so, return true.
	 return ((ch1 != '-') && (ch1 == ch2) && (ch2 == ch3));
}


public void changePlayer() { // Change player marks back and forth.
    if (currentPlayerMark == 'x') {
        currentPlayerMark = 'o';
    }
    else {
        currentPlayerMark = 'x';
    }
}


public boolean placeMark(int row, int col) { // Places a mark at the cell specified by row and col with the mark of the current player.

    
    if ((row >= 0) && (row < 3)) { // Make sure that row and column are in bounds of the board.
        if ((col >= 0) && (col < 3)) {
            if (board[row][col] == '-') {
                board[row][col] = currentPlayerMark;
                return true;
            }
        }
    }

    return false;
}
}



