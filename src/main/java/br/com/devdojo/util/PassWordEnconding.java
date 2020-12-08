package br.com.devdojo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*criptografia de senha*/
public class PassWordEnconding {

    public static void main(String[] args) {

        //algoritmo para criptografar a senha
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("Santiago"));

    }

}
