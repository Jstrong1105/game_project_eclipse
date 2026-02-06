package domain.minesweeper;

import java.util.Random;

/*
 * 지뢰찾기 보드판
 */
class CellBoard
{
	// 생성자
	CellBoard(int size, int mineCount)
	{
		SIZE = size;
		MINE_COUNT = mineCount;
		random = new Random();
		board = new Cell[SIZE][SIZE];
		openCellCount = 0;
		
		// 보드판 생성
		for(int i = 0; i < SIZE; i++)
		{
			for(int j = 0; j < SIZE; j++)
			{
				board[i][j] = new Cell();
			}
		}
		
		// 랜덤 폭탄 배치
		while(mineCount > 0)
		{
			int x = random.nextInt(SIZE);
			int y = random.nextInt(SIZE);
			
			if(!board[x][y].isMine())
			{
				board[x][y].setMine(true);
				mineCount--;
			}
		}
	}
	
	// 화면단에 출력할 모양
	private final char CLOSE_SHPAE = '■';
	private final char FLAG_SHPAE = '§';
	private final char MINE_SHPAE = '*';
	private final char[] OPEN_SHPAE = {'□','1','2','3','4','5','6','7','8'};
	
	private final int[] LX = {-1,-1,-1,0,0,1,1,1};	// 주변 8칸을 체크할 x 좌표 배열
	private final int[] LY = {-1,0,1,-1,1,-1,0,1};	// 주변 8칸을 체크할 y 좌표 배열
	
	private final int SIZE;			// 보드판의 가로 세로
	private final int MINE_COUNT;	// 보드판의 지뢰 개수	
	
	private final Cell[][] board;	// 보드판 객체
	private final Random random;	// 랜덤 인스턴스
	
	private int openCellCount;		// 오픈한 셀의 개수 , 클리어 체크에 사용한다.
	
	// 보드 출력
	void printBoard()
	{
		for(int i = 0; i < SIZE * 1.4; i++){System.out.print("=");}
		
		System.out.print("지뢰찾기");
		
		for(int i = 0; i < SIZE * 1.4; i++){System.out.print("=");}
		
		System.out.println();
		
		System.out.print("  행 ");
		
		for(int i = 0; i < SIZE; i++) {System.out.printf("%2d ",i+1);}
		
		System.out.println();
		
		for(int i = 0; i < SIZE; i++)
		{
			System.out.printf("%2d열 ",(i+1));
			
			for(int j = 0; j < SIZE; j++)
			{
				char shape;
				
				if(board[i][j].isFlagged()) 
				{
					shape = FLAG_SHPAE; 
				}
				else if(board[i][j].isClosed()) 
				{
					shape = CLOSE_SHPAE;
				}
				else 
				{
					if(board[i][j].isMine())
					{
						shape = MINE_SHPAE;
					}
					else
					{
						shape = OPEN_SHPAE[board[i][j].getAdjacentMines()];
					}
				}
				System.out.printf("%2c ",shape);
			}
			
			System.out.println();
		}
	}
	
	// 첫 입력이 지뢰 인 경우 지뢰을 다른 곳으로 옮기는 메소드
	void firstMine(int x, int y)
	{
		if(isOutOfBound(x,y))
		{
			throw new IllegalArgumentException("보드판의 범위를 벗어났습니다.");
		}
		
		if(board[x][y].isMine())
		{
			while(true)
			{
				int nx = random.nextInt(SIZE);
				int ny = random.nextInt(SIZE);
				
				if(!board[nx][ny].isMine())
				{
					board[nx][ny].setMine(true);
					board[x][y].setMine(false);
					break;
				}
			}
		}
	}
	
	// 깃발 입력
	void flagToggle(int x, int y)
	{
		if(isOutOfBound(x,y))
		{
			throw new IllegalArgumentException("보드판의 범위를 벗어났습니다.");
		}
		
		board[x][y].flagToggle();
	}
	
	// 폭탄 체크하기
	boolean isMine(int x,int y)
	{
		if(isOutOfBound(x,y))
		{
			throw new IllegalArgumentException("보드판의 범위를 벗어났습니다.");
		}
		
		return board[x][y].isMine();
	}
	
	// 오픈하기
	void openCell(int x,int y)
	{
		if(isOutOfBound(x,y))
		{
			throw new IllegalArgumentException("보드판의 범위를 벗어났습니다.");
		}
		
		// 닫힌 셀이 아니면 열지 않는다.
		if(!board[x][y].isClosed())
		{
			return;
		}
		
		int adjacentMines = findAdjacent(x,y);
		
		board[x][y].setAdjacentMines(adjacentMines);
		board[x][y].openCell();
		openCellCount++;
		
		// 지뢰의 개수가 0개라면 주변 8칸도 연쇄적으로 오픈
		if(adjacentMines == 0)
		{
			for(int i = 0; i < 8; i++)
			{
				int nx = x + LX[i];
				int ny = y + LY[i];
				
				// 보드판의 범위 안에 있을때만
				if(!isOutOfBound(nx, ny)) 
				{
					openCell(nx,ny);
				}
			}
		}
	}
	
	// 클리어 체크
	boolean isClear()
	{
		return openCellCount >= SIZE * SIZE - MINE_COUNT;
	}
	
	// 클리어 시 모든 지뢰 공개 메소드
	void openMine()
	{
		for(int i = 0; i < SIZE; i++)
		{
			for(int j = 0; j < SIZE; j++)
			{
				board[i][j].openMine();
			}
		}
	}
	
	// 주변 8칸의 지뢰 개수 체크하기
	private int findAdjacent(int x,int y)
	{
		int n = 0;
		
		for(int i = 0; i < 8; i++)
		{
			int nx = x + LX[i];
			int ny = y + LY[i];
			
			// 범위 안에 있다면 체크
			if(!isOutOfBound(nx,ny)) 
			{
				if(board[nx][ny].isMine())
				{
					n++;
				}
			}
		}
		
		return n;
	}
	
	// 범위 체크
	private boolean isOutOfBound(int x, int y)
	{
		return x < 0 || y < 0 || x >= SIZE || y >= SIZE;
	}
}
