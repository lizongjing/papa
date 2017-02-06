package pagame.bean;

public class GameInfo {
	private String gamename;	//遊戲名稱

	private String gametype;	//遊戲類型，例如射擊類，rpg，冒險類，動作類
	
	private String play_station;//遊戲平台，例如pc，psp，vr，ps4等
	
	private String gamescore;	//遊戲評分
	
	private String gamefromurl;	//遊戲來源

	
	
	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public String getGametype() {
		return gametype;
	}

	public void setGametype(String gametype) {
		this.gametype = gametype;
	}

	public String getPlay_station() {
		return play_station;
	}

	public void setPlay_station(String play_station) {
		this.play_station = play_station;
	}

	public String getGamescore() {
		return gamescore;
	}

	public void setGamescore(String gamescore) {
		this.gamescore = gamescore;
	}

	public String getGamefromurl() {
		return gamefromurl;
	}

	public void setGamefromurl(String gamefromurl) {
		this.gamefromurl = gamefromurl;
	}

	@Override
	public String toString() {
		return gamename + "," + gametype + "," + play_station
				+ "," + gamescore + "," + gamefromurl;
	}
	
}
