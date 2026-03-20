package org.example.coding_study.day13;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Phase 13: JUnit과 단위 테스트 (Unit Testing)
 */
public class CalculatorTest {

    private Calculator calculator;

    // 각 테스트 메서드가 실행되기 전마다 호출됨
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        System.out.println("   - [Setup] 새로운 계산기 인스턴스 생성 (Heap 영역 할당)");
    }

    @Test
    @DisplayName("1. 덧셈 테스트: 10 + 20은 30이어야 함")
    void testAdd() {
        int result = calculator.add(10, 20);
        // 기대값과 실제값이 일치하는지 단언(Assertion)함
        assertEquals(30, result, "덧셈 결과가 올바르지 않습니다.");
    }

    @Test
    @DisplayName("2. 예외 처리 테스트: 0으로 나누면 ArithmeticException 발생")
    void testDivideByZero() {
        // 특정 예외가 발생하는지 검증함
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10, 0);
        }, "0으로 나눌 때 예외가 발생해야 합니다.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("   - [TearDown] 테스트 리소스 정리");
    }

    /*
     * [테스트 학습 요약]
     * 1. @Test: 이 메서드가 테스트 케이스임을 나타내는 어노테이션 (리플렉션에 의해 실행됨)
     * 2. Assertions: 기대하는 상태(Expected)와 실제 상태(Actual)를 비교하는 도구
     * 3. 격리성: 각 테스트는 독립적이어야 하며, 다른 테스트에 영향을 주지 않아야 함
     */
}
