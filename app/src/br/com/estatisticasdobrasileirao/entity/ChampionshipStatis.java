package br.com.estatisticasdobrasileirao.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import android.os.Parcel;
import android.os.Parcelable;

public class ChampionshipStatis implements Parcelable {
	private Long id;
	private Long totalGame;
	private String team;
	private Long size;
	private Integer homeResult;
	private Integer awayResult;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTotalGame() {
		return totalGame;
	}
	public void setTotalGame(Long totalGame) {
		this.totalGame = totalGame;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	
	public Integer getHomeResult() {
		return homeResult;
	}
	public void setHomeResult(Integer homeResult) {
		this.homeResult = homeResult;
	}
	public Integer getAwayResult() {
		return awayResult;
	}
	public void setAwayResult(Integer awayResult) {
		this.awayResult = awayResult;
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
	
	
	public static final Parcelable.Creator<ChampionshipStatis> CREATOR = new Parcelable.Creator<ChampionshipStatis>() {

		@Override
		public ChampionshipStatis createFromParcel(Parcel source) {
			return new ChampionshipStatis(source);
		}

		@Override
		public ChampionshipStatis[] newArray(int size) {
			return new ChampionshipStatis[size];
		}
	};
	
	
	
	public ChampionshipStatis() {
	}

	public ChampionshipStatis(Parcel source) {
		readFromParcel(source);
	}
	
	@Override
	public int describeContents() {
		return 0;
		
	}

	
	
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeLong(totalGame);
		dest.writeString(team);
		dest.writeLong(size);
		dest.writeInt(homeResult);
		dest.writeInt(awayResult);
	}

	public void readFromParcel(Parcel source) {
		id = source.readLong();
		totalGame = source.readLong();
		team = source.readString();
		size = source.readLong();
		homeResult = source.readInt();
		awayResult = source.readInt();
		
	}
	
	
	
	
}
