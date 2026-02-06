package engine;

import domain.memorygame.MemoryGameGetter;
import domain.minesweeper.MinesweeperGetter;
import domain.pokergamble.PokerGambleGetter;

enum GameHub
{
	MINESWEEPER("지뢰찾기","지뢰가 아닌 셀을 모두 여세요!",
			MinesweeperGetter :: startGame,
			MinesweeperGetter :: setOption),
	MEMORYGAME("메모리게임","같은 카드를 전부 맞추세요!",
			MemoryGameGetter :: startGame,
			MemoryGameGetter :: setOption),
	POKERGAMBLE("포커겜블","포커를 승리해 목표 코인을 달성하세요!",
			PokerGambleGetter :: startGame,
			PokerGambleGetter :: setOption)
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
