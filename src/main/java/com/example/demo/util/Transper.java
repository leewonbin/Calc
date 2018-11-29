package com.example.demo.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Transper {
	
	private static String clientId = "skdAVB8YJWIxHBD0hdsw";
	private static String clientSecret="5uhwgZ2Bwo";
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("번역할  한국어를 입력해주세요 :");
		String koStr = scan.nextLine();
		if(koStr.trim().isEmpty()) {
			System.out.println("번역 할 내용을 입력해주세요.");
			//	System.exit(0); <-자바 프로그램 종료
			return;
		}
		if(koStr.trim().length() > 50) {
			System.out.println("번역할 글자는 50글자 이하 입니다.");
			return;
		}
		String regex = "^[ㄱ-ㅎ가-힣]*$";
		if(!Pattern.matches(regex, koStr)) {
			System.out.println("번역은 한글만 지원합니다.");
			return;
		}
		try {
            String text = URLEncoder.encode(koStr, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/language/translate";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=ko&target=en&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
		System.out.println(Pattern.matches(regex, koStr));
		System.out.println("프로그램 정상 종료!");
	}

}
