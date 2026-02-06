package domain.pokergamble;

import domain.base.GameOptionTemplate;
import util.InputHandler;

enum PokerGambleOptionList implements GameOptionTemplate<PokerGambleOption>
{
	FIVE_SEVEN("5/7 포커","최대로 받을 카드 장수를 결정합니다.",
			(option) -> {
			boolean five = InputHandler.readBoolean("1. 5포커, 2. 7포커 : ", "1", "2");
					option.setFive(five);}),
	TARGET_COIN("목표 코인","목표로 할 코인을 결정합니다.",
			(option) -> {
			int target = InputHandler.readIntRange(String.format("목표 코인을 입력하세요. (%d~%d) : ", 
					option.getMinTarget(),option.getMaxTarget()	),
					option.getMinTarget(),option.getMaxTarget());
					option.setTarget(target);}),
	WEIGHT("가중치","승리 시 획득하는 배율을 결정합니다.",
			(option) -> {
			int weight = InputHandler.readIntRange(String.format("가중치를 입력하세요. (%d~%d) : ", 
					option.getMinWeight(),option.getMaxWeight()  ), 
					option.getMinWeight(),option.getMaxWeight());
					option.setWeight(weight);})
	;

	PokerGambleOptionList(String name, String explain, OptionSetter<PokerGambleOption> setter)
	{
		this.name = name;
		this.explain = explain;
		this.setter = setter;
	}
	
	private final String name;
	private final String explain;
	private final OptionSetter<PokerGambleOption> setter;
	
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
	public void setOption(PokerGambleOption option)
	{
		setter.setOption(option);
	}
}
