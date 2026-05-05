import java.util.Scanner;
/**
 * MyApp 객체들을 입력받고 누구에게 물어볼지, 몇단을 물어볼지 입력받는 프로그램
 *
 * @author (2023320022 편규빈, 2023320004 호준수)
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

        if(dan >= 2 && dan <=9){
            switch(targetName.toLowerCase()){  // 대소문자 구분 없는 비교를 위해 소문자로 변환
                case "jenna":
                    Jenna.Gugudan(dan);
                    break;
                case "john":
                    John.Gugudan(dan);
                    break;               
                case "maria":
                    Maria.Gugudan(dan);
                    break;
                case "james":
                    James.Gugudan(dan);
                    break;
                default:
                    System.out.println("해당 이름의 팀원이 없습니다.");
            }
        }
    }
}