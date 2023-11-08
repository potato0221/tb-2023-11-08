package com.ll.global.app;

import com.ll.domain.quotation.quotation.entity.Quotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private final Scanner scanner;


    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        List<Quotation> quotations=new ArrayList<>();
        long lastQuotationId=0;

        while (true){

            String cmd=scanner.nextLine().trim();

            if(cmd.equals("등록")){
                System.out.println("명언 : ");
                final String content=scanner.nextLine().trim();
                System.out.println("작가 : ");
                final String authorName=scanner.nextLine().trim();
                final long id=++lastQuotationId;

                Quotation quotation=new Quotation(id,authorName,content);
                quotations.add(quotation);

                System.out.printf("%d번 명언이 등록 되었습니다.",(id));
            }else if(cmd.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("--------------------");

                quotations
                        .reversed()
                        .forEach(
                                quotation -> System.out.printf(
                                        "%d / %s / %s",
                                        quotation.getId(),
                                        quotation.getAuthorName(),
                                        quotation.getContent()
                                )
                        );

            }else if(cmd.equals("종료")) return;




        }



    }
}
