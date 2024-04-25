import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex19EchoServer {

	public static void main(String[] args) {
		
		ServerSocket listener = null; // 수신대기 + 클라이언트 연결 + 연결된 소켓 생성
		
		try {
			listener = new ServerSocket(9000, 20); // 1. 소켓 생성, 2. Bind (IP, Port 할당), 3. Listen (backlog 설정)
			
			while (true) { // 종료하지 않고 ( 클라이언트의 연결 대기 + 통신 ) 작업을 무한 반복
				Socket socket = null;
				InputStream is = null;
				InputStreamReader isr = null;
				BufferedReader br = null;
				OutputStream os = null;
				PrintWriter pw = null;
				try {
					socket = listener.accept(); // 4. 클라이언트의 연결 요청 대기
					is = socket.getInputStream();	// 데이터 수신 스트림 연결
					os = socket.getOutputStream();	// 데이터 송신 스트림 연결
					
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					// 6-1. 연결 닫기
					try { socket.close(); } catch (Exception ex) {}
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6-2. 연결 닫기
			try { listener.close(); } catch (Exception ex) {}
		}

	}

}
