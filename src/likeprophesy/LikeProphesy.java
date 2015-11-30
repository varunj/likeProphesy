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
    
    public static int prophesize(String newPost, String accessToken)
    {
        HashMap<String,ArrayList<String>> hash = FBHashGet.getCleanHash(accessToken);        
        int count1 = algoMachOne(newPost, hash);
        int count2 = algoMachTwo(FBHashGet.userCountList);
        return count1;
    }
    
    public static int algoMachOne(String post, HashMap<String,ArrayList<String>> hash)
    {
        int count = 0, matches = 0;
        Double prob = 0.0;
        for (String name : hash.keySet())
        {
            ArrayList<String> likedWords = hash.get(name);
            ArrayList<String> testWords = FBHashGet.getPost(post, "", "");
            
            matches = 0;
            for (String x : testWords)
            {
                if (likedWords.contains(x))
                {
                    matches++;
                }
            }        
            prob = (matches*1.000000000)/testWords.size();
            if (prob > 0.0)
            {
                System.out.println(name + ": " + prob);
                count++;
            }
        }   
        System.out.println("Projected New Likes = " + count);
        return count;
    }
    
    public static int algoMachTwo(HashMap<String,Integer> hash)
    {
        int count = 0;
        
        return count;
    }
}
