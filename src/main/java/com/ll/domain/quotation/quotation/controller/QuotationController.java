package com.ll.domain.quotation.quotation.controller;

import com.ll.domain.quotation.quotation.entity.Quotation;
import com.ll.global.rq.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuotationController {
    final Scanner scanner;
    final List<Quotation> quotations;
    long lastQuotationId;

    public QuotationController(final Scanner scanner){
        this.scanner=scanner;
        quotations = new ArrayList<>();
        lastQuotationId = 0;
    }


    public void actionRemove(final Rq rq) {
        final long id = rq.getParameterAsLong("id", 0);

        quotations
                .stream()
                .filter(_quotation -> _quotation.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                        quotation -> {
                            quotations.remove(quotation);
                            System.out.printf("%d번 명언이 삭제 되었습니다.", id);
                        },
                        () -> System.out.printf("%d번 명언은 존재하지 않습니다.", id)
                );
    }

    public void actionModify(final Rq rq) {
        final long id = rq.getParameterAsLong("id", 0);
        quotations
                .stream()
                .filter(_quotation -> _quotation.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                        quotation -> {
                            System.out.printf("명언(기존) : %s", quotation.getContent());
                            System.out.println("명언 : ");
                            final String content = scanner.nextLine().trim();
                            System.out.printf("작가(기존) : %s", quotation.getAuthorName());
                            System.out.println("작가 : ");
                            final String authorName = scanner.nextLine().trim();

                            quotation.setContent(content);
                            quotation.setAuthorName(authorName);

                            System.out.printf("%d번 명언이 수정 되었습니다.", id);
                        },
                        () -> System.out.printf("%d번 명언은 존재하지 않습니다.", id)
                );
    }

    public void actionShowList() {

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

    public void actionWrite() {
        System.out.print("명언 : ");
        final String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        final String authorName = scanner.nextLine().trim();

        final long id = ++lastQuotationId;

        Quotation quotation = new Quotation(id, authorName, content);
        quotations.add(quotation);

        System.out.println("%d번 명언이 등록 되었습니다.".formatted(id));
    }

    public void dispatch(Rq rq) {
        switch (rq.getAction()){
            case "삭제" -> actionRemove(rq);
            case "수정" -> actionModify(rq);
            case "목록" -> actionShowList();
            case "등록" -> actionWrite();
        }
    }
}
