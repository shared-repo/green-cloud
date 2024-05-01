
// market_db의 lotto_winning_number 테이블의 데이터를 저장할 DTO 클래스

import java.util.Date;

public class LottoWinningNumber {
	
	private int rnd;
	private Date gameDate;
	private int number1;
	private int number2;
	private int number3;
	private int number4;
	private int number5;
	private int number6;
	private int bonusNumber;
	
	public int getRnd() {
		return rnd;
	}
	public void setRnd(int rnd) {
		this.rnd = rnd;
	}
	public Date getGameDate() {
		return gameDate;
	}
	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}
	public int getNumber1() {
		return number1;
	}
	public void setNumber1(int number1) {
		this.number1 = number1;
	}
	public int getNumber2() {
		return number2;
	}
	public void setNumber2(int number2) {
		this.number2 = number2;
	}
	public int getNumber3() {
		return number3;
	}
	public void setNumber3(int number3) {
		this.number3 = number3;
	}
	public int getNumber4() {
		return number4;
	}
	public void setNumber4(int number4) {
		this.number4 = number4;
	}
	public int getNumber5() {
		return number5;
	}
	public void setNumber5(int number5) {
		this.number5 = number5;
	}
	public int getNumber6() {
		return number6;
	}
	public void setNumber6(int number6) {
		this.number6 = number6;
	}
	public int getBonusNumber() {
		return bonusNumber;
	}
	public void setBonusNumber(int bonusNumber) {
		this.bonusNumber = bonusNumber;
	}
	
	@Override
	public String toString() {
		return String.format("[ROUND:%4d][%s][%2d][%2d][%2d][%2d][%2d][%2d][BONUS:%2d]", 
				rnd, gameDate, number1, number2, number3, number4, number5, number6, bonusNumber);
	}

}
















