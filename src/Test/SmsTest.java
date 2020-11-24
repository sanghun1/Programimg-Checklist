package Test;

import java.util.HashMap;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsTest extends JFrame implements ActionListener{
	JButton bt1, bt2;
	JLabel la1, la2;
	JTextField tf1, tf2;
	Container c;
	GridLayout grid;
	
	
	
	public SmsTest() {
		setTitle("문자보내기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 250);
		
		grid = new GridLayout(3, 2);
		c = getContentPane();
		c.setLayout(grid);
		
		la1 = new JLabel("전화번호");
		la2 = new JLabel("메시지");
		
		bt1 = new JButton("전송");
		bt2 = new JButton("초기화");
		
		tf1 = new JTextField("");
		tf2 = new JTextField("");
		
		c.add(la1);
		c.add(tf1);
		c.add(la2);
		c.add(tf2);
		c.add(bt1);
		c.add(bt2);

		bt1.addActionListener(this);
		
		bt2.addActionListener(this);
		
		setVisible(true);
	}
	
	static void 문자전송(String s1, String s2) {
		 String api_key = "NCSAPRMSHJ0PSXOX";
		    String api_secret = "BMCQ2HDVFBMR4MF6MLDSWZYPLYUKINZC";
		    Message coolsms = new Message(api_key, api_secret);

		    // 4 params(to, from, type, text) are mandatory. must be filled
		    HashMap<String, String> params = new HashMap<String, String>();
		    params.put("to", s1);
		    params.put("from", "01040367935");
		    params.put("type", "SMS");
		    params.put("text", s2);
		    params.put("app_version", "test app 1.2"); // application name and version
		    try {
		      JSONObject obj = (JSONObject) coolsms.send(params);
		      System.out.println("메시지가 전송되었습니다.");
		      System.out.println(obj.toString());
		      // 1번 obj(문자열) -> 자바 오브젝트
		      // 2번 error가 있는지 확인
		      // 3번 DB insert
		    } catch (CoolsmsException e) {
		      System.out.println(e.getMessage());
		      System.out.println(e.getCode());
		    }
	}
	

	public static void main(String[] args) {
		new SmsTest();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton)e.getSource();
		
		String s1 = tf1.getText();
		String s2 = tf2.getText();
		
		if((bt.getText()).equals("전송")){
			문자전송(s1, s2);
			System.out.println("전화번호 : " + s1);
			System.out.println("메시지 내용 : " + s2);
		}
		else if((bt.getText()).equals("초기화")){
			tf1.setText("");
			tf2.setText("");
		}
		
	}

}
