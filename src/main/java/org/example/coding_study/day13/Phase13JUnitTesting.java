package org.example.coding_study.day13;

/**
 * Phase 13: JUnit과 단위 테스트 (JUnit & Unit Testing)
 * [학습 이유]
 * 1. 코드 신뢰성 확보: 내가 만든 기능이 100% 동작한다는 증거를 제시함
 * 2. 리팩토링의 두려움 해소: 코드를 수정해도 기존 기능이 망가지지 않았는지 즉각 확인할 수 있음 (회귀 테스트)
 * 3. 자동화된 문서 역할: 테스트 코드를 보면 이 클래스가 어떻게 쓰여야 하는지 그 의도가 명확히 드러남
 * [학습 목표]
 * 1. JUnit의 기본 어노테이션(@Test, @BeforeEach 등)의 역할을 이해함
 * 2. Assertions 클래스를 사용하여 결과값을 검증하는 법을 배움
 * 3. 테스트 주도 개발(TDD)의 기초 개념을 파악함
 */
public class Phase13JUnitTesting {

    public static void main(String[] args) {
        System.out.println("Phase 13: JUnit & Unit Testing 학습을 시작합니다.");
        System.out.println("   - 실제 테스트 실행은 src/test/java 디렉토리의 CalculatorTest.java를 통해 수행됩니다.");
        System.out.println("   - 단위 테스트는 '하나의 기능'을 '독립적으로' 검증하는 것이 원칙입니다.");

        /*
         * [오늘의 요약]
         * 1. 단위 테스트는 가장 작은 코드 단위(주로 메서드)를 고립시켜서 검증하는 것임
         * 2. JUnit은 자바 표준 테스트 프레임워크로, 어노테이션 기반으로 동작함
         * 3. Assertions(단언)은 '이 결과가 반드시 이래야 한다'라고 선언하는 검문소와 같음
         * 4. 실패한 테스트는 시스템의 잠재적 버그를 미리 찾아주는 아주 소중한 알람임
         */
    }
}
