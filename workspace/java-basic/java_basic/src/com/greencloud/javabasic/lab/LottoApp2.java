package com.greencloud.javabasic.lab;

import java.util.ArrayList;
import java.util.Scanner;

public class LottoApp2 {

	private Scanner scanner = new Scanner(System.in);
	
	private ArrayList<NumberSet> numberSetList = new ArrayList<>();
	
	public void doStart() {
	
		program: while (true) {
		
			String selection = selectTask();

			System.out.println();
			
			switch (selection) {
			case "0": 
				System.out.println("행운을 빕니다.");
				System.out.println("프로그램을 종료합니다.");
				// return;
				break program;
			case "1": 
				// int[] numbers = new int[6];
				NumberSet numberSet = new NumberSet();
				boolean valid = false;
				do {
					int[] numbers = selectBasicNumbers2();// selectBasicNumbers2에서 뽑은 데이터를 return으로 반환한 값 사용
					numberSet.setNumbers(numbers);
					valid = checkAverage(numberSet.getAvg());										
				} while (!valid);
				
				numberSetList.add(numberSet);
				
				System.out.println(numberSet); // numberSet.toString() 자동 호출
				
				break;
			case "2": 
				System.out.println("[ 당첨 예상 번호 목록 ]");
				for (NumberSet nset : numberSetList) {
					System.out.println(nset);
				}
				break;
			default:
				System.out.println("지원하지 않는 명령입니다.");
			}
			
			System.out.println();
		}

	}
	
	public String selectTask() {
		System.out.println("***********************");
		System.out.println("* 0. 종료");
		System.out.println("* 1. 당첨 예상 번호 뽑기");
		System.out.println("* 2. 당첨 예상 번호 목록 보기");
		System.out.println("***********************");		
		System.out.print("작업을 선택하세요 : ");
		String selection = scanner.nextLine();
		
		return selection;
	}
	
	
	public boolean checkAverage(int avg) {
		boolean valid = avg >= 20 && avg <= 26;
		return valid;
	}	
	
	public int[] selectBasicNumbers2() {
		int[] numbers = new int[6];
		for (int i = 0; i < numbers.length; i++) {
			
			numbers[i] = (int)(Math.random() * 45) + 1; // 1 ~ 45, random
			//중복 검사
			for (int j = 0; j < i; j++) {
				if (numbers[i] == numbers[j]) { // 중복인 경우
					//i--;	// 현재 위치에서 다시 뽑기
					i = -1;	// 처음부터 다시 뽑기
					break;
				}
			}
		}
		return numbers;
	}
	
	public static void main(String[] args) {
		
		LottoApp2 lottoApp = new LottoApp2();
		lottoApp.doStart();
		
	}

}























