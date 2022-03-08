package com.hf.hc;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Email")
public class Email extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String memberId = request.getParameter("memberId");
	        String email = request.getParameter("email");
	        System.out.println(email);
	        //���� ���̵�� ȸ�������� �޾ƿ��� ������ �����Ϳ��� email���� ���Ͽ� �������� ������ �������� ������ ����
	        Account m = new MemberService().memberLogin(memberId);
	        if(m == null || !m.getM_email().equals(email))
	        {
	            request.setAttribute("msg", "���̵� �̸��� ������ ���� �ʽ��ϴ�");
	            request.setAttribute("loc", "/member/searchPw");
	            request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	            return;
	        }
	        
	                //mail server ����
	                String host = "smtp.naver.com";
	                String user = "ilovedar@naver.com"; //�ڽ��� ���̹� ����
	                String password = "tmddus8425!";//�ڽ��� ���̹� �н�����
	                
	                //���� ���� �ּ�
	                String to_email = m.getM_email();
	                
	                //SMTP ���� ������ �����Ѵ�.
	                Properties props = new Properties();
	                props.put("mail.smtp.host", host);
	                props.put("mail.smtp.port", 465);
	                props.put("mail.smtp.auth", "true");
	                props.put("mail.smtp.ssl.enable", "true");
	                
	                //���� ��ȣ ������
	                StringBuffer temp =new StringBuffer();
	                Random rnd = new Random();
	                for(int i=0;i<10;i++)
	                {
	                    int rIndex = rnd.nextInt(3);
	                    switch (rIndex) {
	                    case 0:
	                        // a-z
	                        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
	                        break;
	                    case 1:
	                        // A-Z
	                        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
	                        break;
	                    case 2:
	                        // 0-9
	                        temp.append((rnd.nextInt(10)));
	                        break;
	                    }
	                }
	                String AuthenticationKey = temp.toString();
	                System.out.println(AuthenticationKey);
	                
	                Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(user,password);
	                    }
	                });
	                
	                //email ����
	                try {
	                    MimeMessage msg = new MimeMessage(session);
	                    msg.setFrom(new InternetAddress(user, "KH Books"));
	                    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
	                    
	                    //���� ����
	                    msg.setSubject("�ȳ��ϼ��� KH BOOKS ���� �����Դϴ�.");
	                    //���� ����
	                    msg.setText("���� ��ȣ�� :"+temp);
	                    
	                    Transport.send(msg);
	                    System.out.println("�̸��� ����");
	                    
	                }catch (Exception e) {
	                    e.printStackTrace();// TODO: handle exception
	                }
	                HttpSession saveKey = request.getSession();
	                saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
	                //�н����� �ٲܶ� �� �ٲ��� ���ǿ� ���� id
	                request.setAttribute("id", memberId);
	                request.getRequestDispatcher("/views/login_myPage/searchPasswordEnd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
