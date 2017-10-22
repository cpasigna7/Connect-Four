public class HumanPlayer implements Player{
	public int row, col;
	public int player, opp;
	public Board b;
	public HumanPlayer(int playerID, int row, int col){
		this.b = new Board();
		this.row = row;
		this.col = col;
		this.player = playerID;
	}
	public void lastMove(int c){
		if (this.player==1){
			this.b.play(2,c);
		}if (this.player==2){
			this.b.play(1,c);
		}
	}
	public int playToken(){
		int input = IO.readInt();
		while (this.b.play(this.player,input)==false){
			input = IO.readInt();
		}
		return input;
	}
	public int getPlayerID(){
		return this.player;
	}
	public void reset(){
		this.b = new Board(this.row,this.col);
	}
}