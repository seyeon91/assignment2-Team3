
/**
 * TeamMember 클래스의 설명을 작성하세요.
 *
 * @author (2025320070 복창희, 2025320038 윤세연)
 * @version (20260505)
 */
public class TeamMember
{
    // 인스턴스 변수
    private String name;

    /**
     * TeamMember 클래스의 객체 생성자
     */
    public TeamMember(String name){
        this.name = name;
    }

    /**
     * 이름 반환
     */
    public String getName(){
        return this.name;        
    }

    /**
     * 구구단 매서드
     */
    public void Gugudan(int dan)
    {
        System.out.println(this.name + "에게, " + dan + "단을 답해주세요~");
        
        for(int i = 1; i <= 9; i++){
            System.out.print(dan + "x" + i + "=" + (dan * i));
            if(i < 9){
                System.out.print(" ");
            }
        }
        System.out.println();
    }

}