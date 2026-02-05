package domain.trumpcard;

/*
 * 트럼프 카드가 가지는 모양
 */
enum CardShape
{
	SPADE('♠'),
	HEART('♥'),
	DIA('◆'),
	CLUB('♣')
	;
	
	CardShape(char shape)
	{
		this.shape = shape;
	}
	
	private final char shape;
	
	char getShape() { return shape; }
}
