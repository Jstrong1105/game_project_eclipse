package domain.minesweeper;

/*
 * 지뢰찾기 옵션
 */
class MinesweeperOption
{
	// 생성자를 default 로 두어서 패키지 내에 있는 Getter 객체로만 생성이 가능하도록 함
	MinesweeperOption()
	{
		;
	}
	
	private final int MAX_SIZE = 20;
	private final int MIN_SIZE = 10;
	private int size = MIN_SIZE;
	
	int getMaxSize() { return MAX_SIZE; }
	int getMinSize() { return MIN_SIZE; }
	
	private int MAX_WEIGHT = 3;
	private int MIN_WEIGHT = 1;
	private int weight = MIN_WEIGHT;
	
	int getMaxWeight() { return MAX_WEIGHT; }
	int getMinWeight() { return MIN_WEIGHT; }
	
	int getSize(){ return size; }
	int getWeight() { return weight; }
	
	void setSize(int size)
	{
		if(size < MIN_SIZE || size > MAX_SIZE)
		{
			throw new IllegalArgumentException("허용하지 않는 사이즈입니다.");
		}
		
		this.size = size;
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
