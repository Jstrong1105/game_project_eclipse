package domain.pokergamble;

import java.time.Duration;

import java.time.Instant;

import domain.base.GameResult;
import domain.base.GameResultType;
import domain.base.GameTemplate;
import domain.trumpcard.Card;
import domain.trumpcard.CardDeck;
import util.InputHandler;
import util.ScreenCleaner;

/*
 * 포커겜블 구체화 객체
 */
class PokerGambleLauncher extends GameTemplate
{
	PokerGambleLauncher(PokerGambleOption option)
	{
		CARD_COUNT = option.getIsFive() ? 5 : 7;
		TARGET = option.getTarget();
		WEIGHT = option.getWeight();
	}
	
	private final int TARGET;		// 목표 코인
	private final int WEIGHT;		// 보상 가중치
	private final int CARD_COUNT;	// 카드 개수
	
	private HandCard playerCard;	// 플레이어 카드
	private HandCard cpuCard;		// cpu 카드
	private CardDeck cardDeck;		// 카드덱
	
	private PokerRankingResult playerResult; 
	private PokerRankingResult cpuResult;
	
	private int totalBetCoin;		// 현재 판에 걸린 총 코인
	private int betCoin;			// 지금 건 코인
	private int playerCoin;			// 플레이어가 보유한 코인
	private final int MIN_BET_COIN = 5;	// 기본 베팅금
	
	private int[] levelList = {4,5,10};	// 난이도
	
	private int[] openCard = {2,3,4,5};
	private int[] hiddenCard = {0,1,6};
	
	private Instant startTime;	// 시작 시간
	private Instant endTime;	// 종료 시간
	
	@Override
	protected void initialize()
	{
		ScreenCleaner.cleanScreen();
		
		InputHandler.readString("포커 겜블 게임입니다. 준비되면 엔터를 눌러주세요.");
		
		System.out.println();
		
		int level = InputHandler.readIntRange("난이도를 입력해주세요. (1~3) : ", 1, 3);
		
		playerCoin = TARGET / levelList[level-1];
		
		startTime = Instant.now();
		
		roundInitialize();
	}
	
	// 라운드 초기화
	private void roundInitialize()
	{
		totalBetCoin = 0;
		
		playerCard = new HandCard();
		cpuCard = new HandCard();
		cardDeck = new CardDeck();
		
		playerResult = null;
		cpuResult = null;
		
		// 카드 나눠주기
		drawCard();
		drawCard();
		
		// 기본 베팅
		if(playerCoin >= MIN_BET_COIN)
		{
			totalBetCoin += MIN_BET_COIN;
			playerCoin -= MIN_BET_COIN;
		}
		else
		{
			totalBetCoin += playerCoin;
			playerCoin = 0;
		}
	}
	
	@Override
	protected void render()
	{
		for(int i : openCard)
		{
			if(i < cpuCard.countCard())
			{
				cpuCard.openCard(i);
			}
		}
		
		ScreenCleaner.cleanScreen();
		
		cpuCard.printCard();
		System.out.println(" cpu 카드");
		
		System.out.println();
		
		playerCard.printCard();
		System.out.println(" 당신의 카드");
		System.out.println("목표 코인 : " + TARGET);
		System.out.println("현재 베팅 코인 : " + totalBetCoin);
		System.out.println("당신의 코인 : " + playerCoin);
	}
	
	@Override
	protected void input()
	{
		betCoin = 0;
		betCoin = InputHandler.readIntRange("베팅 금액을 입력 (0~" + playerCoin + ") : ", 0, playerCoin);
		
		totalBetCoin += betCoin;
		playerCoin -= betCoin;
	}
	
	@Override
	protected void update()
	{
		// 코인이 남아 있는데 0 을 베팅하면 기권
		if(betCoin == 0 && playerCoin > 0)
		{
			roundFinish(new GameResult(GameResultType.FOLD));
		}
		
		// 카드를 개수 만큼 받지 않았다면
		else if(playerCard.countCard() < CARD_COUNT) 
		{
			drawCard();
		}
		
		// 모든 카드를 받았다면
		else
		{
			playerResult = playerCard.getResult();
			cpuResult = cpuCard.getResult();
			
			int result = playerResult.compareTo(cpuResult);
			
			if(result > 0)
			{
				roundFinish(new GameResult(GameResultType.WIN));
			}
			else if(result < 0)
			{
				roundFinish(new GameResult(GameResultType.LOSE));
			}
			else
			{
				roundFinish(new GameResult(GameResultType.DRAW));
			}
		}
	}
	
	// 라운드 종료
	private void roundFinish(GameResult result)
	{
		if(result.isFold())
		{
			System.out.println("기권했습니다.");
		}
		else 
		{
			for(int i : hiddenCard)
			{
				if(i < cpuCard.countCard())
				{
					cpuCard.openCard(i);
				}
			}
			
			cpuCard.printCard();
			System.out.println(cpuResult.getName());
			
			playerCard.printCard();
			System.out.println(playerResult.getName());
			
			if(result.isWin())
			{
				System.out.println("승리했습니다.");
				playerCoin += totalBetCoin * 2 * WEIGHT;
			}
			else if(result.isLose())
			{
				System.out.println("패배했습니다.");
			}
			else if(result.isDraw())
			{
				System.out.println("무승부입니다.");
				playerCoin += totalBetCoin;
			}
		}
		
		if(playerCoin >= TARGET)
		{
			endTime = Instant.now();
			finish(new GameResult(GameResultType.WIN,(int)Duration.between(startTime, endTime).getSeconds()));
		}
		else if(playerCoin <= 0)
		{
			finish(new GameResult(GameResultType.LOSE));
		}
		else
		{
			roundInitialize();
		}
	}
	
	@Override
	protected void finish(GameResult result)
	{
		if(result.isWin())
		{
			System.out.println("목표를 달성했습니다.");
		}
		else
		{
			System.out.println("모든 코인을 소진했습니다.");
		}
		
		endGame();
	}
	
	// 카드 나눠주기
	private void drawCard()
	{
		Card card = cardDeck.drawCard();
		card.openCard();
		playerCard.drawCard(card);
		
		cpuCard.drawCard(cardDeck.drawCard());
	}
}

