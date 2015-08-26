package br.com.estatisticasdobrasileirao.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {

	private String name;
	private String symbolResource;
	private Integer favorite;
	private Integer symbolId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbolResource() {
		return symbolResource;
	}

	public void setSymbolResource(String symbolResource) {
		this.symbolResource = symbolResource;
	}

	public Integer getFavorite() {
		return favorite;
	}

	public void setFavorite(Integer favorite) {
		this.favorite = favorite;
	}

	public Integer getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(Integer symbolId) {
		this.symbolId = symbolId;
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
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
	public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {

		@Override
		public Team createFromParcel(Parcel source) {
			return new Team(source);
		}

		@Override
		public Team[] newArray(int size) {
			return new Team[size];
		}
	};

	public Team() {
	}

	public Team(Parcel source) {
		readFromParcel(source);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(symbolResource);
		dest.writeInt(favorite);
		dest.writeInt(symbolId);
	}

	public void readFromParcel(Parcel source) {
		name = source.readString();
		symbolResource = source.readString();
		favorite = source.readInt();
		symbolId = source.readInt();
	}

}
