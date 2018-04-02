/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

/**
 *
 * @author johnfouf
 */

public class UserBean implements java.io.Serializable
{
    private String username;
    private String password;

    public void setUsername(String username)
    {
        this.username=username;
    }

    public void setPassword(String passwd)
    {
        this.password=passwd;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

}
