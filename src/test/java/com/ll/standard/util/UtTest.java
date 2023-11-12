package com.ll.standard.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UtTest {

    private final String testFilePath = "temp/test.txt";



    @BeforeEach
    void beforeEach() {
        Ut.file.save(testFilePath, "내용");



    }

    @AfterEach
    void afterEach() {
        Ut.file.delete(testFilePath);
    }

    @Test
    @DisplayName("파일 생성, 삭제")
    void t1() {
        assertThat(Ut.file.exists(testFilePath)).isTrue();
    }

    @Test
    @DisplayName("파일 내용 읽기")
    void t2() {
        final String content = Ut.file.getContents(testFilePath);

        assertThat(content).isEqualTo("내용");
    }


    @Test
    @DisplayName("파일 내용을 읽은 후 long 타입으로 변환")
    void t3() {
        final long id = Ut.file.getContentsAsLong(testFilePath,0);
        assertThat(id).isEqualTo(0);

        final String test2FilePath = "temp/test2.txt";
        Ut.file.save(test2FilePath, 100);
        final long age = Ut.file.getContentsAsLong(test2FilePath,0);
        assertThat(age).isEqualTo(100);
    }

}
