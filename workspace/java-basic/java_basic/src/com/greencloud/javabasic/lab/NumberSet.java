package com.greencloud.javabasic.lab;

import java.io.Serializable;
import java.util.Date;

public class NumberSet implements Serializable {
	
	private int round;			// 회차
	private int[] numbers;		// 당첨번호(예상)
	private int bonusNumber;	// 보너스 번호
	private int avg;			// 평균
	private Date gameDate;		// 추첨일
	
	private boolean predict = true;	// 현재 번호세트가 과거 당첨번호인지 아니면 당첨예상번호인지 구분하는 변수
	
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int[] getNumbers() {
		return numbers;
	}
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
		this.avg = calculateAvg();
	}
	public int getBonusNumber() {
		return bonusNumber;
	}
	public void setBonusNumber(int bonusNumber) {
		this.bonusNumber = bonusNumber;
	}
	public int getAvg() {
		return avg;
	}
//	public void setAvg(int avg) {
//		this.avg = avg;
//	}
	public Date getGameDate() {
		return gameDate;
	}
	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}
	
	public boolean isPredict() {
		return predict;
	}
	public void setPredict(boolean predict) {
		this.predict = predict;
	}
	public int calculateAvg() {
		int sum = 0;
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		int avg = sum / numbers.length;
		return avg;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(128); // 문자열의 결합 연산이 필요한 경우 사용하는 클래스
		if (!predict) {
			sb.append(String.format("[ROUND %4d] : ", round));
		}
		
		for (int number : numbers) {
			sb.append(String.format("[%2d]", number));
		}
		sb.append(String.format("[AVG:%2d]", avg));
		
		if (!predict) {
			sb.append(String.format("[BONUS:%2d]", bonusNumber));		
			sb.append(String.format("[%s]", gameDate));
		}
		
		return sb.toString();
		
	}
	
	

}














