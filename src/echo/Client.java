package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		//3번 소켓객체를 생성 메모리에 올림
		Socket socket =new Socket();
		
		System.out.println("<클라이언트 시작>");
		System.out.println("============================");
		System.out.println("[서버에 연결을 요청합니다.]");
		//4번connect로 해서 서버컴퓨터에 연결요청
		socket.connect(new InetSocketAddress("192.168.219.160",10001));
		//여기도 서버와 똑같이 InetSocketAddress을 통해서 아이핀 과 포트 번호를 알려줌
		
		System.out.println("[서버에 연결되었습니다.]");
		//socket<-->socket연결 클라이언트의 소캣과 서버의 소켓이 연결이됨
		//메세지 보내기 스트림
		OutputStream os=socket.getOutputStream();
		//내보내는 길 sockte에 작성을 해서 내보내는 메소드가 있다(getOutputStream)
		OutputStreamWriter osw=new OutputStreamWriter(os, "UTF-8");
		//내보낸걸 번역을 한다.UTF-8로 번역을 해서 내보낸다.
		BufferedWriter bw=new BufferedWriter(osw);
		//번역을 한걸 묶어서 보낸다.
		//메세지 받기용 스트림
		InputStream is=socket.getInputStream();
		InputStreamReader isr=new InputStreamReader(is, "UTF-8");
		BufferedReader br=new BufferedReader(isr);
		//스케너
		Scanner sc=new Scanner(System.in);
		
		//반복구간
		while (true) {
			String str=sc.nextLine();//입력시 마다 문자를 넣는다 그래서 반복
			if("/q".equals(str)) {//str이 문자열이 라서 ""를 넣는다.
				//str은 주소가 들어가 있다그러므로 /q과 주소 값을 비교하기 때문에 equals사용한다.
				break;
			}
			//메세지 보내기
			bw.write(str);
			bw.newLine();
			bw.flush();
			//메세지 받기
			String reMsg=br.readLine();
			System.out.println("server:["+reMsg+"]");
		}
		
		//자원종료
		bw.close();
		br.close();
		sc.close();
		System.out.println("===========================");
		System.out.println("<클라이언트 종료>");
		socket.close();
	}

}
