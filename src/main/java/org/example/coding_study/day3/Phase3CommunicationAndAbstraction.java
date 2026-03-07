package org.example.coding_study.day3;

/**
 * Phase 3: 소통과 추상화 (Communication and Abstraction)
 * 데이터를 주고받는 방식과 유연한 설계를 위한 약속을 배움 / 메서드 호출 시 생성되는 스택 프레임과 다형성을 통한 동적 바인딩의 원리를 이해함
 * [학습 목표]
 * 1. 매개변수가 스택 메모리에 어떻게 할당되는지 이해함
 * 2. 인터페이스를 통한 결합도 감소의 의미를 파악함
 * 3. 다형성이 메모리상에서 어떻게 구현되는지 확인함
 */
public class Phase3CommunicationAndAbstraction {

    public static void main(String[] args) {
        
        // 1. 매개변수(Parameter)와 스택 프레임
        // 함수에 넘겨주는 재료임 / 메서드 실행 시 스택 영역에 독립적으로 할당되는 지역 변수임
        int result = calculate(10, 20);

        // 2. 인터페이스(Interface)와 다형성(Polymorphism)
        // 기능을 정의한 목록임 / 객체 간의 결합도를 낮추기 위해 정의한 추상적인 계약이자 규격임
        // 하나의 변수가 여러 형태를 가짐 / 조모 타입의 참조 변수가 다양한 자식 인스턴스를 가리키는 현상임
        RemoteControl control = new TvControl();

        // 3. 동적 바인딩 (Dynamic Binding)
        // 연결된 기능을 실행함 / 실행 시점(Runtime)에 참조 변수가 가리키는 실제 인스턴스의 메서드를 찾아가 실행함
        control.turnOn();
        control.setVolume(10);
        control.turnOff();
    }

    // 메서드와 리턴값
    // 결과물을 돌려줌 / 연산이 끝난 후 호출한 곳으로 제어권과 데이터를 반환하며 스택 프레임을 해제함
    public static int calculate(int a, int b) {
        return a + b;
    }

    // 인터페이스 정의
    interface RemoteControl {
        void turnOn();
        void turnOff();
        void setVolume(int volume);
    }

    // 구현체 클래스
    static class TvControl implements RemoteControl {
        @Override
        public void turnOn() {
            // 행위의 실행 / 힙 영역의 인스턴스 상태를 변경하거나 콘솔 출력을 수행함
            System.out.println("TV 전원을 켬");
        }

        @Override
        public void turnOff() {
            System.out.println("TV 전원을 끔");
        }

        @Override
        public void setVolume(int volume) {
            // 매개변수 활용 / 스택에 전달된 값을 이용해 힙 영역의 인스턴스 변수를 수정함
            System.out.println("TV 볼륨 조절: " + volume);
        }
    }
}
