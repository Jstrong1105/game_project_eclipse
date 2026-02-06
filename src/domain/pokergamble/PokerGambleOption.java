package domain.pokergamble;

class PokerGambleOption
{
	private boolean isFive = true;
	boolean getIsFive() { return isFive; }
	
	private final int MIN_TARGET = 1000;
	private final int MAX_TARGET = 10000;
	private int target = MIN_TARGET;
	
	int getMinTarget() { return MIN_TARGET; }
	int getMaxTarget() { return MAX_TARGET; }
	int getTarget() { return target; }
	
	private final int MIN_WEIGHT = 1;
	private final int MAX_WEIGHT = 3;
	private int weight = MIN_WEIGHT;
	
	int getMinWeight() { return MIN_WEIGHT; }
	int getMaxWeight() { return MAX_WEIGHT; }
	int getWeight() { return weight; }
	
	void setFive(boolean five)
	{
		this.isFive = five;
	}
	
	void setTarget(int target)
	{
		if(target < MIN_TARGET || target > MAX_TARGET)
		{
			throw new IllegalArgumentException("허용하지 않는 타겟 입니다.");
		}
		this.target = target;
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
