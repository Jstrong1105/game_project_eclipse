package domain.minesweeper;

import domain.base.GameOptionTemplate;
import util.InputHandler;

enum MinesweeperOptionList implements GameOptionTemplate<MinesweeperOption>
{
	SIZE("사이즈","보드판의 가로,세로 길이",
		(option) -> {
				int size = InputHandler.readIntRange(String.format("사이즈를 입력하세요.(%d~%d) : ",
					option.getMinSize(),option.getMaxSize()),
					option.getMinSize(),option.getMaxSize());
					option.setSize(size);}),
	WEIGHT("가중치","폭탄의 배율",
		(option) -> {
				int weight = InputHandler.readIntRange(String.format("가중치를 입력하세요.(%d~%d) : ",
					option.getMinWeight(),option.getMaxWeight()),
					option.getMinWeight(),option.getMaxWeight());
					option.setWeight(weight);})
	;
	
	MinesweeperOptionList(String name, String explain, OptionSetter<MinesweeperOption> setter)
	{
		this.name = name;
		this.explain = explain;
		this.setter = setter;
	}
	
	private final String name;
	private final String explain;
	private final OptionSetter<MinesweeperOption> setter;
	
	@Override
	public String getExplain() { return explain; }
	
	@Override
	public String getName() { return name; }
	
	@Override
	public void setOption(MinesweeperOption option)
	{
		setter.setOption(option);
	}
}
