package br.com.estatisticasdobrasileirao.entity;

import java.util.ArrayList;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import android.os.Parcel;
import android.os.Parcelable;

public class LotecaResults implements Parcelable {

	private String homeTeam;
	private String awayTeam;
	private String wins;
	private String losses;
	private String draws;
	private String goalsMade;
	private String goalsTaken;
	private ArrayList<Game> winGames;
	private ArrayList<Game> drawGames;
	private ArrayList<Game> lossGames;
	private boolean isSerieB;
	
	
	public LotecaResults() {
	}

	public LotecaResults(String homeTeam, String awayTeam,String wins, String losses, String draws, String goalsMade, String goalsTaken, ArrayList<Game> winGames, ArrayList<Game> drawGames,
			ArrayList<Game> lossGames) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.wins = wins;
		this.losses = losses;
		this.draws = draws;
		this.goalsMade = goalsMade;
		this.goalsTaken = goalsTaken;
		this.winGames = winGames;
		this.drawGames = drawGames;
		this.lossGames = lossGames;
	}
	
	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getWins() {
		return wins;
	}

	public void setWins(String wins) {
		this.wins = wins;
	}

	public String getLosses() {
		return losses;
	}

	public void setLosses(String losses) {
		this.losses = losses;
	}

	public String getDraws() {
		return draws;
	}

	public void setDraws(String draws) {
		this.draws = draws;
	}

	public String getGoalsMade() {
		return goalsMade;
	}

	public void setGoalsMade(String goalsMade) {
		this.goalsMade = goalsMade;
	}

	public String getGoalsTaken() {
		return goalsTaken;
	}

	public void setGoalsTaken(String goalsTaken) {
		this.goalsTaken = goalsTaken;
	}
	
	public ArrayList<Game> getWinGames() {
		return winGames;
	}

	public void setWinGames(ArrayList<Game> winGames) {
		this.winGames = winGames;
	}

	public ArrayList<Game> getDrawGames() {
		return drawGames;
	}

	public void setDrawGames(ArrayList<Game> drawGames) {
		this.drawGames = drawGames;
	}

	public ArrayList<Game> getLossGames() {
		return lossGames;
	}

	public void setLossGames(ArrayList<Game> lossGames) {
		this.lossGames = lossGames;
	}
	
	public boolean isSerieB() {
		return isSerieB;
	}

	public void setSerieB(boolean isSerieB) {
		this.isSerieB = isSerieB;
	}

	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

	public static final Parcelable.Creator<LotecaResults> CREATOR = new Parcelable.Creator<LotecaResults>() {

		@Override
		public LotecaResults createFromParcel(Parcel source) {
			return new LotecaResults(source);
		}

		@Override
		public LotecaResults[] newArray(int size) {
			return new LotecaResults[size];
		}
	};


	public LotecaResults(Parcel source) {
		readFromParcel(source);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(homeTeam);
		dest.writeString(awayTeam);
		dest.writeString(wins);
		dest.writeString(losses);
		dest.writeString(draws);
		dest.writeString(goalsMade);
		dest.writeString(goalsTaken);
		dest.writeList(winGames);
		dest.writeList(drawGames);
		dest.writeList(lossGames);
		dest.writeInt(isSerieB ? 1 : 0);
	}

	public void readFromParcel(Parcel source) {
		homeTeam = source.readString();
		awayTeam = source.readString();
		wins = source.readString();
		losses = source.readString();
		draws = source.readString();
		goalsMade = source.readString();
		goalsTaken = source.readString();
		winGames = new ArrayList<Game>();
		drawGames = new ArrayList<Game>();
		lossGames = new ArrayList<Game>();
		source.readList(winGames, Game.class.getClassLoader());
		source.readList(drawGames, Game.class.getClassLoader());
		source.readList(lossGames, Game.class.getClassLoader());
		isSerieB  = (source.readInt() == 0) ? false : true;
		
	}
			 
}
