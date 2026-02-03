package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputHandler
{
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	
	
	// 문자를 입력받는 메소드
	public static String readString(String prompt)
	{
		try
		{
			System.out.print(prompt);
			
			while(br.ready())
			{
				br.skip(100);
			}
			
			return br.readLine().trim();
		}
		catch(IOException e)
		{
			throw new RuntimeException("입력오류");
		}
	}
	
	// 숫자를 입력받는 메소드
	public static int readInt(String prompt)
	{
		while(true)
		{
			try
			{
				return Integer.parseInt(readString(prompt));
			}
			catch(NumberFormatException e)
			{
				System.out.println("숫자만 입력해주세요.");
			}
		}
	}
	
	// 숫자를 범위 내에서 입력받는 메소드
	public static int readIntRange(String prompt, int min, int max)
	{
		while(true)
		{
			int num = readInt(prompt + " (" + min + " ~ " + max + ") : ");
			
			if(num < min || num > max)
			{
				System.out.println(min + " ~ " + max + " 사이로 입력해주세요.");
			}
			else
			{
				return num;
			}
		}
	}
}
