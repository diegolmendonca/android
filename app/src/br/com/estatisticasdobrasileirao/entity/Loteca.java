package br.com.estatisticasdobrasileirao.entity;

import java.util.ArrayList;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import android.os.Parcel;
import android.os.Parcelable;

public class Loteca implements Parcelable {

	private int sorteioNumber;
	private String sorteioDate;
	private ArrayList<LotecaResults> summaryPerTeam;
	
	
	public Loteca() {
	}

	public Loteca(int sorteioNumber, String sorteioDate, ArrayList<LotecaResults> summaryPerTeam) {
		this.sorteioNumber = sorteioNumber;
		this.sorteioDate = sorteioDate;
		this.summaryPerTeam = summaryPerTeam;
		
	}
	
	public int getSorteioNumber() {
		return sorteioNumber;
	}

	public void setSorteioNumber(int sorteioNumber) {
		this.sorteioNumber = sorteioNumber;
	}

	public String getSorteioDate() {
		return sorteioDate;
	}

	public void setSorteioDate(String sorteioDate) {
		this.sorteioDate = sorteioDate;
	}

	public ArrayList<LotecaResults> getLotecaResults() {
		return summaryPerTeam;
	}

	public void setLotecaResults(ArrayList<LotecaResults> summaryPerTeam) {
		this.summaryPerTeam = summaryPerTeam;
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

	public static final Parcelable.Creator<Loteca> CREATOR = new Parcelable.Creator<Loteca>() {

		@Override
		public Loteca createFromParcel(Parcel source) {
			return new Loteca(source);
		}

		@Override
		public Loteca[] newArray(int size) {
			return new Loteca[size];
		}
	};


	public Loteca(Parcel source) {
		readFromParcel(source);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(sorteioNumber);
		dest.writeString(sorteioDate);
		dest.writeList(summaryPerTeam);
	}

	public void readFromParcel(Parcel source) {
		sorteioNumber = source.readInt();
		sorteioDate = source.readString();
		summaryPerTeam = new ArrayList<LotecaResults>();
	}
			 
}
