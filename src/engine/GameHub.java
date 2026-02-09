package engine;

import domain.base.GameLauncher;
import domain.memorygame.MemoryGameGetter;
import domain.minesweeper.MinesweeperGetter;
import domain.pokergamble.PokerGambleGetter;

public enum GameHub
{
	MINESWEEPER("지뢰찾기","지뢰가 아닌 셀을 모두 여세요!",
			MinesweeperGetter :: getGame,
			MinesweeperGetter :: setOption),
	MEMORY_GAME("메모리게임","같은 카드를 전부 맞추세요!",
			MemoryGameGetter :: getGame,
			MemoryGameGetter :: setOption),
	POKERGAMBLE("포커겜블","포커를 승리해 목표 코인을 달성하세요!",
			PokerGambleGetter :: getGame,
			PokerGambleGetter :: setOption)
	;
	
	
	GameHub(String name,String explain, GameCreater creator, Runnable optionSetter)
	{
		this.name = name;
		this.explain = explain;
		this.creator = creator;
		this.optionSetter = optionSetter;
	}
	
	@FunctionalInterface
	interface GameCreater
	{
		GameLauncher getGame();
	}
	
	private final String name;
	private final String explain;
	private final GameCreater creator;
	private final Runnable optionSetter;

	String getName() { return name; }
	String getExplain() { return explain; }
	
	GameLauncher getGame()
	{
		return creator.getGame();
	}
	
	void setOption()
	{
		optionSetter.run();
	}
}
