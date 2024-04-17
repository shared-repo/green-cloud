package com.greencloud;

import com.greencloud.javabasic.MyBase;

public class MyClass2 extends MyBase {
	
	void m() {
		// super.defaultMember = 100;	// 오류 : 다른 패키지의 부모 클래스의 default member는 사용 불가능 ( default는 같은 패키지에서만 접근 가능 )
		super.protectedMember = 100;	// protected 멤버는 상속 받은 클래스에서 사용 가능
	}

}
