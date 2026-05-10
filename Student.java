
/**
 * Student 클래스의 설명을 작성하세요.
 *
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
 */

// ======================== 학생 클래스 ========================
class Student {
    String name;
    String studentId;
    int    year;
    int    semester;
    int[]  scores;          // 과목별 점수 배열 (-1 = 미입력)

    // 생성자
    Student(String name, String studentId, int year, int semester) {
        this.name      = name;
        this.studentId = studentId;
        this.year      = year;
        this.semester  = semester;
        this.scores    = new int[6];
        for (int i = 0; i < 6; i++) this.scores[i] = -1;
    }

    // 점수 → 등급 변환
    String getGrade(int score) {
        if      (score >= 95) return "A+";
        else if (score >= 90) return "A";
        else if (score >= 85) return "B+";
        else if (score >= 80) return "B";
        else if (score >= 75) return "C+";
        else if (score >= 70) return "C";
        else if (score >= 65) return "D+";
        else if (score >= 60) return "D";
        else                  return "F ";
    }

    // 등급 → 평점 변환
    double getGPA(String grade) {
        switch (grade.trim()) {
            case "A+": return 4.5;
            case "A": return 4.0;
            case "B+": return 3.5;
            case "B": return 3.0;
            case "C+": return 2.5;
            case "C": return 2.0;
            case "D+": return 1.5;
            case "D": return 1.0;
            default:   return 0.0;
        }
    }

    // 평점 평균 계산
    double calcAvgGPA(int[] credits) {
        double total     = 0;
        int    creditSum = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] >= 0) {
                total     += getGPA(getGrade(scores[i])) * credits[i];
                creditSum += credits[i];
            }
        }
        return (creditSum > 0) ? total / creditSum : 0.0;
    }

    // 학생 정보 출력
    void printInfo() {
        System.out.println("이름: " + name + "  학번: " + studentId
            + "  " + year + "학년  " + semester + "학기");
    }
}