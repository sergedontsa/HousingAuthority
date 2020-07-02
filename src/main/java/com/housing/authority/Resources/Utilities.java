package com.housing.authority.Resources;

import com.google.api.client.util.Base64;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Properties;


public class Utilities {


    public static long getNumDays(String start, String end) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy");
        Date date1, date2;
        try {
            date1 = simpleDateFormat.parse(start);
            date2 = simpleDateFormat.parse(end);
            return  (date2.getTime() - date1.getTime())/(1000*60*60*24);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException {
        Properties properties = new Properties();
        Session session = Session.getDefaultInstance(properties, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(from));
        email.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        email.setSubject(subject);
        email.setText(bodyText);
        return email;
    }


}
