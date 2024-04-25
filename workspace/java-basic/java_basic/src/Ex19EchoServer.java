import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex19EchoServer {

	public static void main(String[] args) {
		
		System.out.println("[ 서버 프로그램을 시작합니다 ]");
		
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
					// System.out.println("클라이언트가 연결되었습니다.");
					
					is = socket.getInputStream();	// 데이터 수신 스트림 연결
					isr = new InputStreamReader(is);
					br = new BufferedReader(isr);
					os = socket.getOutputStream();	// 데이터 송신 스트림 연결
					pw = new PrintWriter(os);
					
					String rMessage = br.readLine();	// 데이터 수신
					System.out.printf("[%s] : %s\n", socket.getRemoteSocketAddress(), rMessage);
					
					pw.printf("[서버에서 보낸 메시지] : %s\n", rMessage);
					pw.flush();
					
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					// 6-1. 연결 닫기
					try { br.close(); } catch (Exception ex) {}
					try { isr.close(); } catch (Exception ex) {}
					try { is.close(); } catch (Exception ex) {}
					try { pw.close(); } catch (Exception ex) {}
					try { os.close(); } catch (Exception ex) {}
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
