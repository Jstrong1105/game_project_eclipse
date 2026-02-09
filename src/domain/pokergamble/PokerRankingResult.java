package domain.pokergamble;

import java.util.ArrayList;
import java.util.List;

/*
 * 족보 결과를 담을 객체
 */
class PokerRankingResult implements Comparable<PokerRankingResult>
{
	private PokerRankingList ranking;
	private List<Integer> kicker;
	
	// 족보만 받아서 생성함
	PokerRankingResult(PokerRankingList ranking)
	{
		this.ranking = ranking;
		kicker = new ArrayList<Integer>();
	}
	
	// 키커 추가
	void addKicker(int kicker)
	{
		this.kicker.add(kicker);
	}
	
	// 이름 반환하기
	String getName()
	{
		return ranking.getName();
	}

	@Override
	public int compareTo(PokerRankingResult o)
	{
		int num =  this.ranking.getPower() - o.ranking.getPower();
		
		if(num != 0)
		{
			return num;
		}
		
		for(int i = 0; i < Math.min(this.kicker.size(),o.kicker.size()); i++)
		{
			int number = this.kicker.get(i) - o.kicker.get(i);
			
			if(number != 0)
			{
				return number;
			}
		}
		
		return 0;
	}
}















