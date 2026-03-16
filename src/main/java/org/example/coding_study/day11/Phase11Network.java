package org.example.coding_study.day11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Phase 11: 네트워크 프로그래밍 (Network Programming)
 * [학습 이유]
 * 1. 컴퓨터 간의 대화: 로컬 프로그램을 넘어 다른 컴퓨터와 데이터를 주고받는 방법을 이해함
 * 2. 분산 시스템의 기초: 웹(Web)과 앱(App) 등 현대 애플리케이션의 핵심인 통신 원리를 익힘
 * [학습 목표]
 * 1. IP 주소와 포트(Port)의 개념을 이해하고 자바에서 다루는 법을 배움
 * 2. URL 클래스를 통해 웹 상의 데이터를 읽어오는 방법을 익힘
 * 3. 소켓(Socket) 통신의 기본 흐름을 이해함
 */
public class Phase11Network {

    public static void main(String[] args) {

        // 1. IP 주소 확인 (InetAddress)
        // 컴퓨터의 주소록 / 도메인 이름(www.google.com)을 기계가 이해하는 IP 주소로 변환하거나 로컬 호스트 정보를 얻음
        System.out.println("1. IP 주소 확인 및 도메인 해석:");
        try {
            InetAddress local = InetAddress.getLocalHost();
            System.out.println("   - 내 컴퓨터(LocalHost) IP: " + local.getHostAddress());
            System.out.println("   - 내 컴퓨터 이름: " + local.getHostName());

            InetAddress google = InetAddress.getByName("www.google.com");
            System.out.println("   - 구글(Google) IP: " + google.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // 2. URL을 통한 웹 데이터 읽기
        // 웹 브라우저 흉내내기 / URL(Uniform Resource Locator)을 통해 인터넷 상의 리소스(HTML, 이미지 등)에 접근함
        System.out.println("\n2. URL을 통한 웹 데이터 읽기:");
        String urlString = "https://www.google.com";
        try {
            URL url = new URL(urlString);
            System.out.println("   - 접속할 주소(Protocol): " + url.getProtocol());
            System.out.println("   - 접속할 호스트(Host): " + url.getHost());

            // 스트림을 열어 데이터 읽기 (Day 10의 I/O 개념 활용)
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line = br.readLine(); // 첫 줄만 읽어서 확인
                System.out.println("   - 읽어온 웹 페이지 첫 줄: " + line);
                System.out.println("   - (보안 정책이나 네트워크 상태에 따라 내용이 다를 수 있습니다)");
            }
        } catch (Exception e) {
            System.out.println("   - 웹 연결 중 오류 발생: " + e.getMessage());
        }

        // 3. 소켓(Socket) 프로그래밍 개념
        // 전화기와 전화번호 / 서버(Server)는 소켓을 열고 기다리고, 클라이언트(Client)는 그 소켓(IP+Port)으로 연결을 요청함
        System.out.println("\n3. 소켓(Socket) 통신 개념 (이론):");
        System.out.println("   - 서버 소켓(ServerSocket): 특정 포트(예: 8080)에서 연결 요청을 기다림 (Listen)");
        System.out.println("   - 클라이언트 소켓(Socket): 서버의 IP와 포트로 연결을 시도함 (Connect)");
        System.out.println("   - 연결이 수립되면 양쪽에서 스트림(InputStream/OutputStream)을 통해 데이터를 주고받음");

        /*
         * [오늘의 요약]
         * 1. InetAddress는 컴퓨터의 명찰 / 도메인 이름을 IP 주소로 바꿔주는 DNS 역할을 수행함
         * 2. URL은 인터넷 자원의 주소 / 프로토콜(http, https), 호스트, 포트, 경로 등으로 구성됨
         * 3. 네트워크는 결국 I/O의 확장 / 파일 대신 소켓을 통해 데이터를 읽고 쓰는 것이 핵심 원리임
         * 4. 포트(Port)는 문(Door) / 하나의 컴퓨터 내에서 여러 프로그램이 통신할 수 있도록 구분해주는 번호임
         */
    }
}
