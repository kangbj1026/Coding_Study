package org.example.coding_study.day7;

/**
 * Phase 7: 예외 처리와 안정성 (Exception Handling)
 * 프로그램 실행 중 발생할 수 있는 예상치 못한 상황에 대비하는 법을 배움 / 에러가 발생했을 때 프로그램이 비정상 종료되지 않고 흐름을 제어할 수 있는 능력을 갖춤
 * [학습 목표]
 * 1. 예외(Exception)와 에러(Error)의 차이를 이해함
 * 2. 체크 예외(Checked)와 언체크 예외(Unchecked)의 구분과 처리 방식을 파악함
 * 3. try-catch-finally와 throw/throws 키워드의 메모리적, 논리적 역할을 이해함
 */
public class Phase7Exceptions {

    public static void main(String[] args) {
        
        // 1. 기본 예외 처리 (try-catch)
        // 사고가 날 법한 코드를 보호막으로 감쌈 / 에러 발생 시 프로그램이 즉시 종료되는 대신 catch 블록으로 제어권이 넘어감
        // [JVM 관점] 예외 발생 시 해당 메서드의 실행을 중단하고 예외 객체를 생성하여 일치하는 catch 핸들러를 찾음
        try {
            System.out.println("1. 연산 시작");
            int result = 10 / 0; // ArithmeticException 발생! (0으로 나눌 수 없음)
            System.out.println("연산 결과: " + result); // 실행되지 않음
        } catch (ArithmeticException e) {
            System.out.println("1. 예외 발생: 0으로 나눌 수 없습니다. (" + e.getMessage() + ")");
        }

        // 2. 반드시 실행되는 블록 (finally)
        // 무슨 일이 있어도 마무리 작업을 수행함 / 예외가 발생하든 안 하든, 심지어 return을 만나도 실행됨
        // [메모리 관점] 외부 자원(파일, DB 연결 등)을 안전하게 해제하기 위한 필수 장치임
        try {
            System.out.println("2. 자원 사용 시도");
        } finally {
            System.out.println("2. 마무리 작업: 자원을 안전하게 반납함");
        }

        // 3. 예외의 종류: Checked vs Unchecked
        // [Unchecked] 실행해봐야 아는 예외 (RuntimeException 상속) / 런타임에 결정되므로 강제로 예외 처리를 안 해도 됨
        String nullStr = null;
        // nullStr.length(); // NullPointerException 발생 (언체크 예외의 대표)

        // [Checked] 컴파일러가 미리 검사하는 예외 / 4일차에 배운 javac가 "이건 위험하니까 반드시 처리해!"라고 강제함
        // try { Thread.sleep(100); } catch (InterruptedException e) {} // 체크 예외 처리 예시

        // 4. 예외 던지기 (throw / throws)
        // 내가 처리 안 하고 떠넘기거나 직접 예외를 발생시킴 / 메서드 선언부에 throws를 붙여 호출한 쪽에 책임을 전가함
        try {
            checkAge(15);
        } catch (IllegalArgumentException e) {
            System.out.println("4. 검증 결과: " + e.getMessage());
        }

        /*
         * [오늘의 요약]
         * 1. Exception은 복구가 가능한 상황, Error는 시스템 레벨의 심각한 상황(StackOverflow 등)을 의미함
         * 2. Checked Exception은 컴파일 시점에 체크하며, Unchecked Exception은 실행 시점에 발생함
         * 3. try-catch는 예외를 직접 처리하고, throws는 예외를 호출한 쪽으로 위임하는 방식임
         * 4. 안정적인 프로그램은 모든 '정상 흐름'뿐만 아니라 '예외 상황'까지 설계된 프로그램임
         */
    }

    /**
     * 나이를 확인하여 예외를 던지는 메서드
     * @param age 나이
     * @throws IllegalArgumentException 잘못된 인자가 들어왔을 때 발생
     */
    public static void checkAge(int age) {
        if (age < 19) {
            // 직접 예외 객체를 생성하여 던짐
            throw new IllegalArgumentException("미성년자는 접근할 수 없습니다. (입력된 나이: " + age + ")");
        }
        System.out.println("접근 허용");
    }
}
