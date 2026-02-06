package domain.pokergamble;

/*
 * 포커 족보들
 */
enum PokerRankingList
{
	ROYAL_FLUSH("로얄플러시",11),
	STRAIGHT_FLUSH("스트레이트플러시",10),
	FOUR_OF_A_KIND("포카드",9),
	FULL_HOUSE("풀하우스",8),
	FLUSH("플러시",7),
	MOUNTAIN("마운틴",6),
	STRAIGHT("스트레이트",5),
	BACK_STRAIGHT("백스트레이트",4),
	THREE_OF_A_KIND("트리플",3),
	TWO_PAIR("투페어",2),
	ONE_PAIR("원페어",1),
	HIGH_CARD("탑",0)
	;
	PokerRankingList(String name,int power)
	{
		this.name = name;
		this.power = power;
	}
	
	private final String name;
	private final int power;
	
	String getName() { return name; }
	int getPower() { return power; }
}
