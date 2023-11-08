package com.ll.global.app;

import com.ll.standard.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    private String run(String cmd) {
        Scanner scanner = TestUtil.genScanner(cmd.stripIndent().trim() + "\n종료");

        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        new App(scanner).run();

        String out = byteArrayOutputStream.toString().trim();
        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);

        return out.trim();

    }

    @Test
    @DisplayName("프로그램 시작 시 \"== 명언 앱 ==\" 출력")
    void t1() {
        String out = run("");

        assertThat(out)
                .contains("== 명언 앱 ==");


    }

    @Test
    @DisplayName("종료")
    void t2() {
        String out = run("");

    }

    @Test
    @DisplayName("등록")
    void t3() {
        String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                종료
                """);

        assertThat(out)
                .contains("명언 :")
                .contains("작가 :")
                .contains("1번 명언이 등록 되었습니다.");

    }

    @Test
    @DisplayName("등록 할 때 마다 번호가 1씩 증가, 1건 등록")
    void t4() {
        String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out)
                .contains("1번 명언이 등록 되었습니다.")
                .doesNotContain("2번 명언이 등록 되었습니다.");
    }

    @Test
    @DisplayName("등록 할 때 마다 번호가 1씩 증가, 2건 등록")
    void t5() {

        String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out)
                .contains("1번 명언이 등록 되었습니다.")
                .contains("2번 명언이 등록 되었습니다.")
                .doesNotContain("3번 명언이 등록 되었습니다.");

    }


}
