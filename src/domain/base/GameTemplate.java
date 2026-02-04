package domain.base;

import util.InputHandler;

/*
 * 모든 게임이 구현해야할 메소드와 
 * 공통적인 부분
 * 실행 흐름을 정의한 추상 클래스
 */
public abstract class GameTemplate implements GameLauncher
{
	@Override
	public void run()
	{
		do
		{
			run = true;
			initialize();
			
			while(run)
			{
				render();
				input();
				update();
			}
			
		} while (restart());
		
		InputHandler.readString("게임을 종료합니다.");
	}
	
	// 실행 흐름
	private boolean run;
	
	// 다시 시작
	private boolean restart()
	{
		return InputHandler.readBoolean("다시 시작하시겠습니까? (Y/N) : " , "Y", "N");
	}
	
	// 실행 종료
	protected void endGame()
	{
		run = false;
	}
	
	// 초기화
	protected abstract void initialize();
	
	// 화면 출력
	protected abstract void render();
	
	// 사용자 입력
	protected abstract void input();
	
	// 상태 업데이트
	protected abstract void update();
	
	// 게임 종료
	protected abstract void finish(GameResult result);
	
}
