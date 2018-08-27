package Report;

public class report_DTO {
    private int number;
    private String name;
    private int kor;
    private int eng;

    public report_DTO(int i, String name, int number, int kor, int eng) {
       this.number = number;
       this.kor = kor;
       this.eng = eng;
    }
    public report_DTO( String name ) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getKor() {
        return kor;
    }

    public int getEng() {
        return eng;
    }
}
