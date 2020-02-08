/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gamecam.menickacz.data;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Erik Juríček
 */
public class Menu extends ArrayList<Item> {

    private Date date;

    public Menu() {
        super();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
