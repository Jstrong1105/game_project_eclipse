package engine;

import domain.base.GameOptionTemplate;
import util.InputHandler;

class GameOptionMenu<T , E extends Enum<E> & GameOptionTemplate>
{
	GameOptionMenu(T option, E menu, E[] menuList)
	{
		this.option = option;
		this.menu = menu;
		this.menuList = menuList;
	}
	
	private T option;
	
	private E menu;
	
	private E[] menuList;
	
	void setOption(String gameName)
	{
		System.out.printf("====%s====\n",gameName);
		
		for(E list : menuList)
		{
			System.out.println(list.ordinal()+1 + ". " + list.getName() + " : " + list.getExplain());
		}
		
		int answer = InputHandler.readIntRange("변경할 옵션을 선택해주세요.(1~"+menuList.length+") : ", 1,menuList.length);
		
		answer--;
		
		for(E list : menuList)
		{
			if(list.ordinal() == answer)
			{
				list.setOption(option);
				break;
			}
		}
	}
}
