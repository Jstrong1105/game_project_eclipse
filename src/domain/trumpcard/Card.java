package domain.trumpcard;

/*
 * 트럼프 카드 한장 객체
 */
public class Card
{
	// 외부에서 사용은 가능하지만
	// 외부에서 생성은 불가능하다.
	// 생성은 같은 패키지내에 있는 
	// 카드덱 클래스를 이용해서만 가능하다.
	Card(CardShape shape, int number)
	{
		if(number < MIN_NUMBER || number > MAX_NUMBER)
		{
			throw new IllegalArgumentException("잘못된 카드 숫자입니다.");
		}
		
		this.shape = shape;
		this.number = number;
		status = CardStatus.HIDDEN;
	}
	
	private final int MIN_NUMBER = 2;
	private final int MAX_NUMBER = 14;
	
	private CardStatus status;
	private final CardShape shape;
	private final int number;
	
	public boolean isOpen() { return status == CardStatus.OPEN; }
	public boolean isClosed() { return status == CardStatus.HIDDEN; }
	public char getShape() { return shape.getShape(); }
	public int getNumber() { return number; }
	
	public void openCard()
	{
		status = CardStatus.OPEN;
	}
	
	public void hiddenCard()
	{
		status = CardStatus.HIDDEN;
	}
	
	public Card copy()
	{
		return new Card(this.shape,this.number);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(this == o) { return true; }
		if(o == null) { return false; }
		if(this.getClass() != o.getClass()) { return false; }
		
		Card card = (Card) o;
		
		return (this.shape == card.shape) && (this.number == card.number);
	}
	
	@Override
	public int hashCode()
	{
		return shape.ordinal() * 31 + number;
	}
}
