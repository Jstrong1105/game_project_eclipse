package domain.trumpcard;

import java.util.ArrayList;
import java.util.List;

public class CardPrinter
{
	private final char[] numberShape = {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
	private final char hiddenShape = '?';
	
	public void printCard(List<Card> cardList)
	{
		int size = cardList.size();
		
		for(int i = 0; i < size; i++) { System.out.print("┌───────┐ "); }
		System.out.println();
		for(Card card : cardList) 
		{ 
			if(card.isOpen())
			{
				System.out.printf("│%c      │ ",card.getShape());
			}
			else
			{
				System.out.printf("│%c      │ ",hiddenShape);
			}
		}
		System.out.println();
		for(Card card : cardList)
		{
			if(card.isOpen())
			{
				System.out.printf("│   %c   │ ",numberShape[card.getNumber()-2]);
			}
			else
			{
				System.out.printf("│   %c   │ ",hiddenShape);
			}
		}
		System.out.println();
		for(Card card : cardList) 
		{
			if(card.isOpen())
			{
				System.out.printf("│      %c│ ",card.getShape());
			}
			else
			{
				System.out.printf("│      %c│ ",hiddenShape);
			}
			
		}
		System.out.println();
		for(int i = 0; i < size; i++) { System.out.print("└───────┘ "); }
	}
	
	// 테스트용 메인 메소드
	public static void main(String[] args)
	{
		CardPrinter printer = new CardPrinter();
		
		List<Card> cardList = new ArrayList<>();
		
		CardDeck deck = new CardDeck();
		
		cardList.add(deck.drawCard());
		cardList.add(deck.drawCard());
		cardList.add(deck.drawCard());
		cardList.add(deck.drawCard());
		cardList.add(deck.drawCard());
		cardList.add(deck.drawCard());
		cardList.add(deck.drawCard());
		
		printer.printCard(cardList);
	} 	
}
