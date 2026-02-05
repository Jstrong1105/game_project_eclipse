package domain.memorygame;

class MemoryGameOption
{
	private final int MAX_PAIR = 4;
	private final int MIN_PAIR = 2;
	private int pair = MIN_PAIR;
	
	int getMaxPair() { return MAX_PAIR; }
	int getMinPair() { return MIN_PAIR; }
	int getPair() { return pair; }
	
	private final int MAX_COUNT = 8;
	private final int MIN_COUNT = 4;
	private int count = MIN_COUNT;
	
	int getMaxCount() { return MAX_COUNT; }
	int getMinCount() { return MIN_COUNT; }
	int getCount() { return count; }
	
	private final int MAX_WEIGHT = 3;
	private final int MIN_WEIGHT = 1;
	private int weight = MIN_WEIGHT;
	
	int getMaxWeight() { return MAX_WEIGHT; }
	int getMinWeight() { return MIN_WEIGHT; }
	int getWeight() { return weight; }
	
	void setPair(int pair)
	{
		if(pair < MIN_PAIR || pair > MAX_PAIR)
		{
			throw new IllegalArgumentException("허용하지 않는 페어입니다.");
		}
		this.pair = pair;
	}
	
	void setCount(int count)
	{
		if(count < MIN_COUNT || count > MAX_COUNT)
		{
			throw new IllegalArgumentException("허용하지 않는 숫자입니다.");
		}
		this.count = count;
	}
	
	void setWeight(int weight)
	{
		if(weight < MIN_WEIGHT || weight > MAX_WEIGHT)
		{
			throw new IllegalArgumentException("허용하지 않는 가중치입니다.");
		}
		this.weight = weight;
	}
}
