package com.greencloud.javabasic.lab;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class LottoApp2 {

	private Scanner scanner = new Scanner(System.in);
	
	private ArrayList<NumberSet> numberSetList = new ArrayList<>();		// 당첨 예상 번호 목록을 저장할 리스트
	private ArrayList<NumberSet> winningNumbers = new ArrayList<>();	// 과거 당첨번호 목록을 저장할 리스트
	
	public LottoApp2() {
		FileInputStream fis = null;		// 파일에 대한 byte[] 입출력
		InputStreamReader isr = null;	// String <-> byte[] 변환
		BufferedReader br = null;		// 한 줄씩 읽는 기능 제공
		try {
			fis = new FileInputStream("lotto-winning-numbers.txt");
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");	// 특정 형식의 문자열 -> 날짜 or 날짜 -> 특정 형식의 문자열
			
			while (true) {
				String line = br.readLine();	// 텍스트 파일에서 한 줄 읽기
				if (line == null) {				// EOF
					break;
				}
				
				String[] data = line.split(","); // split : "abc-efg-xy-tac".split("-") --> ["abc", "efg", "xy", "tac"]
				NumberSet ns = new NumberSet();
				// 회차 번호 읽어서 저장
				ns.setRound( Integer.parseInt(data[0]) ); // parseInt : "123" --> 123
				// 당첨 번호 6개 숫자 읽어서 저장
				int[] numbers = new int[6];
				for (int i = 2; i < 8; i++) {
					numbers[i-2] = Integer.parseInt(data[i]);					
				}
				ns.setNumbers(numbers);
				// 보너스 번호 읽어서 저장
				ns.setBonusNumber( Integer.parseInt(data[8]) );
				// 당첨 번호 종류 저장 ( 예측 번호 or 과거 추첨 번호 )
				ns.setPredict(false);
				// 추첨 일자 읽어서 저장
				ns.setGameDate( sdf.parse(data[1]) ); // sdf.parse : 특정 형식 문자열 -> Date 객체로 변환
				
				winningNumbers.add(ns);
				
				// System.out.println(line);
			}			
		} catch (IOException ex) {			
		} catch (ParseException ex) {			
		} finally {
			try { br.close(); } catch (Exception ex) {}
			try { isr.close(); } catch (Exception ex) {}
			try { fis.close(); } catch (Exception ex) {}
		}
	}
	
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
			case "3":
				// exportNumbersAs\Binary(); // numberSetList (ArrayList)를 Binary 형식으로 데이터 저장				
				exportNumbersAsText(); 		// numberSetList (ArrayList)를 문자열 형식으로 저장	
				break;				
			case "4":
				System.out.print("회차 : ");
				int round = scanner.nextInt();
				scanner.nextLine();	// nextInt() 호출 후에 buffer에 남아있는 enter 제거
				boolean found = false;
				for (NumberSet ns : winningNumbers) {
					if (ns.getRound() == round) {
						System.out.println(ns);
						found = true;
						break; // 회차번호는 고유하기 때문에 한 번 발견된 회차는 다시 나오지 않으므로 중단 처리
					}
				}
				if (!found) {
					System.out.println("입력한 회차의 추첨 정보가 없습니다.");
				}
				break;
			default:
				System.out.println("지원하지 않는 명령입니다.");
			}
			
			System.out.println();
		}

	}
	
	private String selectTask() {
		System.out.println("***********************");
		System.out.println("* 0. 종료");
		System.out.println("* 1. 당첨 예상 번호 뽑기");
		System.out.println("* 2. 당첨 예상 번호 목록 보기");
		System.out.println("* 3. 당첨 예상 번호 내보내기");
		System.out.println("* 4. 당첨 번호 검색");
		System.out.println("***********************");		
		System.out.print("작업을 선택하세요 : ");
		String selection = scanner.nextLine();
		
		return selection;
	}
	
	
	private boolean checkAverage(int avg) {
		boolean valid = avg >= 20 && avg <= 26;
		return valid;
	}	
	
	private int[] selectBasicNumbers2() {
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
	
	private void exportNumbersAsBinary() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); // Date 객체의 정보를 지정한 형식의 문자열로 변환하는 객체
		String dateString = sdf.format(new Date());
		String fileName = String.format("%s-golden-numbers.dat", dateString);
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(numberSetList);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 닫는 순서는 여는 순서의 역순으로 진행 
			try { oos.close(); } catch (Exception ex) {}
			try { fos.close(); } catch (Exception ex) {}
		}
	}
	
	private void exportNumbersAsText() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); // Date 객체의 정보를 지정한 형식의 문자열로 변환하는 객체
		String dateString = sdf.format(new Date());
		String fileName = String.format("%s-golden-numbers.txt", dateString);
		
		FileOutputStream fos = null;
//		OutputStreamWriter osw = null;		// 문자열 출력 기본 기능 제공하는 객체
		PrintStream ps = null;				// 문자열 출력 확장 기능 제공하는 객체 ( System.out 객체와 같은 객체 )
		try {					
			fos = new FileOutputStream(fileName);
//			osw = new OutputStreamWriter(fos);	
			ps = new PrintStream(fos);			
			for (NumberSet ns : numberSetList) {
//				osw.write(ns.toString());
//				osw.write(System.lineSeparator()); // 운영체제에 따라 적절한 줄바꿈 처리 : windows --> \r\n, linux --> \n
				ps.println(ns);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {					
//			try { osw.close(); } catch (Exception ex) {}
			try { ps.close(); } catch (Exception ex) {}
			try { fos.close(); } catch (Exception ex) {}
		}			
	}
	
	public static void main(String[] args) {
		
		LottoApp2 lottoApp = new LottoApp2();
		lottoApp.doStart();
		
	}

}























