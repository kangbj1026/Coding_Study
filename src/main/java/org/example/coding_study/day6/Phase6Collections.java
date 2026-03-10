package org.example.coding_study.day6;

import java.util.*;

/**
 * Phase 6: 컬렉션 프레임워크 (Collections Framework)
 * 여러 데이터를 효율적으로 저장하고 관리하기 위한 표준화된 방식을 배움 / 데이터의 특성에 따라 어떤 자료구조를 선택해야 메모리와 성능 면에서 유리한지 이해함
 * [학습 목표]
 * 1. List, Set, Map의 핵심 차이와 용도를 파악함
 * 2. 제네릭이 컬렉션에서 데이터 안전성을 어떻게 보장하는지 확인함
 * 3. 각 컬렉션 인터페이스의 주요 구현체(ArrayList, HashSet, HashMap)의 특징을 이해함
 */
public class Phase6Collections {

    public static void main(String[] args) {
        
        // 1. List: 순서가 있는 목록
        // 중복을 허용하고 데이터의 순서를 유지함 / 내부적으로 동적 배열(Array) 구조를 사용하여 인덱스로 빠른 접근이 가능함
        // [메모리 관점] ArrayList는 연속된 메모리 공간을 점유하여 참조 효율이 높음
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Spring");
        list.add("Java"); // 중복 허용
        
        System.out.println("1. List 결과: " + list + " (크기: " + list.size() + ")");

        // 2. Set: 집합 (중복 불가)
        // 데이터의 중복을 허용하지 않음 / 순서를 보장하지 않으며(HashSet 기준), 수학의 집합 개념을 구현함
        // [메모리 관점] 내부적으로 해시 테이블(Hash Table)을 사용하여 데이터의 존재 여부를 매우 빠르게 판단함
        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("Spring");
        set.add("Java"); // 중복 무시됨
        
        System.out.println("2. Set 결과: " + set + " (중복은 하나만 남음)");

        // 3. Map: 키-값 쌍 (Key-Value)
        // 고유한 키(Key)와 그에 대응하는 값(Value)을 연결함 / 리스트처럼 순서로 찾는 것이 아니라 '이름'으로 데이터를 찾음
        // [메모리 관점] Key는 Set 구조를 가져 중복이 안 되며, Value는 중복이 가능함
        Map<String, Integer> map = new HashMap<>();
        map.put("Java", 100);
        map.put("Spring", 90);
        map.put("Java", 95); // 같은 키에 넣으면 기존 값이 덮어씌워짐
        
        System.out.println("3. Map 결과: Java의 점수는 " + map.get("Java") + "점");

        // 4. 컬렉션과 제네릭의 결합
        // 타입을 강제하여 5일차에 배운 '타입 안전성'을 확보함 / 컴파일러가 다른 타입이 들어오는 것을 미리 차단함
        // list.add(10); // [컴파일 에러] String 전용 리스트에 숫자를 넣을 수 없음

        /*
         * [오늘의 요약]
         * 1. List: 순서 중요, 중복 허용 (데이터를 차례대로 나열할 때 사용)
         * 2. Set: 순서 무관, 중복 불가 (고유한 값들의 목록을 관리할 때 사용)
         * 3. Map: Key-Value 구조 (이름표를 붙여서 데이터를 빠르게 찾을 때 사용)
         * 4. 컬렉션 프레임워크는 모두 인터페이스(List, Set, Map) 기반이므로, 필요에 따라 구현체(ArrayList, LinkedList 등)를 유연하게 바꿀 수 있음
         */
    }
}
