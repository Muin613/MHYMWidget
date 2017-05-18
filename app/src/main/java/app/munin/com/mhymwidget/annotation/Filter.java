package app.munin.com.mhymwidget.annotation;

/**
 * Created by Administrator on 2017/5/1.
 */
@Table("t_user")
public class Filter {

    @Column("id")
    private int id;
    @Column("userName")
    private String userName;
    @Column("nickName")
    private String nickName;
    @Column("age")
    private String age;
    @Column("city")
    private String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
