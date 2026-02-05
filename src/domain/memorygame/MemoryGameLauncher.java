package domain.memorygame;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import domain.base.GameResult;
import domain.base.GameResultType;
import domain.base.GameTemplate;
import util.GameSleeper;
import util.InputHandler;

/*
 * 메모리 게임 구체화 런처
 */
class MemoryGameLauncher extends GameTemplate
{
	MemoryGameLauncher(MemoryGameOption option)
	{
		COUNT = option.getCount();
		PAIR = option.getPair();
		WEIGHT = option.getWeight();
	}
	
	private final int COUNT;	// 카드의 개수
	private final int PAIR;		// 카드의 짝
	private final int WEIGHT; 	// 시간 가중치
	
	private MemoryTable table;	// 바닥에 깔린 카드
	
	private List<Integer> playerNumber;	// 플레이어가 선택한 카드의 숫자
	
	private int openCount;
	
	private Instant startTime;	// 시작한 시간
	
	@Override
	protected void initialize()
	{	
		InputHandler.readString("메모리 게임입니다. 준비 되면 엔터를 눌러주세요.");

		// 속성 초기화
		openCount = 0;
		
		table = new MemoryTable(COUNT, PAIR);
		
		playerNumber = new ArrayList<Integer>();
		
		// 모든 카드 오픈
		for(int i = 0; i < COUNT * PAIR; i++)
		{
			table.openCard(i);
		}
		
		InputHandler.readString("엔터를 누르면 카드가 보여집니다. 카드를 기억하세요!");
		
		table.printCard();
		
		// 10초 * 가중치 만큼 보여준다.
		GameSleeper.sleepGame(10 * WEIGHT);
		
		cleanScreen();
		
		// 모든 카드 숨김
		for(int i = 0; i < COUNT * PAIR; i++)
		{
			table.hiddenCard(i);
		}
	
		startTime = Instant.now();
	}

	@Override
	protected void render()
	{
		table.printCard();
		System.out.println();
	}

	@Override
	protected void input()
	{
		playerNumber.clear();
		
		for(int i = 0; i < PAIR; i++)
		{
			while(true)
			{
				int answer = InputHandler.readIntRange("카드를 선택하세요.("+1+"~"+(COUNT*PAIR)+") : ", 1, COUNT*PAIR);
				
				answer--;
				
				if(!table.isOpen(answer))
				{
					playerNumber.add(answer);
					table.openCard(answer);
					break;
				}
				else
				{
					System.out.println("이미 오픈된 카드입니다.");
				}
			}
			
			cleanScreen();
			render();
		}
	}

	@Override
	protected void update()
	{
		for(int i = 0; i < playerNumber.size() - 1; i++)
		{
			// 한장이라도 다르다면 전부 다시 숨긴다.
			if(!table.drawCard(playerNumber.get(i)).equals(table.drawCard(playerNumber.get(i+1))))
			{
				System.out.println("다른 카드입니다.");
				
				for(int j : playerNumber)
				{
					table.hiddenCard(j);
				}
				GameSleeper.sleepGame(3 * WEIGHT);
				
				cleanScreen();
				return;
			}
		}
		
		System.out.println("같은 카드입니다.");
		
		GameSleeper.sleepGame(1);
		
		cleanScreen();
		
		openCount += PAIR;
		
		if(openCount >= PAIR * COUNT)
		{
			finish(new GameResult(GameResultType.WIN));
		}
	}

	@Override
	protected void finish(GameResult result)
	{
		System.out.println("모든 카드를 맞췄습니다.");
	
		Instant endTime = Instant.now();
		
		System.out.printf("소요시간 : %d초\n",Duration.between(startTime, endTime).getSeconds());
		
		endGame();
	}
	
	// 화면 청소 
	private void cleanScreen()
	{
		for(int i = 0; i < 30; i++)
		{
			System.out.println();
		}
	}
}
