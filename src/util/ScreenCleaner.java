package util;

/*
 * 화면 정리 유틸리티
 */
public class ScreenCleaner
{
	public static void cleanScreen()
	{
		System.out.print("\033[H\033[2J\033[3J");
		System.out.flush();
	}
}
