package br.com.estatisticasdobrasileirao.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import android.os.Parcel;
import android.os.Parcelable;

public class Classification implements Parcelable {
	
	private int posicao;
	private String teamName;
	private int pontos;
	private int jogos;
	private int vitorias;
	private int empates;
	private int derrotas;
	private String rodadaDate;
	
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	public int getJogos() {
		return jogos;
	}
	public void setJogos(int jogos) {
		this.jogos = jogos;
	}
	public int getVitorias() {
		return vitorias;
	}
	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}
	public int getEmpates() {
		return empates;
	}
	public void setEmpates(int empates) {
		this.empates = empates;
	}
	public int getDerrotas() {
		return derrotas;
	}
	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}
	
	
	public String getRodadaDate() {
		return rodadaDate;
	}
	public void setRodadaDate(String rodadaDate) {
		this.rodadaDate = rodadaDate;
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
	
	
	
	public static final Parcelable.Creator<Classification> CREATOR = new Parcelable.Creator<Classification>() {

		@Override
		public Classification createFromParcel(Parcel source) {
			return new Classification(source);
		}

		@Override
		public Classification[] newArray(int size) {
			return new Classification[size];
		}
	};
	
	
	
	public Classification() {
	}

	public Classification(Parcel source) {
		readFromParcel(source);
	}
	
	@Override
	public int describeContents() {
		return 0;
		
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(posicao);
		dest.writeString(teamName);
		dest.writeInt(pontos);
		dest.writeInt(jogos);
		dest.writeInt(vitorias);
		dest.writeInt(empates);
		dest.writeInt(derrotas);
		dest.writeString(rodadaDate);
	}

	public void readFromParcel(Parcel source) {
		posicao = source.readInt();
		teamName = source.readString();
		pontos = source.readInt();
		jogos = source.readInt();
		vitorias = source.readInt();
		empates = source.readInt();
		derrotas = source.readInt();
		rodadaDate = source.readString();
		
	}
	
}
