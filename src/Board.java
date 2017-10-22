public class Board {
	private char [][] board;
	private int row, col;
	private char player1, player2;
	public Board(){
		this.row = 6;
		this.col = 7;
		player1 = 'o';
		player2 = 't';
		board = new char[6][7];
		for (int i = 0; i<6; i++){
			for (int j = 0; j<7; j++){
				board[i][j] = ' ';
			}
		}
	}
	public Board(int row, int col){
		this.row = row;
		this.col = col;
		player1 = 'o';
		player2 = 't';
		if ((row < 0) || (col < 0)){
			row = 6;
			col = 7;
		}
		board = new char[row][col];
		for (int i = 0; i<row; i++){
			for (int j = 0; j<col; j++){
				board[i][j] = ' ';
			}
		}
	}
	public int getNumRows(){
		return this.row;
	}
	public int getNumCols(){
		return this.col;
	}
	public char getPlayerOne(){
		return player1;
	}
	public char getPlayerTwo(){
		return player2;
	}		
	public void setPlayerOne(char o){
		this.player1 = o;
	}
	public void setPlayerTwo(char t){
		this.player2 = t;
	}
	public char getToken(int row, int col){//returns the char representing token at location row,col, returns '\0' if indices are invalid 
		if (row >= 0 && row < board.length && col >= 0 && col < board[0].length)
			return board[row][col];
		else 
			return '\0';
		} 

	public boolean canPlay(){//places the appropriate token for player p in column c. returns true if successful, false otherwise.
		for (int x = 0; x<row; x++){
			for (int y = 0; y<col; y++){
				if (board[x][y] == ' '){
					return true;
				}
			}
		}
		return false;
	}
	public boolean play(int p, int c){//places the appropriate token for player p in column c. returns true if successful, false otherwise.
	if (c <col && c>= 0){
		for (int i = board.length -1; i > -1; i--){
			if (board[i][c] == ' ' && canPlay()== true){
				if (p == 1) {
					board[i][c]= player1;
					return true;
				}
				else if (p == 2){ 
					board[i][c]= player2;                             
					return true;
				}
			}
		}
	} 
	return false; 
}
	public int isFinished(){
		int result =-1;
		if(this.canPlay()==false){
			result = 0;
		}
		else{
			for(int i=0;i<row-3;i++){
				for(int j=0;j<col;j++){
					if(board[i][j] == board[i+1][j] && board[i][j] == board[i+2][j] && board[i][j]==board[i+3][j]){
						if(board[i][j] == player1){
							result = 1;
						}
						if(board[i][j] == player2){
							result = 2;
						}
					}
				}
			}
			for(int i=0;i<row-3;i++){
				for(int j=col-1;j>=col-1;j--){
					if(board[i][j] == board[i+1][j-1] && board[i][j] == board[i+2][j-2] && board[i][j]==board[i+3][j-3]){
						if(board[i][j] == player1){
							result = 1;
						}
						if(board[i][j] == player2){
							result = 2;
						}
					}
				}
			}
		}
		for(int i=0;i<row;i++){
			for(int j=0;j<col-3;j++){
				if(board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2] && board[i][j]==board[i][j+3]){
					if(board[i][j] == player1){
						result = 1;
					}
					if(board[i][j] == player2){
						result = 2;
					}
				}
			}
		}
		for(int i=0;i<row-3;i++){
			for(int j=0;j<col-3;j++){
				if(board[i][j] == board[i+1][j+1] && board[i][j] == board[i+2][j+2] && board[i][j]==board[i+3][j+3]){
					if(board[i][j] == player1){
						result = 1;
					}
					if(board[i][j] == player2){
						result = 2;
					}
				}
			}
		}
		return result;
	}
}