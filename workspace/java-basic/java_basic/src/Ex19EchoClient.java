import java.net.Socket;

public class Ex19EchoClient {

	public static void main(String[] args) {
		
		Socket socket = null;
		
		try {
			socket = new Socket("127.0.0.1", 9000);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { socket.close(); } catch (Exception ex) {}
		}
		

	}

}
