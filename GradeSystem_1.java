/**
 * GradeSystem 클래스의 설명을 작성하세요.
 *
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
 */

import java.util.Scanner;

// ======================== 메인 클래스 ========================
public class GradeSystem_1 {

    static final int MAX = 50;
    static Scanner   sc  = new Scanner(System.in);

    // 과목명 & 학점 (18학점: 6과목 x 3학점)
    static String[] subjects = { "객체지향프로그래밍", "자료구조", "운영체제",
                                  "데이터베이스", "컴퓨터네트워크", "소프트웨어공학" };
    static int[]    credits  = { 3, 3, 3, 3, 3, 3 };

    // 학생 객체 배열
    static Student[] students = new Student[MAX];
    static int       count    = 0;

    // ======================== main ========================
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("   선문대학교 성적처리 시스템");
        System.out.println("====================================");

        int menu;
        do {
            printMenu();
            menu = inputInt("메뉴 선택: ", 0, 4);

            switch (menu) {
                case 1: inputGrade();    break;
                case 2: viewAll();       break;
                case 3: searchStudent(); break;
                case 4: searchSubject(); break;
                case 0: System.out.println("\n시스템을 종료합니다."); break;
            }
        } while (menu != 0);

        sc.close();
    }

    // ======================== 메뉴 출력 ========================
    static void printMenu() {
        System.out.println("\n------------------------------------");
        System.out.println("  1. 성적 입력");
        System.out.println("  2. 전체 성적 조회");
        System.out.println("  3. 학생별 성적 조회");
        System.out.println("  4. 과목별 성적 조회");
        System.out.println("  0. 종료");
        System.out.println("------------------------------------");
    }

    // ======================== 1. 성적 입력 ========================
    static void inputGrade() {
        if (count >= MAX) {
            System.out.println("최대 인원을 초과했습니다.");
            return;
        }

        System.out.println("\n[ 성적 입력 ]");
        System.out.print("이름  : ");
        String name = sc.nextLine().trim();
        System.out.print("학번  : ");
        String id = sc.nextLine().trim();

        // 중복 학번 확인
        for (int i = 0; i < count; i++) {
            if (students[i].studentId.equals(id)) {
                System.out.println("이미 등록된 학번입니다.");
                return;
            }
        }

        int year = inputInt("학년 (1~4): ", 1, 4);
        int sem  = inputInt("학기 (1~2): ", 1, 2);

        Student s = new Student(name, id, year, sem);   // 객체 생성

        System.out.println("\n과목별 점수 입력 (0~100, 건너뛰기: -1)");
        for (int i = 0; i < subjects.length; i++) {
            System.out.print(subjects[i] + " : ");      // println → print + 문자열 연결
            int score = inputInt("", -1, 100);
            if (score >= 0) {
                s.scores[i] = score;
            }
        }

        students[count++] = s;
        System.out.println("\n등록 완료: " + name + " (" + id + ")");
    }

    // ======================== 2. 전체 성적 조회 ========================
    static void viewAll() {
        if (count == 0) {
            System.out.println("등록된 학생이 없습니다.");
            return;
        }

        System.out.println("\n[ 전체 성적 조회 ]");

        for (int i = 0; i < count; i++) {
            Student s = students[i];
            System.out.println("====================================");
            System.out.println("이름: " + s.name
                + "  학번: " + s.studentId
                + "  " + s.year + "학년 " + s.semester + "학기");
            System.out.println("------------------------------------");

            for (int j = 0; j < subjects.length; j++) {    // for 반복문
                int score = s.scores[j];
                if (score >= 0) {                           // if-else 조건문
                    System.out.println("  " + subjects[j]
                        + " : " + score + "점  (" + s.getGrade(score) + ")");
                } else {
                    System.out.println("  " + subjects[j] + " : 미입력");
                }
            }

            System.out.println("------------------------------------");
            // Math.round 로 소수점 2자리 처리
            double gpa = Math.round(s.calcAvgGPA(credits) * 100) / 100.0;
            System.out.println("  평점 평균: " + gpa);
        }
        System.out.println("====================================");
    }

    // ======================== 3. 학생별 성적 조회 ========================
    static void searchStudent() {
        System.out.print("\n이름 또는 학번 입력: ");
        String keyword = sc.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < count; i++) {
            Student s = students[i];
            if (!s.name.equals(keyword) && !s.studentId.equals(keyword)) {
                continue;                                   // continue
            }

            System.out.println("\n[ 성적표 ]");
            s.printInfo();
            System.out.println("------------------------------------------");
            System.out.println("과목명          학점   점수   등급");
            System.out.println("------------------------------------------");

            for (int j = 0; j < subjects.length; j++) {
                int score = s.scores[j];
                if (score >= 0) {                           // if-else 조건문
                    System.out.println(subjects[j]
                        + "   " + credits[j] + "학점"
                        + "   " + score + "점"
                        + "   " + s.getGrade(score));
                } else {
                    System.out.println(subjects[j]
                        + "   " + credits[j] + "학점"
                        + "   미입력");
                }
            }

            System.out.println("------------------------------------------");
            double gpa = Math.round(s.calcAvgGPA(credits) * 100) / 100.0;
            System.out.println("평점 평균: " + gpa);
            found = true;
        }

        if (!found) {
            System.out.println("해당 학생을 찾을 수 없습니다.");
        }
    }

    // ======================== 4. 과목별 성적 조회 ========================
    static void searchSubject() {
        System.out.println("\n과목 선택:");
        for (int i = 0; i < subjects.length; i++) {
            System.out.println("  " + (i + 1) + ". " + subjects[i]);
        }
        int idx = inputInt("선택: ", 1, subjects.length) - 1;

        System.out.println("\n[ " + subjects[idx] + " 성적 조회 ]");
        System.out.println("이름      학번          점수   등급");
        System.out.println("--------------------------------");

        int sum = 0, cnt = 0, max = -1, min = 101;

        for (int i = 0; i < count; i++) {
            int score = students[i].scores[idx];
            if (score < 0) {
                continue;                                   // continue
            }
            System.out.println(students[i].name
                + "   " + students[i].studentId
                + "   " + score + "점"
                + "   " + students[i].getGrade(score));
            sum += score;
            cnt++;
            if (score > max) {                              // if 조건문
                max = score;
            }
            if (score < min) {                              // if 조건문
                min = score;
            }
        }

        System.out.println("--------------------------------");
        if (cnt > 0) {                                      // if-else 조건문
            // Math.round 로 소수점 1자리 처리
            double avg = Math.round((double) sum / cnt * 10) / 10.0;
            System.out.println("인원: " + cnt + "명"
                + "   최고: " + max + "점"
                + "   최저: " + min + "점"
                + "   평균: " + avg + "점");
        } else {
            System.out.println("입력된 성적이 없습니다.");
        }
    }

    // ======================== 정수 입력 (예외처리) ========================
    static int inputInt(String prompt, int min, int max) {
        while (true) {                                      // while 반복문
            try {
                if (!prompt.isEmpty()) {
                    System.out.print(prompt);
                }
                int val = Integer.parseInt(sc.nextLine().trim());
                if (val >= min && val <= max) {
                    return val;
                }
                System.out.println("  " + min + " ~ " + max + " 사이 값을 입력하세요.");
            } catch (NumberFormatException e) {             // 예외처리
                System.out.println("  숫자만 입력 가능합니다.");
            }
        }
    }
}