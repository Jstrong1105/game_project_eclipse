package domain.memorygame;

import domain.base.GameOptionTemplate;
import util.InputHandler;

enum MemoryGameOptionList implements GameOptionTemplate<MemoryGameOption>
{
	COUNT("카드 수","카드의 수를 결정합니다.",
			(option) -> { 
				int count = InputHandler.readIntRange(String.format("변경할 카드 수를 입력하세요 (%d~%d) : ",
					option.getMinCount(),option.getMaxCount()), 
					option.getMinCount(), option.getMaxCount());
					option.setCount(count);}),
	PAIR("페어 수","카드의 짝의 수를 결정합니다.",
			(option) -> { 
				int pair = InputHandler.readIntRange(String.format("변경할 페어 수를 입력하세요 (%d~%d) : ",
					option.getMinPair(),option.getMaxPair()), option.getMinPair(),option.getMaxPair());
					option.setPair(pair);}),
	WEIGHT("시간 가중치","보여지는 시간을 조절합니다.",
			(option)-> { 
				int weight = InputHandler.readIntRange(String.format("변경할 가중치를 입력하세요 (%d~%d) : ",
					option.getMinWeight(),option.getMaxWeight()), 
					option.getMinWeight(), option.getMaxWeight());
					option.setWeight(weight);})
	;
	
	MemoryGameOptionList(String name,String explain,OptionSetter<MemoryGameOption> setter)
	{
		this.name = name;
		this.explain = explain;
		this.setter = setter;
	}
	
	private final String name;
	private final String explain;
	private final OptionSetter<MemoryGameOption> setter;
	
	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getExplain()
	{
		return explain;
	}

	@Override
	public void setOption(MemoryGameOption option)
	{
		setter.setOption(option);
	}
}
