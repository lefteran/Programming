/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

/**
 *
 * @author johnfouf
 */

public class RegBean implements java.io.Serializable
{
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Integer capacity;
    private String cardno;
    private String cvv;
    private String date;

    public void setUsername(String username)
    {
        this.username=username;
    }

    public void setPassword(String passwd)
    {
        this.password=passwd;
    }

     public void setName(String onoma)
    {
        this.name=onoma;
    }

      public void setSurname(String epitheto)
    {
        this.surname=epitheto;
    }

      public void setEmail(String mail)
    {
        this.email=mail;
    }

     public void setCapacity(Integer cap)
    {
        this.capacity=cap;
    }

      public void setCardno(String card)
    {
        this.cardno=card;
    }

       public void setCvv(String cvv2)
    {
        this.cvv=cvv2;
    }

      public void setDate(String day)
    {
        this.date=day;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getName()
    {
       return this.name;
    }

      public String getSurname()
    {
       return this.surname;
    }

      public String getEmail()
    {
        return this.email;
    }

     public Integer getCapacity()
    {
        return this.capacity;
    }

      public String getCardno()
    {
        return this.cardno;
    }

       public String getCvv()
    {
        return this.cvv;
    }

      public String getDate()
    {
        return this.date;
    }
}
