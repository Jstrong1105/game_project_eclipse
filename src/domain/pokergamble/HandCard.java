package domain.pokergamble;

import java.util.ArrayList;
import java.util.List;

import domain.trumpcard.Card;
import domain.trumpcard.CardPrinter;

/*
 * 각각의 플레이어가 가지고 있는 
 * 카드를 관리하는 클래스
 */
class HandCard
{
	HandCard()
	{
		handCard = new ArrayList<Card>();
		printer = new CardPrinter();
	}
	
	private List<Card> handCard;	// 각각의 카드
	private CardPrinter printer;	// 카드 출력
	
	// 카드 오픈하기
	void openCard(int index)
	{
		if(handCard.size() < index)
		{
			throw new IllegalArgumentException("존재하지 않는 카드입니다.");
		}
		
		handCard.get(index).openCard();
	}
	
	// 카드 받기
	void drawCard(Card card)
	{
		handCard.add(card);
	}
	
	// 카드 개수 반환하기
	int countCard()
	{
		return handCard.size();
	}
	
	// 카드 출력하기
	void printCard()
	{
		printer.printCard(handCard);
	}
}
