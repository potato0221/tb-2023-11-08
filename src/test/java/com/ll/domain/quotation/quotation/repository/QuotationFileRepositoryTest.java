package com.ll.domain.quotation.quotation.repository;

import com.ll.domain.quotation.quotation.entity.Quotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuotationFileRepositoryTest {

    @Test
    @DisplayName("save를 하면 quotation의 id에 새 번호가 할당된다.")
    void t1(){
        QuotationFileRepository repository=new QuotationFileRepository();
        Quotation quotation=new Quotation("작가1","내용1");
        repository.save(quotation);

        assertThat(quotation.getId()).isEqualTo(1L);
    }
}
