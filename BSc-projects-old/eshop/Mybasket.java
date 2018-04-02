/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kalathi;

import java.io.Serializable;
import javax.servlet.http.*;
import java.util.*;

/**
 *
 * @author johnfouf
 */
public class Mybasket implements Serializable{
    private
          ArrayList<ArrayList<String>> basket;
          HttpSession session;


    public Mybasket(HttpSession mysession){
         session = mysession;
          basket = new ArrayList<ArrayList<String>>();
        if(session.getAttribute("basket")!=null)
            basket = (ArrayList<ArrayList<String>>) session.getAttribute("basket");
        else{
            add_item("sfgr","gaesr","rgte");
            remove_item("sfgr","gaesr","rgte");
        }

           

    }

    public void remove_item(String myfile, String dir,String user) {
        ArrayList<String> eps = new ArrayList<String>();
                    eps.add(myfile);
                    eps.add(dir);
                    eps.add(user);
                    if(basket.contains(eps)){
                        basket.remove(basket.lastIndexOf(eps));
                        session.setAttribute( "basket", basket );
                    }
    }

    public String mybasket(int i, int j){
        return basket.get(i).get(j);
    }

    public int size(){
        return basket.size();
    }

    public void add_item(String myfile, String dir,String user){
        ArrayList<String> eps = new ArrayList<String>();
                eps.add(myfile);
                eps.add(dir);
                eps.add(user);
                if(!basket.contains(eps)){
                    basket.add(eps);
                    session.setAttribute( "basket", basket );
                }
    }

     public boolean contain(ArrayList<String> myeps){
        return basket.contains(myeps);
    }






}

