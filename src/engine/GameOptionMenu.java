package engine;

import domain.base.GameOptionTemplate;
import util.InputHandler;

class GameOptionMenu<T , E extends Enum<E> & GameOptionTemplate<T>>
{
	GameOptionMenu(T option, E[] menuList)
	{
		this.option = option;
		this.menuList = menuList;
	}
	
	private final T option;
	
	private final E[] menuList;
	
	void setOption(String gameName)
	{
		while(true)
		{
			System.out.printf("====%s====\n",gameName);
			
			System.out.println("0. 뒤로가기");
			
			for(E list : menuList)
			{
				System.out.println(list.ordinal()+1 + ". " + list.getName() + " : " + list.getExplain());
			}
			
			int answer = InputHandler.readIntRange("변경할 옵션을 선택해주세요.(0~"+menuList.length+") : ", 0,menuList.length);
			
			if(answer == 0)
			{
				break;
			}
			
			answer--;
			
			menuList[answer].setOption(option);
		}
	}
}
