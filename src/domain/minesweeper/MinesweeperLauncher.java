package domain.minesweeper;

import java.time.Duration;
import java.time.Instant;

import domain.base.GameResult;
import domain.base.GameResultType;
import domain.base.GameTemplate;
import util.InputHandler;
import util.ScreenCleaner;

class MinesweeperLauncher extends GameTemplate
{
	MinesweeperLauncher(MinesweeperOption option)
	{
		SIZE = option.getSize();
		WEIGHT = option.getWeight();
	}
	
	private final int SIZE;		// 보드판의 가로 세로 길이
	private final int WEIGHT;	// 폭탄 배율
	
	private CellBoard board; 	// 보드판
	
	private int playerX;		// 플레이어가 입력한 열번호
	private int playerY;		// 플레이어가 입력한 행번호
	private boolean first;		// 첫 입력 여부
	private boolean open;		// 오픈 / 깃발 선택 여부
	private final int openNumber = 1;
	private final int flagNumber = 2;
	private Instant startTime;	// 시작한 시간
	private Instant endTime;	// 종료시간
	
	private final int[] LEVEL_LIST = {10,5,4};
	
	@Override
	protected void initialize()
	{
		ScreenCleaner.cleanScreen();
		
		InputHandler.readString("지뢰찾기 게임입니다. 준비되면 엔터를 눌러주세요.");
		
		System.out.println();
		
		int level = InputHandler.readIntRange("레벨을 선택해주세요. (1~" + LEVEL_LIST.length + ") : ", 1, LEVEL_LIST.length);
		int mineCount = ((SIZE * SIZE) / LEVEL_LIST[level-1]) * WEIGHT;
		// 폭탄의 수는 보드판의 크기 * 레벨 * 폭탄 배율
		board = new CellBoard(SIZE, mineCount);
		first = true;
		startTime = Instant.now();
	}

	@Override
	protected void render()
	{
		ScreenCleaner.cleanScreen();
		
		board.printBoard();
	}

	@Override
	protected void input()
	{
		System.out.println();
		
		// 사용자 입력 초기화
		playerX = 0;
		playerY = 0;
		
		playerX = InputHandler.readIntRange("열 번호 입력 (1~" + SIZE +") : ", 1, SIZE);
		playerY = InputHandler.readIntRange("행 번호 입력 (1~" + SIZE +") : ", 1, SIZE);
		open = InputHandler.readBoolean("오픈("+openNumber+")/깃발("+flagNumber+") : ", Integer.toString(openNumber), Integer.toString(flagNumber));
		
		System.out.println();
		
		// 사용자 입력을 인덱스 번호로 바꿈
		playerX--;
		playerY--;
	}

	@Override
	protected void update()
	{
		if(!open)
		{
			board.flagToggle(playerX, playerY);
			return;
		}
		else 
		{
			if(first)
			{
				first = false;
				board.firstMine(playerX, playerY);
			}
			
			if(board.isMine(playerX, playerY))
			{
				finish(new GameResult(GameResultType.LOSE));
			}
			else
			{
				board.openCell(playerX, playerY);
			}
		}
		
		if(board.isClear())
		{
			endTime = Instant.now();
			finish(new GameResult(GameResultType.WIN,(int)Duration.between(startTime, endTime).getSeconds()));
		}
	}

	@Override
	protected void finish(GameResult result)
	{
		ScreenCleaner.cleanScreen();
		
		board.openMine();
		board.printBoard();
		if(result.isWin())
		{
			System.out.println("축하합니다. 모든 폭탄을 찾아냈습니다.");
			System.out.println("클리어 시간 : " + (int)Duration.between(startTime, endTime).getSeconds() + "초");
		}
		else
		{
			System.out.println((playerX+1) + "열 " + (playerY+1)+"행은 폭탄입니다.");
		}
		endGame();
	}
}
