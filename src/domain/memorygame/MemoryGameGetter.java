package domain.memorygame;

import domain.base.GameLauncher;
import domain.base.GameOptionMenu;

/*
 * 외부에서 해당 패키지에 있는 런처나 옵션 등에 접근 할 수 있는 객체
 */
public class MemoryGameGetter
{
	private static MemoryGameOption option;
	
	private static MemoryGameOption getOption()
	{
		if(option == null)
		{
			option = new MemoryGameOption();
		}
		
		return option;
	}
	
	// 시작하기
	public static void startGame()
	{
		GameLauncher game = new MemoryGameLauncher(getOption());
		game.run();
	}
	
	// 옵션 수정
	public static void setOption()
	{
		GameOptionMenu<MemoryGameOption, MemoryGameOptionList> menu = new GameOptionMenu<MemoryGameOption, MemoryGameOptionList>(getOption(), MemoryGameOptionList.values());
		menu.setOption("메모리 게임 옵션");
	}
}
