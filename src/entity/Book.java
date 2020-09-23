package entity;


import java.util.List;

//图书实体类
public class Book {
    private int bno;                    //图书编号
    private String name;                //书名
    private String author;              //作者
    private String publish;             //出版社
    private String carrier;             //图书载体页数
    private String ISBN_price;          //isbn和定价
    private String CLCN;                //中图法编号
    private String image;                 //图书封面图片地址
    private List<Borrow> borrows;


    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getISBN_price() {
        return ISBN_price;
    }

    public void setISBN_price(String ISBN_price) {
        this.ISBN_price = ISBN_price;
    }

    public String getCLCN() {
        return CLCN;
    }

    public void setCLCN(String CLCN) {
        this.CLCN = CLCN;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bno=" + bno + '\'' +
                ", name=" + name + '\'' +
                ", author=" + author + '\'' +
                ", publish=" + publish + '\'' +
                ", carrier=" + carrier + '\'' +
                ", ISBN_price=" + ISBN_price + '\'' +
                ", CLCN=" + CLCN + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
