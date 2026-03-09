package org.example.coding_study.day5;

/**
 * Phase 5: 제네릭과 타입 안전성 (Generics & Type Safety)
 * 컴파일러가 타입을 미리 검사하여 런타임의 불안정성을 제거하는 과정을 배움 / 다양한 타입을 유연하게 다루면서도 데이터의 안정성을 보장하는 자바의 핵심 설계 방식을 이해함
 * [학습 목표]
 * 1. 제네릭을 사용하는 이유와 타입 안전성(Type Safety)의 의미를 파악함
 * 2. 컴파일 시점의 타입 체크와 실행 시점의 타입 소거(Type Erasure)를 이해함
 * 3. 잘못된 타입 캐스팅으로 발생하는 런타임 에러를 방지하는 법을 익힘
 */
public class Phase5Generics {

    public static void main(String[] args) {
        
        // 1. 제네릭 이전의 방식 (Object 사용)
        // 모든 타입을 다 담을 수 있는 만능 바구니임 / 내부적으로 Object 타입을 사용하므로 데이터를 꺼낼 때 반드시 형변환(Casting)이 필요함
        Box objectBox = new Box();
        objectBox.setItem("Apple"); 
        
        // 위험한 형변환 / 컴파일 시점에는 에러가 안 나지만, 실행 시(Runtime)에 타입이 안 맞으면 프로그램이 죽음(ClassCastException)
        String fruit = (String) objectBox.getItem();
        System.out.println("1. 일반 박스 결과: " + fruit);

        // 2. 제네릭(Generics)의 도입
        // 타입을 미리 정해두는 바구니임 / 클래스를 정의할 때가 아니라 객체를 생성할 때 사용할 타입을 결정함
        // [컴파일러의 역할] javac가 이 박스에는 'String'만 들어갈 수 있음을 미리 감시함
        GenericBox<String> stringBox = new GenericBox<>();
        stringBox.setItem("Orange");
        
        // 형변환 생략 가능 / 컴파일러가 이미 String임을 보장하므로 안전하게 꺼낼 수 있음
        String myFruit = stringBox.getItem();
        System.out.println("2. 제네릭 박스 결과: " + myFruit);

        // 3. 타입 안전성(Type Safety) 실험
        // 다른 타입을 넣으려고 시도함 / 컴파일러(javac)가 4일차에 배운 번역 과정에서 즉시 에러를 발생시켜 실행조차 못하게 막음
        // stringBox.setItem(123); // [컴파일 에러!] "너 String 박스라고 했잖아! 숫자는 안 돼!"

        // 4. 타입 소거 (Type Erasure) - 전공자적 관점
        // 실행할 때는 타입을 지워버림 / 하위 호환성을 위해 JVM 실행 시점에는 제네릭 정보가 사라지고 Object로 처리됨
        // [JVM 메모리] 실행 중인 Heap 영역에는 GenericBox<String>과 GenericBox<Integer>가 똑같은 구조로 존재함
        System.out.println("4. 타입 소거: 실행 시점에는 제네릭 정보가 사라지지만, 컴파일러가 이미 안전을 보장했기에 괜찮음");

        /*
         * [오늘의 요약]
         * 1. 제네릭은 '타입을 파라미터로 전달'하여 유연성과 안전성을 동시에 잡는 기술임
         * 2. 컴파일러(javac)는 제네릭을 보고 미리 타입을 체크하여 런타임 에러를 방지함
         * 3. 런타임(JVM) 시점에는 타입 정보가 사라지는 '타입 소거'가 발생하지만, 이미 검증이 끝난 상태라 안전함
         * 4. 이 과정을 통해 4일차에 배운 '컴파일러의 역할'이 얼마나 중요한지 다시 한번 깨닫게 됨
         */
    }

    // 1번 예제를 위한 일반 박스 클래스
    static class Box {
        private Object item;
        public void setItem(Object item) { this.item = item; }
        public Object getItem() { return item; }
    }

    // 2번 예제를 위한 제네릭 박스 클래스 <T>
    // T는 Type의 약자로, 나중에 결정될 타입을 위한 예약어임
    static class GenericBox<T> {
        private T item;
        public void setItem(T item) { this.item = item; }
        public T getItem() { return item; }
    }
}
