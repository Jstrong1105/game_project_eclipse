package domain.base;

/*
 * 게임 결과 객체
 */
public class GameResult
{
	public GameResult(GameResultType result)
	{
		this.result = result;
		clearTime = Integer.MAX_VALUE;
	}
	
	public GameResult(GameResultType result,int clearTime)
	{
		this.result = result;
		this.clearTime = clearTime;
	}
	
	private final GameResultType result;
	private final int clearTime;
	
	public boolean isWin() { return result == GameResultType.WIN; } 
	public boolean isLose() { return result == GameResultType.LOSE; }
	public boolean isDraw() { return result == GameResultType.DRAW; }
	public boolean isFold() { return result == GameResultType.FOLD; }
	
	public int getClearTime() { return clearTime; }
}
