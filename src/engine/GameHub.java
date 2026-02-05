package engine;

import domain.minesweeper.MinesweeperGetter;

enum GameHub
{
	MINESWEEPER("지뢰찾기","지뢰가 아닌 셀을 모두 여세요!",
			MinesweeperGetter :: getMinesweeperLauncher,
			MinesweeperGetter :: setOption)
	;
	
	GameHub(String name,String explain, Runnable creator, Runnable optionSetter)
	{
		this.name = name;
		this.explain = explain;
		this.creator = creator;
		this.optionSetter = optionSetter;
	}
	
	private final String name;
	private final String explain;
	private final Runnable creator;
	private final Runnable optionSetter;

	String getName() { return name; }
	String getExplain() { return explain; }
	
	void startGame()
	{
		creator.run();
	}
	
	void setOption()
	{
		optionSetter.run();
	}
}
