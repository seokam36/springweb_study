package spring_practice_1.model.dto;

public class WaitListDto {
    private int num;
    private String telNum;
    private int people;

    public WaitListDto() {
    }

    public WaitListDto(int num, String telNum, int people) {
        this.num = num;
        this.telNum = telNum;
        this.people = people;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "WaitListDto{" +
                "num=" + num +
                ", telNum='" + telNum + '\'' +
                ", people=" + people +
                '}';
    }
}
