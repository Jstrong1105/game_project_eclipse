package domain.minesweeper;

import domain.base.GameLauncher;
import domain.base.GameOptionMenu;

/*
 * 외부에서 해당 패키지에 있는 런처나 옵션 등에 접근 할 수 있는 객체
 */
public class MinesweeperGetter
{
	private static MinesweeperOption option;
	private static MinesweeperOptionList[] list;
	
	// 옵션 얻기
	private static MinesweeperOption getMinesweeperOption()
	{
		if(option == null)
		{
			option = new MinesweeperOption();
		}
		
		return option;
	}
	
	// 옵션 리스트 얻기
	private static MinesweeperOptionList[] getMinesweeperOptionList()
	{
		if(list == null)
		{
			list = MinesweeperOptionList.values();
		}
		
		return list;
	}
	
	// 시작하기
	public static void startGame()
	{
		GameLauncher game =  new MinesweeperLauncher(getMinesweeperOption());
		game.run();
	}
	
	// 옵션 수정하기
	public static void setOption()
	{	
		GameOptionMenu<MinesweeperOption,MinesweeperOptionList> optionMenu = new GameOptionMenu<>(getMinesweeperOption(),getMinesweeperOptionList());
		optionMenu.setOption("지뢰찾기 옵션");
	}
}
