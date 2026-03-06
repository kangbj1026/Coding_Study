package org.example.coding_study.day2;

/**
 * Phase 2: 객체지향의 설계와 실체 (Object-Oriented Design and Reality)
 * 현실 세계의 사물이나 개념을 프로그램 속에 객체로 만들어 조립하는 방식임 / 데이터와 그 데이터를 처리하는 행위를 하나의 단위인 '객체'로 묶어 관리하며, 캡슐화와 다형성 등을 활용해 코드의 재사용성과 유지보수성을 높이는 프로그래밍 패러다임임
 * [학습 목표]
 * 1. 클래스가 메모리(Method Area)에 어떻게 저장되는지 이해함
 * 2. 인스턴스 생성 시 Heap 영역에 어떤 변화가 생기는지 확인함
 * 3. 생성자와 this 키워드의 메모리적 역할을 파악함
 */
public class Phase2ObjectOriented {

    public static void main(String[] args) {
        
        // 1. 클래스(Class)와 인스턴스(Instance)
        // 객체를 만들기 위한 설계도임 / Method Area에 정의된 객체의 상태(필드)와 행위(메서드)에 대한 메타데이터임
        // 설계도로 만든 실제 물건임 / 실행 시점(Runtime)에 Heap 영역에 동적으로 할당된 실체화된 메모리 공간임
        Computer myCom = new Computer("내컴퓨터", 16);

        // 2. 생성자(Constructor)
        // 객체를 만들 때 초기값을 넣어주는 기능임 / 메모리 할당 직후 Heap 영역의 데이터를 초기화하기 위해 호출되는 특수 메서드임
        Computer yourCom = new Computer("니컴퓨터", 8);

        System.out.println("1. 인스턴스 생성 확인: " + myCom.modelName + ", " + yourCom.modelName);

        // 3. 참조 변수와 주소값
        // 변수에 객체를 담음 / Stack 영역에 생성된 변수가 Heap 영역에 있는 인스턴스의 시작 주소를 가리킴
        Computer refCom = myCom; 

        // 4. 메서드 호출과 행위
        // 객체에게 일을 시킴 / Method Area에 있는 공통된 코드 로직을 해당 인스턴스의 데이터를 사용해 실행함
        myCom.powerOn();
        yourCom.powerOn();
    }

    // 공부를 위한 내부 클래스 정의
    static class Computer {
        String modelName;
        int ramSize;

        // 생성자
        // 객체 생성 시 필수 값을 지정함 / 인스턴스 필드에 주소값을 매핑하여 초기 상태를 구성함
        public Computer(String modelName, int ramSize) {
            // 나 자신을 가리킴 / 현재 힙 영역에 생성된 인스턴스 본인의 시작 주소를 의미함
            this.modelName = modelName;
            this.ramSize = ramSize;
        }

        public void powerOn() {
            // 인스턴스 변수 사용 / 참조 변수(this)가 가리키는 힙 메모리의 특정 데이터를 읽어옴
            System.out.println(this.modelName + " 전원 켜짐 (RAM: " + this.ramSize + "GB)");
        }
    }
}
