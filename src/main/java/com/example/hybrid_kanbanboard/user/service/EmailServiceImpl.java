package com.example.hybrid_kanbanboard.user.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private int authNumber;

    private final JavaMailSender mailSender;

    public void makeRandomNumber() {
        // 난수의 범위 111111 ~ 999999 (6자리 난수)
        Random r = new Random();
        int checkNum = r.nextInt(888888) + 111111;
        System.out.println("인증번호 : " + checkNum);
        authNumber = checkNum; // checkNum이 authNumber에 담김
    }

    // 이메일 보낼 양식
    @Override
    public String joinEmail(String email) {
        makeRandomNumber(); // 이메일 인증 코드 만들기
        String toMail = email; // 누구에게 보낼지?
        String title = "회원 가입 인증 이메일 입니다."; // 이메일 제목, postman : ?email=thddltkr10@naver.com
        String content =
                "홈페이지를 방문해주셔서 감사합니다." + 	//html 형식으로 작성
                        "<br><br>" +
                        "인증 번호는 " + authNumber + "입니다." +
                        "<br>" +
                        " "; //이메일 내용 삽입
        sendEmail(toMail, title, content);
        return Integer.toString(authNumber);
    }

    // 이메일 전송 메서드
    public void sendEmail (String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        // true 매개 값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setTo(toMail);
            helper.setSubject(title);
            // true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
            helper.setText(content,true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
