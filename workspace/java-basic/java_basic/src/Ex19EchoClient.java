import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Ex19EchoClient {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("서버에 전송할 메시지 : ");
		String message = scanner.nextLine();
		
		
		Socket socket = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		try {
			socket = new Socket("127.0.0.1", 9000); // 1. 소켓 만들기, 2. 연결 ... 여기서 전달인자 연결하려는 서버 IP와 Port
													// 127.0.0.1 == localhost == 현재컴퓨터, 자기자신
			is = socket.getInputStream();	// 데이터 수신 스트림 연결
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			os = socket.getOutputStream();	// 데이터 송신 스트림 연결
			pw = new PrintWriter(os);
			
			pw.println(message);	// 데이터 전송
			pw.flush();	// buffer에 있는 데이터를 강제 전송
			
			String rMessage = br.readLine();
			System.out.println(rMessage);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 4. 연결 닫기
			try { br.close(); } catch (Exception ex) {}
			try { isr.close(); } catch (Exception ex) {}
			try { is.close(); } catch (Exception ex) {}
			try { pw.close(); } catch (Exception ex) {}
			try { os.close(); } catch (Exception ex) {}
			try { socket.close(); } catch (Exception ex) {}
		}
		

	}

}
