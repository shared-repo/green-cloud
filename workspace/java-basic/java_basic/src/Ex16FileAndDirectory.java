import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ex16FileAndDirectory {

	public static void main(String[] args) {
		
		// test();
		
		String path = "C:\\Windows";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		File file = new File(path);	// 지정된 경로의 파일 또는 디렉터리 정보를 관리하는 객체 만들기
		
		File[] filesAndDirs = file.listFiles(); // 지정된 디렉터리에 포함된 모든 파일과 디렉터리 정보를 File 형식의 객체 목록으로 반환
		for (File f : filesAndDirs) {
			long ticks = f.lastModified();	// 1970-01-01 00:00:00 이후 경과된 시간을 1/1000초 단위로 반환
			Date d = new Date(ticks);
			String dateString = sdf.format(d);
			System.out.print(dateString);
			
			if (f.isDirectory()) {
				System.out.printf("%7s %8s", "<DIR>", "");
			} else {
				System.out.printf("%7s %,8d", "", f.length());
			}
			
			System.out.printf(" %s", f.getName());
			
			System.out.println();
			
		}
		

	}
	
	static void test() {
		String path = "C:\\Windows";
		
		File file = new File(path); // 지정된 경로의 파일 또는 디렉터리 정보를 관리하는 객체 만들기
		
		if ( file.isFile() ) {
			System.out.println("파일입니다.");
		}
		if ( file.isDirectory() ) {
			System.out.println("디렉터리입니다.");
		}
		
		System.out.println(new Date(file.lastModified()));
		
		if ( file.canRead() ) {
			System.out.println("읽기 가능");
		}
		if (file.canWrite() ) {
			System.out.println("쓰기 가능");
		}
	}

}
