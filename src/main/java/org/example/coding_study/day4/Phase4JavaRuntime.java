package org.example.coding_study.day4;

/**
 * Phase 4: 자바 실행 환경과 JVM (Java Runtime and JVM)
 * 작성한 코드가 어떻게 실행되는지, JVM이 메모리를 어떻게 효율적으로 관리하는지 배움 / 소스 코드가 바이트코드로 변환되어 클래스 로더를 통해 메모리에 적재되고, 실행 엔진에 의해 기계어로 번역되어 실행되는 전 과정을 이해함
 * [학습 목표]
 * 1. .java -> .class -> 기계어까지의 빌드 및 실행 과정을 이해함
 * 2. JVM의 런타임 데이터 영역(Runtime Data Areas) 구성을 파악함
 * 3. 가비지 컬렉션(Garbage Collection)의 기본 원리를 이해함
 */
public class Phase4JavaRuntime {

    public static void main(String[] args) {
        
        // 1. 컴파일 단계 (Compilation Phase)
        // 사람이 쓴 소스 코드를 JVM용 설계도로 바꿈 / javac 컴파일러가 .java 파일을 읽어 바이트코드인 .class 파일로 번역함
        // [작동 위치] javac는 JVM 내부가 아니라, OS에 설치된 JDK(Java Development Kit) 환경에서 독립적인 프로그램으로 작동함
        // [파일명 규칙] public 클래스명과 파일명이 반드시 일치해야 컴파일러가 대상을 식별할 수 있음
        System.out.println("1. 컴파일 완료: JDK 환경의 javac가 .java를 .class(바이트코드)로 변환함");

        // 2. 클래스 로더 (Class Loader)
        // 설계도를 공장 메모리로 가져옴 / 실행 시점에 필요한 .class 파일들을 JVM의 Method Area로 동적으로 로드하고 검증함
        System.out.println("2. 클래스 로더: 번역된 .class 파일을 JVM 메모리(Method Area)에 적재함");

        // 3. 런타임 데이터 영역 (Runtime Data Areas)
        // 용도에 따라 메모리를 나누어 관리함 / 실행 중인 프로그램이 사용하는 Stack(지역 변수), Heap(객체), Method(클래스 정보) 영역임
        Runtime runtime = Runtime.getRuntime();
        long total = runtime.totalMemory() / 1024 / 1024;
        long free = runtime.freeMemory() / 1024 / 1024;

        System.out.println("3. 메모리 영역 확인 (Heap):");
        System.out.println("   - 현재 할당된 총 메모리: " + total + "MB");
        System.out.println("   - 사용 가능한 여유 메모리: " + free + "MB");

        // 4. 가비지 컬렉션 (Garbage Collection)
        // 안 쓰는 물건을 알아서 버림 / Heap 영역에서 더 이상 참조되지 않는 객체들을 자동으로 찾아 메모리에서 해제함
        createGarbage();
        System.gc(); // 명시적 GC 요청 (실제 환경에서는 JVM이 판단하여 수행함)
        System.out.println("4. GC 실행: 참조가 끊긴 객체들을 정리하여 메모리 공간을 확보함");

        // 5. 실행 엔진 (Execution Engine)
        // 설계도를 보고 실제로 기계를 돌림 / 로드된 바이트코드를 인터프리터나 JIT 컴파일러가 OS에 맞는 기계어로 바꾸어 실행함
        System.out.println("5. 실행 엔진: JIT 컴파일러가 반복되는 코드를 최적화하여 물리적 CPU에 명령을 전달함");

        /*
         * [오늘의 요약]
         * 1. javac(컴파일러)는 JDK에 포함된 도구로, 우리 OS(윈도우 등)에서 소스 코드를 번역하는 역할을 함
         * 2. 컴파일러는 .java를 .class로 바꾸며, 이때 파일명과 클래스명이 같아야 함
         * 3. JVM은 'Write Once, Run Anywhere'를 실현하는 가상 컴퓨터임
         * 4. 메모리는 크게 Method(클래스), Stack(메서드/변수), Heap(데이터/객체)으로 나뉨
         * 5. GC 덕분에 개발자는 직접적인 메모리 해제 작업 없이 비즈니스 로직에 집중할 수 있음
         */
    }

    private static void createGarbage() {
        for (int i = 0; i < 10000; i++) {
            // Heap 영역에 생성되지만 메서드가 끝나면 참조가 끊겨 쓰레기(Garbage)가 되는 객체들
            new String("Garbage " + i);
        }
    }
}
