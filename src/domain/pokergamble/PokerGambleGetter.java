package domain.pokergamble;

import domain.base.GameLauncher;
import domain.base.GameOptionMenu;

/*
 * 패키지를 외부와 연결하는 다리
 */
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
	
	public static GameLauncher getGame()
	{
		return new PokerGambleLauncher(getOption());
	}
	
	public static void setOption()
	{
		GameOptionMenu<PokerGambleOption, PokerGambleOptionList> menu = new GameOptionMenu<>(getOption(), getList());
		menu.setOption("포커겜블 옵션");
	}
}
