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
    
    static String accessToken = "CAACEdEose0cBAFxfqhOQ4ZAtEZCehnZBWvEpPwZA0QYaKj5m0TZChHhSkbwgJ2tUM0ECWOEB6WErTY3UYldMzZC5BF7Ri30ZBXMFGfXPBkWkNU45BqzMdcTbigFLJCclBxTIeTKnmd3pZBnASLmZCAGmrbETyZAaRmwzJdgTvi4AZAEsnpOHmO4sUd9isaV6GZBMZBZBj4n9LiONGUugZDZD";
    
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
