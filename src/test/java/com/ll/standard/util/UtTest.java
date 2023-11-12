package com.ll.standard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UtTest {

    @Test
    @DisplayName("파일 생성, 삭제")
    void t1(){
        String filePath="temp/test.txt";
        Ut.file.save(filePath,"내용");

        assertThat(Ut.file.exists(filePath)).isTrue();

        Ut.file.delete(filePath);

    }

    @Test
    @DisplayName("파일 내용 읽기")
    void t2(){
        String filePath="temp/test.txt";
        Ut.file.save(filePath,"내용");


        final String content=Ut.file.getContents(filePath);

        assertThat(content).isEqualTo("내용");

        Ut.file.delete(filePath);

    }
}
