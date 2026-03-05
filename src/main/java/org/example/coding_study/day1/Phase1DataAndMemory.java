package org.example.coding_study.day1;

/**
 * Phase 1: 데이터의 본질과 메모리 (Nature of Data and Memory)
 * [학습 목표]
 * 1. 타입(Type)이 메모리 크기를 어떻게 결정하는지 이해
 * 2. 기본형(Primitive)과 참조형(Reference)이 메모리의 어디에 저장되는지 구분
 * 3. 변수가 단순한 이름이 아닌 '메모리 주소의 별칭'임을 이해
 */
public class Phase1DataAndMemory {

    public static void main(String[] args) {
        
        // 1. 타입(Type)과 메모리 크기
        // 숫자를 담기 위해 int를 사용 / 4바이트(32비트)의 메모리 공간을 확보하고, 이 안의 이진수를 정수로 해석
        int age = 25;
        // 소수점이 있는 숫자를 저장 / 8바이트 공간을 확보하여 부동소수점 방식으로 데이터를 저장
        double weight = 70.5;
        
        System.out.println("1. 기본 타입의 값: " + age + ", " + weight);

        // 2. 변수(Variable)의 본질
        // 값을 저장하는 바구니 / 메모리 특정 주소(Address)에 붙인 이름(Alias)
        int anotherAge = age;
        anotherAge = 30;

        System.out.println("2. 변수 값 복사 확인: age=" + age + ", anotherAge=" + anotherAge);

        // 3. 기본형(Primitive) vs 참조형(Reference)
        // 값 그대로를 저장 / Stack 영역에 실제 데이터 값을 직접 저장
        int primitiveInt = 100;

        // 문자열 변수 생성 / Heap 메모리에 인스턴스를 만들고, 그 시작 주소를 변수에 저장
        String referenceStr = new String("Hello");

        System.out.println("3. 참조형 변수가 가리키는 값: " + referenceStr);

        // 4. 참조형의 '주소 복사' 실험
        // 변수를 다른 변수에 대입 / 데이터 자체가 아닌 '메모리 주소값'을 복사하여 같은 곳을 바라보게 함
        String anotherStr = referenceStr;
        
        System.out.println("4. 주소 복사 확인: " + (referenceStr == anotherStr ? "같은 주소를 가리킴" : "다른 주소임"));

        // --- Phase 1 추가 꿀팁 (전공자들이 중요하게 생각하는 것) ---

        // 5. 문자열 리터럴 풀 (String Constant Pool)
        // 똑같은 글자니까 하나만 생성 / 동일한 문자열 리터럴은 Heap 내부의 '상수 풀'에서 주소를 재사용함
        String s1 = "Java";
        String s2 = "Java";
        System.out.println("5. 리터럴 주소 비교: " + (s1 == s2 ? "주소가 같음(메모리 절약)" : "주소가 다름"));

        // 6. null의 진짜 의미
        // 값이 비어있음 / 참조 변수가 Heap 영역의 어떤 메모리 주소도 가리키지 않는 상태 (0x0)
        String nullStr = null;
        // nullStr.length(); // [전공자적 관점] 주소가 없는 곳을 찾아가려니 NullPointerException 발생!
        System.out.println("6. null 변수 확인: " + nullStr);

        /*
         * [오늘의 요약]
         * 1. 타입은 '메모리 설계도' (얼마나 크게 쓸지, 어떻게 읽을지) ## [ 값의 형태 지정 / 메모리 크기(Byte) 결정 및 데이터 해석 방식 ] ##
         * 2. 모든 데이터는 메모리 주소(Address)에 저장되지만, 우리가 쓰기 편하게 변수명(Name)을 붙여 사용 ## [ 값을 저장하는 바구니 / 메모리 주소(Address)에 붙인 이름(Alias) ] ##
         * 3. 자바 메모리는 크게 Stack(기본형, 참조형)과 Heap(실제 객체 데이터)으로 나눔 ## [ 값 그대로 vs 주소값 / Stack에 실제 값이 있는가 vs Heap의 주소가 있는가 ] ##
         * 4. 문자열 리터럴은 메모리 효율을 위해 재사용되며, null은 가리키는 주소가 없다는 뜻
         */
    }
}
