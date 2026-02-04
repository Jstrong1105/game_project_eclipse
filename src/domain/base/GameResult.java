package domain.base;

/*
 * 게임 결과 객체
 */
public class GameResult
{
	private final GameResultType result;
	
	public GameResult(GameResultType result)
	{
		this.result = result;
	}
	
	public boolean isWin() { return result == GameResultType.WIN; } 
	public boolean isLose() { return result == GameResultType.LOSE; }
	public boolean isDraw() { return result == GameResultType.DRAW; }
	public boolean isFold() { return result == GameResultType.FOLD; }
	
}
