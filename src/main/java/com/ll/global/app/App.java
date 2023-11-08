package com.ll.global.app;

import java.util.Scanner;

public class App {

    private final Scanner scanner;

    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        long lastQuotationId=0;

        while (true){

            String cmd=scanner.nextLine().trim();

            if(cmd.equals("등록")){
                System.out.println("명언 : ");
                final String content=scanner.nextLine().trim();
                System.out.println("작가 : ");
                final String authorName=scanner.nextLine().trim();
                final long id=++lastQuotationId;

                System.out.printf("%d번 명언이 등록 되었습니다.",(id));
            }else if(cmd.equals("종료")) return;


        }



    }
}
