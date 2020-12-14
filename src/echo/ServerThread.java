package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	//필드
	private Socket socket;//소캣이라는 필드를 생성한다.
	//생성자
	public ServerThread(Socket socket) {
		this.socket =socket;//필드 소캣안에 생성자 소캣을 넣는다.
		//이제 서버안에 start가 작동을 한다.
	}
	
	//메소드g.s
	//메소드 일반
	@Override
	public void run() {
		try {
			//메시지 받기용 스트림
			InputStream is = socket.getInputStream();
			//문자열을 받는 통로
			InputStreamReader isr=new InputStreamReader(is, "UTF-8");
			//받은 문자열을 번역
			BufferedReader br=new BufferedReader(isr);
			//받은 문자열을 모아서 출력
			//메세지 보내기용 스트림
			OutputStream os=socket.getOutputStream();
			OutputStreamWriter osw=new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw=new BufferedWriter(osw);
			
			//반복구간
			while (true) {
				
				//메세지 받기
				String msg= br.readLine();
				//메세지를 받는 곳이 반복이 되어야 한다.
				if(msg==null) {//들어오는 것이 아무것도 없다면
					break;
				}
				System.out.println("받은 메시지:"+msg);
				//메세지를 출력하는 구간도 반복이 되어야 한다.
				//메세지 보내기
				bw.write(msg);
				bw.newLine();
				bw.flush();
			}
			//자원종료 자원을 종료하기 때문에 반복문 안에 못들어감
			bw.close();
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
}
