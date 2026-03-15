package org.example.coding_study.day10;

import java.io.*;

/**
 * Phase 10: 입출력(I/O)과 직렬화(Serialization)
 * [학습 이유]
 * 1. 입출력(I/O) : 파일 저장과 읽기 / 프로그램 외부 자원(파일, 네트워크)과의 데이터 통로 설계
 * 2. 직렬화(Serialization) : 객체(붕어빵) 냉동 보관 / 힙(Heap) 메모리 객체 상태의 영속성(Persistence) 부여
 * [학습 목표]
 * 1. 스트림(Stream)의 바이트 단위와 문자 단위 처리 차이를 이해함
 * 2. 보조 스트림(Buffered)을 통한 성능 향상 기법을 익힘
 * 3. 객체 직렬화(Serializable)를 통해 데이터를 영구 저장하는 법을 배움
 */
public class Phase10IOAndSerialization {

    public static void main(String[] args) {

        // 1. 바이트 스트림 vs 문자 스트림
        // 이미지나 음악 등 모든 데이터를 다룸 / 1바이트 단위로 데이터를 주고받는 가장 기초적인 통로(InputStream/OutputStream)
        String rawFile = "binary_data.dat";
        // 한글이나 영어 등 글자 처리에 특화됨 / 2바이트 단위(Unicode)로 문자를 해석하여 입출력하는 통로(Reader/Writer)
        String textFile = "study_note.txt";

        System.out.println("1. 스트림 생성 및 파일 쓰기 시작");
        try (FileWriter fw = new FileWriter(textFile)) {
            fw.write("10일 차: 자바 입출력 학습 중입니다.");
            System.out.println("   - 텍스트 파일을 생성했습니다: " + textFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. 보조 스트림 (Buffered Stream)
        // 데이터를 한꺼번에 옮기는 큰 바구니 / 내부적으로 버퍼(Buffer) 메모리 공간을 두어 물리적인 입출력 횟수를 줄여 성능을 최적화함
        System.out.println("\n2. 보조 스트림을 이용한 파일 읽기:");
        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            // 한 줄씩 읽어오기 / 줄바꿈 기호(\n, \r)를 기준으로 버퍼에서 데이터를 잘라와 문자열로 반환함
            String line = br.readLine();
            System.out.println("   - 읽은 내용: " + line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. 객체 직렬화 (Serialization)
        // 붕어빵(객체)을 냉동 포장하여 보관함 / 힙(Heap) 메모리에 있는 객체 인스턴스의 상태를 일렬의 바이트 데이터로 변환하여 저장함
        System.out.println("\n3. 객체 직렬화 및 복원 실습:");
        Student student = new Student("홍길동", 20);
        String objFile = "student.obj";

        // 객체를 파일로 내보내기 / ObjectOutputStream이 객체 그래프를 탐색하며 멤버 변수들을 바이트 스트림으로 직렬화함
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(objFile))) {
            oos.writeObject(student);
            System.out.println("   - 객체를 저장했습니다: " + student);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 파일에서 객체 다시 불러오기 / 저장된 바이트 데이터를 읽어와 힙(Heap) 메모리에 동일한 상태의 새로운 인스턴스를 복원(Deserialization)함
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(objFile))) {
            Student restoredStudent = (Student) ois.readObject();
            System.out.println("   - 객체를 복원했습니다: " + restoredStudent);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*
         * [오늘의 요약]
         * 1. 스트림은 데이터가 흐르는 통로 / 단방향(In/Out)으로만 흐르며 FIFO(First-In First-Out) 구조를 가짐
         * 2. 보조 스트림은 속도를 높여주는 장치 / 주 스트림에 연결하여 버퍼링(Buffering) 등의 부가 기능을 제공함
         * 3. 직렬화는 객체를 데이터로 만드는 것 / 클래스가 Serializable 인터페이스를 구현해야 하며 serialVersionUID로 버전을 관리함
         * 4. 자원 반납은 필수 / 입출력 종료 후 스트림을 닫아야 메모리 누수(Memory Leak)와 파일 잠김을 방지할 수 있음
         */
    }

    // 직렬화 대상 클래스 (Serializable 구현 필수)
    // 이 클래스는 직렬화가 가능함 / JVM에게 이 객체의 상태를 바이트로 변환해도 좋다는 승인(Marker Interface)을 함
    static class Student implements Serializable {
        // 객체의 버전 번호 / 직렬화된 데이터와 현재 클래스 구조가 일치하는지 확인하는 고유 식별값(Fingerprint)
        private static final long serialVersionUID = 1L;
        String name;
        int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', age=" + age + "}";
        }
    }
}
