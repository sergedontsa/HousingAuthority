package com.housing.authority;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class myDemo {
    public static void main(String[] args) {
        int i = 0;
        while (i < 10){
            String password = "1234";
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String hashPassword = bCryptPasswordEncoder.encode(password);
            boolean dhashPawword = bCryptPasswordEncoder.matches(password, hashPassword);



            System.out.println(hashPassword+" "+dhashPawword);
            i++;
        }
    }
}
