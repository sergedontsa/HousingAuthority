package com.housing.authority.Controllers;

import com.housing.authority.Resources.Constant;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 *
 */
@RestController
@RequestMapping(value = Constant.HOUSING_CONTROLLER)
@RequiredArgsConstructor
public class HousingController {


    @CrossOrigin
    @GetMapping(value = "/email")
    public static void sendEmail(){
        //need to fix the domaine email
        Email from = new Email("dontsa00serge@gmail.com");
        String subject = "Sending with Twilio SendGrid is fun";
        Email to = new Email("dontsaserge@gmail.com");
        Content content = new Content("text/plain", "and easy to do anywhere even with java");
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sendGrid = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println("+ "+response.getStatusCode());
            System.out.println("+ "+response.getBody());
            System.out.println("+ "+response.getHeaders());

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @CrossOrigin
    @GetMapping("bill")
    //@Scheduled(fixedRate = 9000)
    public static void bill() throws UnirestException {
        System.out.println("In this method we will sent the bill everyday after 6 pm");
        HttpResponse<JsonNode> httpResponse = Unirest.get("https://jsonplaceholder.typicode.com/posts")
                .asJson();

        JSONObject jsonObject = httpResponse.getBody().getObject();
        System.out.println("Result: " + jsonObject.toString());
    }





}
