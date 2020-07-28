package com.housing.authority.Controllers.Messaging;

import com.housing.authority.Resources.Constant;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EmailController {

    @GetMapping("/email")
    public void sendEmail(){
        Email from = new Email("dontsa00serge@gmail.com");
        String subject = "Sending the first email";
        Email to = new Email("dontsaserge@gmail.com");
        Content content = new Content("text/plain", "and easy to do anywhere, even with java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sendGrid = new SendGrid(System.getenv(Constant.emailApi));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
