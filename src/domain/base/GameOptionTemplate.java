package domain.base;


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
