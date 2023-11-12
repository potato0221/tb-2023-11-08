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

        List<Quotation> quotations = new ArrayList<>();
        long lastQuotationId = 0;

        while (true) {

            final String cmd = scanner.nextLine().trim();
            final String[] cmdBits=cmd.split("\\?",2);
            final String action=cmdBits[0].trim();
            final String queryString=cmdBits.length==2?cmdBits[1].trim():"";

            switch (action) {
                case "삭제" -> {
                    final String idStr=queryString.replace("id=","");
                    final long id=Long.parseLong(idStr);
                    quotations
                            .removeIf(quotation -> quotation.getId() == id);

                    System.out.printf("%d번 명언이 삭제 되었습니다.",id);
                }

                case "등록" -> {
                    System.out.print("명언 : ");
                    final String content = scanner.nextLine().trim();
                    System.out.print("작가 : ");
                    final String authorName = scanner.nextLine().trim();

                    final long id = ++lastQuotationId;

                    Quotation quotation = new Quotation(id, authorName, content);
                    quotations.add(quotation);

                    System.out.println("%d번 명언이 등록 되었습니다.".formatted(id));
                }
                case "목록" -> {
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("----------------------");

                    quotations
                            .reversed()
                            .forEach(
                                    quotation -> System.out.println(
                                            "%d / %s / %s".formatted(
                                                    quotation.getId(),
                                                    quotation.getAuthorName(),
                                                    quotation.getContent()
                                            )
                                    )
                            );
                }
                case "종료" -> {
                    return;
                }
            }


        }


    }
}
