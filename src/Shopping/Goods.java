package Shopping;

public class Goods {    // DTO
    private String pname;
    private int price;

    public Goods(String pname, int price) {
        this.pname = pname;
        this.price = price;
    }

    public String getPname() {
        return pname;
    }

    public int getPrice() {
        return price;
    }
}
