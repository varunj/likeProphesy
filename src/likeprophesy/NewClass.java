/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package likeprophesy;

import java.time.LocalDate;

/**
 *
 * @author VarunJain
 */
public class NewClass {
    public static void main(String[] args)
    {
        String a = "Fri Aug 22 21:34:13 IST 2014";
        System.out.println(LocalDate.parse(a.split(" ")[5] + "-" + "12" + "-" + a.split(" ")[2]));
    }
}
