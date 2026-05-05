import java.util.Scanner;
/**
 * MyApp 객체들을 입력받고 누구에게 물어볼지 몇단을 물어볼지 입력받는 프로그램
 *
 * @author (2023320022 편규빈)
 * @version (2026. 05. 05)
 */
public class MyApp
{
   public static void main(String[] args){
       Scanner scanner = new Scanner(System.in);
    
       // 객체
       TeamMember Jenna = new TeamMember("Jenna");
       TeamMember John = new TeamMember("John");
       TeamMember Maria = new TeamMember("Maria");
       TeamMember James = new TeamMember("James");
       
       // 누구에게 물어볼지 입력받기
       System.out.print("질문할 팀원 이름을 입력하시오: ");
       String targetName = scanner.next();
       
       // 몇단을 물어볼건지 입력받기
       System.out.print("원하는 구구단 수를 입력하세요: ");
       int dan = scanner.nextInt();
       
    }
}