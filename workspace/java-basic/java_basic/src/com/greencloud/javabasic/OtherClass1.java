package com.greencloud.javabasic;

// public class OtherClass3 { // 오류 : public 클래스의 이름은 파일이름과 일치해야 합니다. 
public class OtherClass1 {
	
	// 같은 패키지의 public이 아닌 클래스 사용 가능
	OtherClass2 oc2 = new OtherClass2();
	
	public int x = 10;		// public : 모든 곳에서 접근 가능
	private int y = 10;		// private : 같은 클래스에서 접근 가능
	int z = 10;				// default : 같은 패키지에서 접근 가능

}

class OtherClass3 { // public이 아닌 클래스의 이름은 파일 이름과 다를 수 있습니다.	
}
