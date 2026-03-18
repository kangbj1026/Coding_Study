package org.example.coding_study.day12;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Phase 12: 어노테이션과 리플렉션 (Annotation & Reflection)
 * [학습 이유]
 * 1. 프레임워크의 마법 이해: 스프링 부트에서 사용하는 @Component, @Autowired 등이 어떻게 동작하는지 그 원리를 파악함
 * 2. 런타임 제어: 실행 중에 클래스의 정보를 분석하고 비공개(private) 멤버에 접근하거나 동적으로 객체를 생성하는 기술을 익힘
 * [학습 목표]
 * 1. 어노테이션(Annotation)의 정의와 커스텀 어노테이션 제작법을 배움
 * 2. 리플렉션(Reflection) API를 사용하여 클래스 정보(필드, 메서드, 생성자)를 추출하는 법을 익힘
 * 3. 런타임에 동적으로 메서드를 호출하거나 값을 변경하는 실무적 활용 사례를 확인함
 */

// 1. 커스텀 어노테이션 정의
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD}) // 적용 대상: 클래스, 메서드, 필드
@Retention(RetentionPolicy.RUNTIME) // 유지 기간: 실행 시점까지 (리플렉션에서 사용 가능)
@interface MyStudyInfo {
    String value() default "Java Study";
    int day() default 12;
}

// 2. 어노테이션을 적용한 테스트 클래스
@MyStudyInfo(value = "Annotation & Reflection", day = 12)
class Member {
    private String name;
    public int age;

    public Member() {
        this.name = "Unknown";
        this.age = 0;
    }

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void sayHello() {
        System.out.println("   - [Private Method] 안녕하세요, " + name + "입니다.");
    }

    public void info() {
        System.out.println("   - [Public Method] 이름: " + name + ", 나이: " + age);
    }
}

public class Phase12AnnotationReflection {

    public static void main(String[] args) {

        System.out.println("1. 어노테이션 정보 추출:");
        Class<Member> memberClass = Member.class;
        if (memberClass.isAnnotationPresent(MyStudyInfo.class)) {
            MyStudyInfo info = memberClass.getAnnotation(MyStudyInfo.class);
            System.out.println("   - 적용된 어노테이션 값: " + info.value());
            System.out.println("   - 학습 일차: Day " + info.day());
        }

        System.out.println("\n2. 리플렉션을 통한 클래스 정보 분석:");
        System.out.println("   - 클래스명: " + memberClass.getSimpleName());
        
        System.out.print("   - 모든 필드: ");
        for (Field field : memberClass.getDeclaredFields()) {
            System.out.print(field.getName() + " ");
        }
        System.out.println();

        System.out.println("\n3. 리플렉션으로 동적 객체 생성 및 비공개 멤버 접근:");
        try {
            // 생성자를 찾아 객체 생성
            Constructor<Member> constructor = memberClass.getConstructor(String.class, int.class);
            Member member = constructor.newInstance("홍길동", 25);
            member.info();

            // 비공개(private) 필드 접근 및 수정
            Field nameField = memberClass.getDeclaredField("name");
            nameField.setAccessible(true); // 강제 접근 허용
            nameField.set(member, "이몽룡");
            System.out.println("   - [수정 후] ");
            member.info();

            // 비공개(private) 메서드 호출
            Method helloMethod = memberClass.getDeclaredMethod("sayHello");
            helloMethod.setAccessible(true);
            helloMethod.invoke(member);

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * [오늘의 요약]
         * 1. 어노테이션은 코드에 붙이는 '메타데이터'로, 컴파일러나 런타임 도구에게 정보를 제공함
         * 2. 리플렉션은 구체적인 클래스 타입을 몰라도 런타임에 클래스의 내부 정보를 들여다보고 조작할 수 있는 기능임
         * 3. 강력한 기능이지만, 성능 저하와 캡슐화 파괴의 위험이 있으므로 꼭 필요한 경우(프레임워크 제작 등)에만 신중히 사용해야 함
         * 4. 스프링의 @Service, @Repository 등은 이러한 리플렉션 기술을 통해 객체를 관리함
         */
    }
}
