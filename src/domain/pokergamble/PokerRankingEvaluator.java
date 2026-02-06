package domain.pokergamble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import domain.trumpcard.Card;

/*
 * 카드 뭉치를 받아서 족보를 반환하는 족보 계산기
 */
class PokerRankingEvaluator
{
	// 생성자
	PokerRankingEvaluator(List<Card> handCard)
	{
		this.handCard = handCard;
	}
	
	private final List<Card> handCard; 	// 받은 족보
	
	@FunctionalInterface
	interface Evaluator
	{
		PokerRankingResult evaluate();
	}
	
	private final List<Evaluator> evaluator = Arrays.asList	// 계산 순서
			(
					this::straightFlush,
					this::fourOfAKind,
					this::fullHouse,
					this::flush,
					this::straight,
					this::threeOfAKind,
					this::twoPair,
					this::onePair,
					this::highCard
			);
			
	// 외부에서 호출하는 계산 메소드
	PokerRankingResult getResult()
	{
		if(handCard.size() < 5)
		{
			throw new IllegalArgumentException("카드는 5장 이상이여야 합니다.");
		}

		PokerRankingResult result;
		
		dataCounting();
		
		for(Evaluator eval : evaluator)
		{
			result = eval.evaluate();
			if(result != null)
			{
				return result;
			}
		}
		
		throw new IllegalStateException("족보 계산에 오류가 발생했습니다.");
	}
	
	// 사전에 어떤 모양이 몇개고 어떤 숫자가 몇개 있는지 계산하여 담아 둘 변수들
	
	private final int[] numberCount = new int[13];	
	// 카드의 개수
	
	private final List<Integer> numberOrder = new ArrayList<>();
	// 모든 카드의 숫자를 내림차순 정렬
	
	private final HashMap<Integer,Integer> pairCount = new HashMap<>();
	// 1장 : 몇개 , 2장 : 몇개 , 3장 : 몇개 , 4장 : 몇개
	
	private final HashMap<Character, Integer> shapeCount = new HashMap<>();	
	// 모양 : 개수
	
	private final int[] flushCount = new int[13];
	// 플러시에 해당하는 카드의 개수
	
	private List<Integer> flushOrder = new ArrayList<>();
	// 플러시 숫자를 내림차순 정렬
	
	private boolean flush = false;
	// 플러시 여부
	
	// 족보 계산하기 전에 카드를 분석하는 메소드
	private void dataCounting()
	{
		// 초기화
		pairCount.put(1, 0);
		pairCount.put(2, 0);
		pairCount.put(3, 0);
		pairCount.put(4, 0);
		
		for(Card card : handCard)
		{
			shapeCount.put(card.getShape(),shapeCount.getOrDefault(card.getShape(),0)+1);
			numberCount[card.getNumber()-2] += 1;
			numberOrder.add(card.getNumber());
		}
		
		numberOrder.sort(Collections.reverseOrder());
		
		for(int i : numberCount)
		{
			if(i > 0)
			{
				pairCount.put(i, pairCount.getOrDefault(i,0) + 1);
			}
		}
		
		for(char shape : shapeCount.keySet())
		{
			// 5 / 7 포커 에서 플러시를 만족하는 문양은 2개 이상일 수 없다.
			if(shapeCount.get(shape) >= 5)
			{
				flush = true;
				
				for(Card card : handCard)
				{
					if(card.getShape() == shape)
					{
						flushCount[card.getNumber()-2] += 1;
						flushOrder.add(card.getNumber());
					}
				}
				
				flushOrder.sort(Collections.reverseOrder());
				break;
			}
		}
		
		// 여기까지 하면 판독을 위한 준비는 끝
	}
	
	// int[] 을 넘겨주면 스트레이트인지 판독해서 맞다면 스트레이트를 이루는 가장 큰 카드를
	// 아니라면 -1을 넘겨주는 메소드
	private int straightNumber(int[] count)
	{
		int n = 0;
		
		for(int i = count.length - 1; i >= 0; i--)
		{
			if(count[i] >= 1)
			{
				n++;
			}
			else
			{
				n = 0;
			}
			
			if(n >= 5)
			{
				return i + 6;
			}
		}
		
		// 백스트레이트라면 
		if(count[12] >= 1 && count[0] >= 1 && count[1] >= 1 && count[2] >= 1 && count[3] >= 1)
		{
			return 5;
		}
		
		return -1;
	}
	
	// 스트레이트 플러시 판독기
	private PokerRankingResult straightFlush()
	{
		if(flush)
		{
			int straight = straightNumber(flushCount);
			
			if(straight != -1)
			{
				PokerRankingResult result;
				
				if(straight == 14)
				{
					result = new PokerRankingResult(PokerRankingList.ROYAL_FLUSH);
					result.addKicker(straight);
					return result;
				}
				else
				{
					result = new PokerRankingResult(PokerRankingList.STRAIGHT_FLUSH);
					result.addKicker(straight);
					return result;
				}
			}
		}
		
		return null;
	}
	
	// 포카드 판독기
	private PokerRankingResult fourOfAKind()
	{
		// 포카드 인가?
		if(pairCount.get(4) >= 1)
		{
			PokerRankingResult result = new PokerRankingResult(PokerRankingList.FOUR_OF_A_KIND);
			
			// 어떤 카드가 4장인가?
			for(int i = numberCount.length -1; i >= 0; i--)
			{
				if(numberCount[i] >= 4)
				{
					result.addKicker(i+2);
					
					// 4장을 이룬 카드를 제외하고 가장 큰 카드가 무엇인가?
					for(int j : numberOrder)
					{
						if(j != i+2)
						{
							result.addKicker(j);
							return result;
						}
					}
				}
			}
		}
		
		return null;
	}
	
	// 풀하우스 판독기
	private PokerRankingResult fullHouse()
	{
		int three = pairCount.get(3);
		int two = pairCount.get(2);
		
		if(three >= 2 || (three >= 1 && two >= 1))
		{
			PokerRankingResult result = new PokerRankingResult(PokerRankingList.FULL_HOUSE);
			
			
			
			return result;
		}
		
		return null;
	}
	
	// 플러시 판독기
	private PokerRankingResult flush()
	{
		return null;
	}
	
	// 스트레이트 판독기
	private PokerRankingResult straight()
	{
		return null;
	}
	
	// 트리플 판독기
	private PokerRankingResult threeOfAKind()
	{
		return null;
	}
	
	// 투페어 판독기
	private PokerRankingResult twoPair()
	{
		return null;
	}
	
	// 원페어 판독기
	private PokerRankingResult onePair()
	{
		return null;
	}
	
	// 탑 판독기
	private PokerRankingResult highCard()
	{
		return null;
	}
}

