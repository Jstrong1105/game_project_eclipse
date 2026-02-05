package util;

public class GameSleeper
{
	// 입력받은 시간만큼 정지 시키면서 남은 시간을 출력하는 메소드
	public static void sleepGame(int seconds)
	{
		try
		{
			for (int i = seconds; i > 0; i--)
			{
				System.out.printf("\r%d초 남았습니다.",i);
				Thread.sleep(1000);
			}
			
			System.out.printf("\r0초 남았습니다.\n");
			Thread.sleep(500);
			System.out.println();
		}
		catch (InterruptedException e) {
			System.out.println(e.toString());
		}
	}
}
