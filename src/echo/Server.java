package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		//서버소캐생성
		ServerSocket serverSocket=new ServerSocket();
		//바인드
		serverSocket.bind(new InetSocketAddress("192.168.219.160",10001));
		System.out.println("<서버시작>");
		System.out.println("===============================");
		System.out.println("[연결을 기다리고 있습니다.]");
		
		while (true) {//출장을 보내는게 언재끝날줄 모름 탈출조건이 없어 끝나지 않음
			//accept 소캣을 만든다.
			Socket socket = serverSocket.accept();
			System.out.println("[클라이언트가 연결 되었습니다.]");
			Thread thread =new ServerThread(socket);
			//이제 스래드를 만들어서 일을독립적으로 하게 된다.서버스래드 안에 소캣 주소를 넣었다.
			thread.start();
			//출장보내기.start();

		}
		/* 지금은 반복문이 끝이 나지 않기 때문에 서버를 종료할수가 없다.
		System.out.println("===========================");
		System.out.println("<서버종료>");
		serverSocket.close();
		*/
	}

}
