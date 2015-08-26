package br.com.estatisticasdobrasileirao.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {
	
	private int id;
	private int year;
	private String homeTeam;
	private String homeResult;
	private String awayTeam;
	private String awayResult;
	private String gameYear;
	private String gameDate;
	private String gameDay;
	private int homeSymbolId;
	private int awaySymbolId;
	
	
	public Game(String homeResult, String awayResult, int year) {
		this.homeResult = homeResult;
		this.awayResult = awayResult;
		this.year = year;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHomeResult() {
		return homeResult;
	}
	public void setHomeResult(String homeResult) {
		this.homeResult = homeResult;
	}
	public String getAwayResult() {
		return awayResult;
	}
	public void setAwayResult(String awayResult) {
		this.awayResult = awayResult;
	}
	public String getGameYear() {
		return gameYear;
	}
	public void setGameYear(String gameYear) {
		this.gameYear = gameYear;
	}
	public int getHomeSymbolId() {
		return homeSymbolId;
	}
	public void setHomeSymbolId(int homeSymbolId) {
		this.homeSymbolId = homeSymbolId;
	}
	public int getAwaySymbolId() {
		return awaySymbolId;
	}
	public void setAwaySymbolId(int awaySymbolId) {
		this.awaySymbolId = awaySymbolId;
	}
	
	
	public String getGameDate() {
		return gameDate;
	}
	
	public void setGameDate(String gameDate) {
		this.gameDate = gameDate;
	}
	
	public String getGameDay() {
		return gameDay;
	}
	
	public void setGameDay(String gameDay) {
		this.gameDay = gameDay;
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
	
	public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {

		@Override
		public Game createFromParcel(Parcel source) {
			return new Game(source);
		}

		@Override
		public Game[] newArray(int size) {
			return new Game[size];
		}
	};

	public Game() {
	}

	public Game(Parcel source) {
		readFromParcel(source);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(year);
		dest.writeString(homeTeam);
		dest.writeString(homeResult);
		dest.writeString(awayTeam);
		dest.writeString(awayResult);
		dest.writeString(gameYear);
		dest.writeString(gameDate);
		dest.writeString(gameDay);
		dest.writeInt(homeSymbolId);
		dest.writeInt(awaySymbolId);
	}

	public void readFromParcel(Parcel source) {
		id = source.readInt();
		year = source.readInt();
		homeTeam = source.readString();
		homeResult = source.readString();
		awayTeam = source.readString();
		awayResult = source.readString();
		gameYear = source.readString();
		gameDate = source.readString();
		gameDay = source.readString();
		homeSymbolId = source.readInt();
		awaySymbolId = source.readInt();
		
		
	}
	
}
