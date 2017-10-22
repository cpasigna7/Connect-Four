
public class AIPlayer implements Player{
	public int row, col;
	public int player;
	public Board b;
	public AIPlayer(int playerID, int row, int col){
		this.b = new Board();
		this.row = row;
		this.col = col;
		this.player = playerID;
	}
	public void lastMove(int c) {
		if (this.player==1){
			this.b.play(2,c);
		}if (this.player==2){
			this.b.play(1,c);
		}
	}
	public int playToken(){
		int slot = 0;
		for (int i = 0; i<col; i++){
			if (b.play(this.player, i)){
				slot = i;
				break;
			}
		}
		return slot;
	}
	public int getPlayerID(){
		return this.player;
	}
	public void reset(){
		this.b = new Board(this.row, this.col);
	}
}