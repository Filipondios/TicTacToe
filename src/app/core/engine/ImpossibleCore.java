package app.core.engine;

import app.core.board.Board;
import app.core.board.Ownership;
import app.frames.MainFrame;

/**
 * @author Filipondios
 * @version 01.12.2022
 */
public final class ImpossibleCore implements Core {
			
	@Override
	public void makeMove() {
		
		Board board = MainFrame.game_board;
		int bestScore = Integer.MIN_VALUE;
		int move = 0;
		
		for (int i = 0; i < 9; i++) {
			if(board.getCell(i).owner == Ownership.NONE) {
				
				board.markTileNoColor(i, Ownership.AI); //Perform an available move
				int score = minimax(board, 0, false); // See how good that move is
				board.markTileNoColor(i, Ownership.NONE); // Undo move
				
				if(score > bestScore) {
					bestScore = score;
					move = i;
				}
			}
		}
		board.markTile(move, Ownership.AI);
	}
	
	private int minimax(Board target, int depth, boolean isMaximizing) {
		
		int result = target.evaluate(); // Terminal node
		if(result != -2) return result;
	
		int bestScore;	
		if(isMaximizing) {
			bestScore = Integer.MIN_VALUE;
			
			for (int i = 0; i < 9; i++) {
				if(target.getCell(i).owner == Ownership.NONE) {
					
					target.markTileNoColor(i, Ownership.AI); //Perform an available move
					int score = minimax(target, depth+1, false); // See how good that move is
					target.markTileNoColor(i, Ownership.NONE); // Undo move
					bestScore = Math.max(score, bestScore);
				}
			}
			
		}else {
			bestScore = Integer.MAX_VALUE;
			
			for (int i = 0; i < 9; i++) {
				if(target.getCell(i).owner == Ownership.NONE) {
					
					target.markTileNoColor(i, Ownership.PLAYER); //Perform an available move
					int score = minimax(target, depth+1, true); // See how good that move is
					target.markTileNoColor(i, Ownership.NONE); // Undo move
					bestScore = Math.min(score, bestScore);
				}
			}
		}
		return bestScore;
	}
}