package domain.trumpcard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 외부에서 카드를 생성하기 위해 만드는 객체
 */
public class CardDeck
{
	private List<Card> cardDeck;
	
	public CardDeck()
	{
		cardDeck = new ArrayList<>();
		
		for(CardShape shape : CardShape.values())
		{
			for(int i = 2; i <= 14; i++)
			{
				cardDeck.add(new Card(shape,i));
			}
		}
		
		Collections.shuffle(cardDeck);
	}
	
	public Card drawCard()
	{
		if(cardDeck.isEmpty())
		{
			throw new IllegalStateException("카드덱이 비어있습니다.");
		}
		
		return cardDeck.remove(cardDeck.size()-1);
	}
}
