package domain.playerdata;

import java.io.Serializable;

import domain.base.GameResult;

/*
 * 게임의 결과를 담을 객체
 */
public class GameData implements Serializable
{
	private static final long serialVersionUID = 1L;

	GameData()
	{
		tryCount = 0;
		clearCount = 0;
		minClearTime = Integer.MAX_VALUE;
	}
	
	private int tryCount;
	private int clearCount;
	private int minClearTime;
	
	public void update(GameResult result)
	{
		tryCount++;
		if(result.isWin())
		{
			clearCount++;
			
			if(minClearTime > result.getClearTime())
			{
				minClearTime = result.getClearTime();
			}
		}
	}
	
	public int getTry() { return tryCount; }
	public int getClear() { return clearCount; }
	public int getTime() { return minClearTime == Integer.MAX_VALUE ? -1 : minClearTime; }
}
