package com.ll.standard.util;

import lombok.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UtTest {

    private static final String TEMP_DIR = "temp/";
    private final String testFilePath = TEMP_DIR + "test.txt";
    private final String test2FilePath = TEMP_DIR + "test2.txt";


    @BeforeEach
    void beforeEach() {
        Ut.file.save(testFilePath, "내용");
    }

    @AfterEach
    void afterEach() {
        Ut.file.delete(testFilePath);
        // 만약 test2.txt 파일도 여러 테스트에 걸쳐 사용된다면, afterEach에서 삭제하는 것이 좋습니다.
        Ut.file.delete(test2FilePath);
    }

    @Test
    @DisplayName("파일 생성, 삭제")
    void t1() {
        assertThat(Ut.file.exists(testFilePath)).isTrue();
    }

    @Test
    @DisplayName("파일 내용 읽기")
    void t2() {
        final String content = Ut.file.getContent(testFilePath);

        assertThat(content).isEqualTo("내용");
    }


    @Test
    @DisplayName("파일 내용을 읽은 후 long 타입으로 변환")
    void t3() {
        Ut.file.save(test2FilePath, "100");
        final long age = Ut.file.getContentAsLong(test2FilePath, 0);
        assertThat(age).isEqualTo(100);
    }

    @Test
    @DisplayName("없는 파일 읽으라는 시도를 하면 null 반환")
    void t4() {
        final String nonExistentFilePath = TEMP_DIR + "not-exists.txt";
        final String content = Ut.file.getContent(nonExistentFilePath);
        assertThat(content).isNull();

    }

    @Test
    @DisplayName("객체가 파일로 저장될 수 있다")
    void t5() {
        Ut.file.save(testFilePath, new TestArticle(1, "제목", "내용"));
        final String content = Ut.file.getContent(testFilePath);

        assertThat(content).isNotBlank();

    }

    @Test
    @DisplayName("JSON 형식으로 파일에 저장된 객체를 읽을 수 있다.")
    void t6() {

        // 기대하는 객체를 생성합니다.
        final TestArticle expectedArticle = new TestArticle(1, "제목", "내용");

        // 객체를 파일에 저장합니다.
        Ut.file.save(testFilePath, expectedArticle);

        // 파일로부터 객체를 읽어옵니다.
        final TestArticle actualArticle = Ut.file.getContent(testFilePath, TestArticle.class);

        // actualArticle이 null이 아님을 확인합니다.
        assertThat(actualArticle).isNotNull();

        // actualArticle이 expectedArticle과 필드별로 동일한지 검증합니다.
        assertThat(actualArticle).usingRecursiveComparison().isEqualTo(expectedArticle);

    }


}

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Getter
class TestArticle {
    private long id;
    private String title;
    private String content;

}