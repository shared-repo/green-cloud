package com.greencloud.javabasic;

// public class OtherClass2 {
class OtherClass2 { // default 접근성 클래스는 다른 패키지에서 사용 불가능

	void m() {
		OtherClass1 oc1 = new OtherClass1();
		oc1.x = 20; // public 멤버 접근 가능
		// oc1.y = 20; // private 멤법 접근 불가능
		oc1.z = 20; // default 멤버 접근 가능 (같은 패키지 이므로)
		
		///////////////////////////////
		
		MyBase mb = new MyBase();
		mb.defaultMember = 100;
		mb.protectedMember = 100;
	}
	
}
