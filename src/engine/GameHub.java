package engine;

import domain.base.GameLauncher;
import domain.minesweeper.MinesweeperGetter;
import domain.minesweeper.MinesweeperOption;
import domain.minesweeper.MinesweeperOptionList;

enum GameHub
{
	MINESWEEPER("지뢰찾기","지뢰가 아닌 셀을 모두 여세요!",
			MinesweeperGetter :: getMinesweeperLauncher,
			() -> {
				GameOptionMenu<MinesweeperOption, MinesweeperOptionList> menu = 
			new GameOptionMenu<>
				(MinesweeperGetter.getMinesweeperOption(),
				 MinesweeperGetter.getMinesweeperOptionList());
				 menu.setOption("지뢰찾기옵션");
			});
	
	;
	GameHub(String name,String explain, GameMaker creater, Runnable optionSetter)
	{
		this.name = name;
		this.explain = explain;
		this.creater = creater;
		this.optionSetter = optionSetter;
	}
	
	private final String name;
	private final String explain;
	private final GameMaker creater;
	private final Runnable optionSetter;
	
	@FunctionalInterface
	interface GameMaker
	{
		GameLauncher makeGame();
	}

	String getName() { return name; }
	String getExplain() { return explain; }
	
	void startGame()
	{
		creater.makeGame().run();
	}
	
	void setOption()
	{
		optionSetter.run();
	}
}
