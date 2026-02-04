package domain.base;

/*
 * 모든 게임 옵션 세터가 정의해야하는 메소드를 정의한 인터페이스
 */

public interface GameOptionTemplate<T>
{
	
	String getName();
	String getExplain();
	void setOption(T option);
	
	@FunctionalInterface
	interface OptionSetter<T>
	{
		void setOption(T option);
	}
}
