package org.example.coding_study.day8;

/**
 * Phase 8: 멀티스레딩 기초 (Multithreading Concepts)
 * 하나의 프로그램 내부에서 여러 개의 작업 흐름(Thread)을 동시에 실행하는 법을 배움 / CPU 자원을 효율적으로 사용하여 프로그램의 응답성을 높이는 원리를 이해함
 * [학습 목표]
 * 1. 프로세스(Process)와 스레드(Thread)의 개념적 차이를 파악함
 * 2. 자바에서 스레드를 생성하는 두 가지 방식(Thread 클래스, Runnable 인터페이스)을 익힘
 * 3. 스레드별 독립적인 Stack 영역과 공유되는 Heap 영역의 메모리 구조를 이해함
 */
public class Phase8Multithreading {

    public static void main(String[] args) {
        
        // [사전 지식] 현재 이 코드를 실행하는 것도 하나의 스레드임
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("0. 현재 실행 중인 스레드: " + currentThreadName + " (이게 바로 Main 스레드임)");

        // 1. 스레드 생성 방식 1: Thread 클래스 상속
        // 클래스 자체를 일꾼으로 만듦 / 상속을 받으므로 다른 클래스를 상속받을 수 없다는 단점이 있음
        MyThread thread1 = new MyThread();

        // 2. 스레드 생성 방식 2: Runnable 인터페이스 구현
        // 작업 내용(Run)만 따로 정의하여 일꾼에게 전달함 / 다중 상속이 불가능한 자바에서 가장 권장되는 방식임
        // [메모리 관점] 인터페이스 구현체는 데이터(Heap)로 존재하고, 스레드 객체가 이를 가져다 실행함
        Thread thread2 = new Thread(new MyRunnable());

        // 3. 스레드 시작 (start)
        // 새로운 작업 흐름을 실행함 / run()을 직접 호출하는 것이 아니라 start()를 통해 JVM에게 새로운 Stack을 만들라고 명령함
        // [JVM 관점] start() 호출 시 새로운 Stack Frame이 독립적으로 생성되어 Main 스레드와 별개로 움직임
        System.out.println("1. 멀티스레드 작업 시작 명령 전달");
        thread1.start();
        thread2.start();

        // 4. 메인 스레드의 독립성 확인
        // 다른 스레드가 일하는 동안 메인은 자기 할 일을 계속함 / 비동기(Asynchronous)적인 흐름의 시작임
        for (int i = 1; i <= 3; i++) {
            System.out.println("[Main 스레드] " + i + "번째 업무 수행 중...");
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }

        /*
         * [오늘의 요약]
         * 1. 프로세스는 실행 중인 프로그램(공장), 스레드는 그 안의 일꾼(Worker)임
         * 2. 모든 스레드는 자신만의 Stack 메모리를 가지며, Heap 영역의 객체는 공유함
         * 3. start()는 새로운 Stack을 생성하고 독립적인 실행 흐름을 만드는 트리거임
         * 4. 멀티스레드는 성능을 높여주지만, 공유 자원에 동시에 접근할 때 발생하는 동기화 문제에 주의해야 함
         */
    }

    // 방식 1: Thread 상속
    static class MyThread extends Thread {
        @Override
        public void run() {
            // 새로운 스레드가 할 일 정의
            for (int i = 1; i <= 3; i++) {
                System.out.println("   [Thread 상속 일꾼] " + i + "번 작업 중...");
                try { Thread.sleep(700); } catch (InterruptedException e) {}
            }
        }
    }

    // 방식 2: Runnable 구현 (권장)
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println("      [Runnable 구현 일꾼] " + i + "번 작업 중...");
                try { Thread.sleep(600); } catch (InterruptedException e) {}
            }
        }
    }
}
