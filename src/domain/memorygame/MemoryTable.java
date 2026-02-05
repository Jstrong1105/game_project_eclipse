package domain.memorygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.trumpcard.Card;
import domain.trumpcard.CardDeck;
import domain.trumpcard.CardPrinter;

/*
 * 카드 맞추기 할 카드를 관리할 테이블
 */
class MemoryTable
{
	private List<Card> board;
	private final int count;
	private final int pair;
	private CardPrinter printer;
	
	MemoryTable(int count, int pair)
	{
		printer = new CardPrinter();
		this.count = count;
		this.pair = pair;
		
		CardDeck cardDeck = new CardDeck();
		
		board = new ArrayList<>();
		
		for(int i = 0; i < count; i++)
		{
			Card card = cardDeck.drawCard();
			
			for(int j = 0; j < pair; j++)
			{
				board.add(card.copy());
			}
		}
		
		Collections.shuffle(board);
	}
	
	// 해당 카드 반환하기
	Card drawCard(int index)
	{
		if(index < 0 || index >= count*pair)
		{
			throw new IllegalArgumentException("존재하지 않는 인덱스입니다.");
		}
		
		return board.get(index);
	}
	
	// 해당 카드 상태 확인하기
	boolean isOpen(int index)
	{
		if(index < 0 || index >= count*pair)
		{
			throw new IllegalArgumentException("존재하지 않는 인덱스입니다.");
		}
		
		return board.get(index).isOpen();
	}
	
	// 해당 카드 열기 
	void openCard(int index)
	{
		if(index < 0 || index >= count*pair)
		{
			throw new IllegalArgumentException("존재하지 않는 인덱스입니다.");
		}
		
		board.get(index).openCard();
	}
	
	// 해당 카드 닫기
	void hiddenCard(int index)
	{
		if(index < 0 || index >= count*pair)
		{
			throw new IllegalArgumentException("존재하지 않는 인덱스입니다.");
		}
		
		board.get(index).hiddenCard();
	}
	
	// 카드 출력하기
	void printCard()
	{
		List<Card> list = new ArrayList<>();
		
		for(int i = 0; i < pair; i++)
		{
			list.clear();
			
			for(int j = 0; j < count; j++)
			{
				list.add(board.get(j+i*count));
			}
			printer.printCard(list);
			System.out.println();
		}
	}
}

