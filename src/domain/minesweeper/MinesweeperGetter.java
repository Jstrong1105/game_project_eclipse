package domain.minesweeper;

/*
 * 외부에서 해당 패키지에 있는 런처나 옵션 등에 접근 할 수 있는 객체
 */
public class MinesweeperGetter
{
	private static MinesweeperOption option;
	private static MinesweeperOptionList[] optionList;
	
	// 옵션 얻기
	public static MinesweeperOption getMinesweeperOption()
	{
		if(option == null)
		{
			option = new MinesweeperOption();
		}
		
		return option;
	}
	
	// 런처 얻기
	public static MinesweeperLauncher getMinesweeperLauncher()
	{
		if(option == null)
		{
			option = new MinesweeperOption();
		}

		return new MinesweeperLauncher(option);
	}
	
	// 옵션 세터 얻기
	public static MinesweeperOptionList[] getMinesweeperOptionList()
	{
		if(optionList == null)
		{
			optionList = MinesweeperOptionList.values();
		}
		
		return optionList;
	}
}
