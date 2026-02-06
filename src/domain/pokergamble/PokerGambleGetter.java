package domain.pokergamble;

import domain.base.GameLauncher;
import domain.base.GameOptionMenu;

public class PokerGambleGetter
{
	private static PokerGambleOption option;
	private static PokerGambleOptionList[] list;
	
	private static PokerGambleOption getOption()
	{
		if(option == null)
		{
			option = new PokerGambleOption();
		}
		
		return option;
	}
	
	private static PokerGambleOptionList[] getList()
	{
		if(list == null)
		{
			list = PokerGambleOptionList.values();
		}
		
		return list;
	}
	
	public static void startGame()
	{
		GameLauncher game = new PokerGambleLauncher(getOption());
		game.run();
	}
	
	public static void setOption()
	{
		GameOptionMenu<PokerGambleOption, PokerGambleOptionList> menu = new GameOptionMenu<>(getOption(), getList());
		menu.setOption("포커겜블 옵션");
	}
}
