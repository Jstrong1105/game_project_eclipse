package domain.playerdata;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.HashMap;

import domain.base.GameResult;
import engine.GameHub;

/*
 * 플레이어별 게임 결과를 담을 객체
 */
public class PlayerData implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, EnumMap<GameHub, GameData>> playerData = new HashMap<>();
	
	private final Path path = Paths.get("C:/playerdata/player.ser");
	
	// 외부에서 데이터를 불러와서 playerData에 담을 메소드
	@SuppressWarnings("unchecked")
	public void load()
	{
		if(!Files.exists(path))
		{
			return;
		}
		
		try
			(
				InputStream isr = Files.newInputStream(path);
				ObjectInputStream ois = new ObjectInputStream(isr)
			)
		{
			playerData = (HashMap<String,EnumMap<GameHub,GameData>>)ois.readObject();
		}
		catch (IOException e) 
		{
			throw new RuntimeException("입력 오류",e);
		}
		catch (ClassNotFoundException e) 
		{
			throw new RuntimeException("클래스 타입이 일치하지 않습니다.",e);
		}
	}
	
	// 외부에 데이터를 저장할 메소드
	public void save()
	{
		try
		{
			if(Files.notExists(path.getParent()))
			{
				Files.createDirectories(path.getParent());
			}
			
			try(OutputStream os = Files.newOutputStream(path);
				ObjectOutputStream oos = new ObjectOutputStream(os))
			{
				oos.writeObject(playerData);
			}
		}
		catch (IOException e) 
		{
			throw new RuntimeException("입력 오류",e);
		}
	}
	
	// 아이디를 입력받아 새로운 레코드 생성 메소드
	public void addPlayer(String id)
	{
		EnumMap<GameHub,GameData> enumMap = new EnumMap<>(GameHub.class);
		
		for(GameHub hub : GameHub.values())
		{
			enumMap.put(hub, new GameData());
		}
		
		playerData.put(id,enumMap);
		
		save();
	}
	
	// 아이디 게임 GameResult를 받아서 결과를 반영하는 메소드
	public void addData(String id, GameHub hub, GameResult result)
	{
		if(playerData.get(id).get(hub) == null)
		{
			playerData.get(id).put(hub, new GameData());
		}
		
		playerData.get(id).get(hub).update(result);
		
		save();
	}
	
	// 해당 아이디가 존재하는지 확인하는 메소드
	public boolean hasId(String id)
	{
		return playerData.containsKey(id);
	}
	
	// 해당 유저의 해당 게임의 데이터 반환하기
	public int[] getData(String id, GameHub hub)
	{
		int[] data = new int[3];
		data[0] = playerData.get(id).get(hub).getTry();
		data[1] = playerData.get(id).get(hub).getClear();
		data[2] = playerData.get(id).get(hub).getTime();
		
		return data;
	}
}
