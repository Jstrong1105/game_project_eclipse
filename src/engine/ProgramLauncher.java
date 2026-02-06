package engine;

import util.GameSleeper;
import util.InputHandler;
import util.ScreenCleaner;

public class ProgramLauncher
{
	private static final GameHub[] GAMES = GameHub.values();
	private static final int EXIT = 0;
	public static void main(String[] args)
	{
		int option = GAMES.length+1;
		
		System.out.println("로딩 중입니다.");
		
		GameSleeper.sleepGame(2);
		
		while(true)
		{
			System.out.println("====게임실행런처====");
			
			System.out.println(EXIT + ". 종료");
			
			for(GameHub game : GAMES)
			{
				System.out.printf("%d. %s : %s\n",game.ordinal()+1,game.getName(),game.getExplain());
			}
			
			System.out.println(option + ". 옵션 변경");
			
			System.out.println();
			
			int answer = InputHandler.readIntRange("번호를 선택해주세요 : ", EXIT, option);
			
			if(answer == EXIT)
			{
				InputHandler.readString("프로그램을 종료합니다");
				break;
			}
			else if(answer == option)
			{
				System.out.println();
				setOption();
			}
			else if(answer >= 1 && answer < option)
			{
				System.out.println();
				GAMES[answer-1].startGame();
			}
			else
			{
				System.out.println("잘못된 번호입니다.");
			}
		}
		
		InputHandler.closeStream();
	}
	
	private static void setOption()
	{
		while(true)
		{
			System.out.println("====게임옵션 설정====");
			System.out.println(EXIT + ". 뒤로가기");
			
			for(GameHub game : GAMES)
			{
				System.out.printf("%d. %s 옵션\n",game.ordinal()+1,game.getName());
			}
			
			System.out.println();
			
			int answer = InputHandler.readIntRange("옵션을 변경할 게임을 선택해주세요 : ", EXIT, GAMES.length);
			
			if(answer == EXIT)
			{
				InputHandler.readString("런처화면으로 돌아갑니다.");
				ScreenCleaner.cleanScreen();
				break;
			}
			
			else if(answer >= 1 && answer <= GAMES.length)
			{
				GAMES[answer-1].setOption();
			}
			else
			{
				System.out.println("잘못된 번호입니다.");
			}
		}
	}
	
	
}
