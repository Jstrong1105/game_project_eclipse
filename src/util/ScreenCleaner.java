package util;

/*
 * 화면 정리 유틸리티
 */
public final class ScreenCleaner
{
	private ScreenCleaner()
	{
		
	}
	
	public static void cleanScreen()
	{
		// 033[H 커서를 왼쪽 상단 구석으로 이동
		// 033[2J 화면 지우기
		// 033[3J 남아있는 버퍼 비우기
		System.out.print("\033[H\033[2J\033[3J");
		System.out.flush();
	}
}
