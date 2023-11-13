package com.ll.domain.quotation.quotation.repository;

import com.ll.domain.quotation.quotation.entity.Quotation;
import com.ll.standard.util.Ut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuotationFileRepositoryTest {

    @BeforeEach
    void beforeEach(){
        Ut.file.delete("data/prod/quotation");
    }
    @Test
    @DisplayName("save를 하면 quotation의 id에 새 번호가 할당된다.")
    void t1(){
        QuotationFileRepository repository=new QuotationFileRepository();
        Quotation quotation=new Quotation("작가1","내용1");
        repository.save(quotation);

        assertThat(quotation.getId()).isEqualTo(1L);
    }
    @Test
    @DisplayName("1번 명언을 저장하면 테이블 폴더에 1.json이 생긴다")
    void t2(){
        QuotationFileRepository repository=new QuotationFileRepository();
        Quotation quotation=new Quotation("작가1","내용1");
        repository.save(quotation);

        assertThat(Ut.file.exists("data/prod/quotation/1.json")).isTrue();
    }
}
