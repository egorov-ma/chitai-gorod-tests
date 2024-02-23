package ru.chitaigorod;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {

        String s = "%D0%A7%D0%B8%D1%81%D1%82%D0%B0%D1%8F%20%D0%B0%D1%80%D1%85%D0%B8%D1%82%D0%B5%D0%BA%D1%82%D1%83%D1%80%D0%B0";
        String query = "random word £500 bank $";
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String decodedQuery = URLDecoder.decode(s, StandardCharsets.UTF_8);
        System.out.println(encodedQuery);
        System.out.println(decodedQuery);
    }
}