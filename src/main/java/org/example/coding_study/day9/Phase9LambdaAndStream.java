package org.example.coding_study.day9;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Phase 9: 람다(Lambda)와 스트림(Stream)
 * 자바 8부터 도입된 함수형 프로그래밍 스타일을 배움 / 코드를 더 간결하게 만들고 데이터 처리를 선언적으로 수행하는 원리를 이해함
 * [학습 목표]
 * 1. 람다식을 통해 익명 함수(Anonymous Function)를 작성하는 법을 익힘
 * 2. 함수형 인터페이스(Functional Interface)의 개념을 파악함
 * 3. 스트림 API를 사용하여 컬렉션 데이터를 효율적으로 필터링하고 변환하는 법을 배움
 */
public class Phase9LambdaAndStream {

    public static void main(String[] args) {

        // 1. 기존 방식 vs 람다 방식 (Runnable 예시)
        // [기존] 익명 객체를 생성하여 전달 (코드가 길고 복잡함)
        Runnable oldRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("1. 기존 방식: 익명 객체로 실행 중");
            }
        };

        // [람다] 화살표 연산자(->)를 사용하여 동작만 전달 (간결함)
        // (매개변수) -> { 실행문 } 구조 / 인터페이스의 추상 메서드가 하나일 때만 가능함
        Runnable lambdaRunnable = () -> System.out.println("2. 람다 방식: 함수형 코드로 실행 중");

        oldRunnable.run();
        lambdaRunnable.run();

        // 2. 스트림(Stream) API 활용
        // 데이터를 일렬로 세워두고 파이프라인(Pipeline)을 통해 하나씩 처리하는 개념임
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

        System.out.println("\n3. 스트림을 활용한 데이터 처리:");
        
        // [중간 연산] filter(조건에 맞는 것만), map(데이터 변환)
        // [최종 연산] forEach(출력), collect(결과 수집)
        List<String> filteredNames = names.stream()
                .filter(name -> name.length() >= 5)           // 5글자 이상인 이름만 필터링
                .map(name -> name.toUpperCase())              // 대문자로 변환
                .sorted()                                     // 정렬
                .collect(Collectors.toList());                // 리스트로 다시 만듦

        System.out.println("   - 필터링된 결과: " + filteredNames);

        // 3. 스트림을 이용한 숫자 계산
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        int sumOfEvens = numbers.stream()
                .filter(n -> n % 2 == 0)                      // 짝수만 선택
                .mapToInt(n -> n)                             // 기본 자료형으로 변환
                .sum();                                       // 합계 계산

        System.out.println("4. 1부터 10까지 짝수의 합: " + sumOfEvens);

        /*
         * [오늘의 요약]
         * 1. 람다는 메서드를 하나의 식으로 표현한 것이며, 동작을 파라미터로 전달할 수 있게 해줌
         * 2. 함수형 인터페이스(@FunctionalInterface)는 단 하나의 추상 메서드만 가진 인터페이스임
         * 3. 스트림은 데이터를 변경(Mutation)하지 않고, 흐름(Flow) 속에서 가공하여 결과를 냄 (불변성 유지)
         * 4. 명령형(어떻게 할 것인가)에서 선언형(무엇을 할 것인가) 프로그래밍으로의 패러다임 전환임
         */
    }
}
