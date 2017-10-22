public class ConnectFour{
	
	public static void main(String[] args){
		Board board = new Board (6,7);
		CFGUI gui = new CFGUI(board, ChipColor.BLACK, ChipColor.BLUE);
		Player player1 = new HumanPlayer(1,6,7);
		Player player2 = new HumanPlayer(2,6,7);
		//Player AI = new AIPlayer(2,6,7);
		int lastmove;
		
		while(board.canPlay()== true){
			lastmove = player1.playToken();
			player2.lastMove(lastmove);
			//AI.lastMove(lastmove);
			board.play(1, lastmove);		
			gui.redraw();
			if(board.isFinished()== 0){
				System.out.println("It's a tie!");
				gui.gameOver(-1);
				break;
				}
			if(board.isFinished()== 1){
				System.out.println("Player 1 Wins!");
				gui.gameOver(1);
				break;
				}
			if(board.isFinished()== 2){
				System.out.println("Player 2 Wins!");
				gui.gameOver(2);
				break;
				}
			lastmove = player2.playToken();
			//lastmove = AI.playToken();
			player1.lastMove(lastmove);
			board.play(2, lastmove);		
			gui.redraw();
			if(board.isFinished()== 0){
				System.out.println("It's a tie!");
				gui.gameOver(-1);
				break;
				}
			if(board.isFinished()== 1){
				System.out.println("Player 1 Wins!");
				gui.gameOver(1);
				break;
				}
			if(board.isFinished()== 2){
				System.out.println("Player 2 Wins!");
				gui.gameOver(2);
				break;
			}
			}
				gui.close();
	}
}
	