/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package likeprophesy;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author VarunJain
 */
public class LikeProphesy {
    
    static String accessToken = "CAACEdEose0cBAHdlug1565oFw6lXIc1nfosOgEX9ammEIdgSSvGcAMEXxAvC6OhOTvksHUKrOFgaUmsiiuC78n1BEkTgRlDcWC4vx56bAQaNIG6bDqf0eN6LQ1fIOzO8TmigXVmOdGzRwcH3ydZBc7aplka7tyejuhyogf5c43FPAtOV1QTk88R49wnMPQDQc72TSjgZDZD";
    
    public static int prophesize(String newPost)
    {
        HashMap<String,ArrayList<String>> hash = FBHashGet.getCleanHash(accessToken);        
        int count = 0;
        Double prob = 0.0;
        for (String name : hash.keySet())
        {
            prob = algoMachOne(newPost, name, hash);
            System.out.println(name + ": " + prob);
            if (prob > 0.0)
            {
                count++;
            }
        }   
        System.out.println("Projected New Likes = " + count);
        return count;
    }
    
    public static Double algoMachOne(String post, String name, HashMap<String,ArrayList<String>> hash)
    {
        ArrayList<String> likedWords = hash.get(name);
        ArrayList<String> testWords = FBHashGet.getPost(post, "");
        
        int matches = 0;
        Double probability = 0.0;
        
        for (String x : testWords)
        {
            if (likedWords.contains(x))
            {
                matches++;
            }
        }        
        probability = (matches*1.0)/testWords.size();
        
        return probability;
    }
   
    

}
