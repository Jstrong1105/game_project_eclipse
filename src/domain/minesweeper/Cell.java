package domain.minesweeper;

/*
 * 지뢰찾기 한칸
 */
class Cell
{
	private CellStatus status;	// OPEN / CLOSED / FLAGGED
	private boolean mine;		// 지뢰 여부
	private int adjacentMines;  // 인접한 폭탄의 개수
	
	Cell()
	{
		status = CellStatus.CLOSED;
		mine = false;
		adjacentMines = 0;
	}
	
	void setMine(boolean mine) { this.mine = mine; }
	void setAdjacentMines(int adjacentMines) 
	{ 
		if(adjacentMines < 0 || adjacentMines > 8)
		{
			throw new IllegalArgumentException("잘못된 지뢰 수 입니다.");
		}
		
		this.adjacentMines = adjacentMines; 
	}
	
	boolean isMine() { return mine; }
	int getAdjacentMines() { return adjacentMines; }
	
	boolean isOpen() { return status == CellStatus.OPEN; }
	boolean isClosed() { return status == CellStatus.CLOSED; }
	boolean isFlagged() { return status == CellStatus.FLAGGED; } 
	
	// 한칸 열기
	void openCell()
	{
		if(status == CellStatus.CLOSED)
		{
			status = CellStatus.OPEN;
		}
	}
	
	// 깃발 토글하기
	void flagToggle()
	{
		if(status == CellStatus.CLOSED)
		{
			status = CellStatus.FLAGGED;
		}
		else if(status == CellStatus.FLAGGED)
		{
			status = CellStatus.CLOSED;
		}
	}
	
	// 게임이 종료되었을때 실행되는 지뢰 공개 
	void openMine()
	{
		if(mine)
		{
			status = CellStatus.OPEN;
		}
	}
}
