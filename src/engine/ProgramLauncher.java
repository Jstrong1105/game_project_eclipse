package engine;

import domain.base.GameLauncher;
import domain.playerdata.PlayerData;
import util.GameSleeper;
import util.InputHandler;
import util.ScreenCleaner;

public class ProgramLauncher
{
	private static final GameHub[] GAMES = GameHub.values();
	private static final int EXIT = 0;
	
	public static final PlayerData playerData = new PlayerData();
	
	public static String playerId;
	
	public static void main(String[] args)
	{
		int option = GAMES.length+1;
		
		playerData.load();
		
		System.out.println(" 로딩 중입니다.");
		
		GameSleeper.sleepGame(2);
		
		GameLauncher launcher;
		
		boolean run = login();
		
		while(run)
		{
			ScreenCleaner.cleanScreen();
			
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
				run = false;
			}
			else if(answer == option)
			{
				System.out.println();
				setOption();
			}
			else if(answer >= 1 && answer < option)
			{
				System.out.println();
				launcher = GAMES[answer-1].getGame();
				launcher.run();
			}
			else
			{
				System.out.println("잘못된 번호입니다.");
			}
		}
		
		InputHandler.closeStream();
	}
	
	private static boolean login()
	{
		while(true)
		{
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 종료");
			
			int answer = InputHandler.readIntRange("번호를 선택해주세요 : ", 1, 3);
			
			if(answer == 3)
			{
				return false;
			}
			else if(answer == 1)
			{
				playerId = InputHandler.readString("아이디를 입력해주세요 : ");
				
				if(playerData.hasId(playerId))
				{
					System.out.println("로그인 성공");
					return true;
				}
				else
				{
					System.out.println("존재하지 않는 아이디입니다.");
				}
			}
			else
			{
				playerId = InputHandler.readString("가입할 아이디를 입력해주세요 : ");
				if(playerData.hasId(playerId))
				{
					System.out.println("이미 존재하는 계정입니다.");
				}
				else
				{
					playerData.addPlayer(playerId);
					System.out.println("회원 가입 완료");
				}
			}
		}
	}
	
	private static void setOption()
	{
		while(true)
		{
			ScreenCleaner.cleanScreen();
			
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
