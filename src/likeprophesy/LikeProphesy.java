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
    
    static HashMap<String,Double> result1 = new HashMap<String,Double>();
    static HashMap<String,Double> result2 = new HashMap<String,Double>();
    
    public static void prophesize(String newPost, String accessToken)
    {
        result1.clear();
        result2.clear();
        HashMap<String,ArrayList<String>> hash = FBHashGet.getCleanHash(accessToken); 
        
        algoMachOne(newPost, hash);
        algoMachTwo(FBHashGet.userCountList);
        
        System.out.println(result1);
        System.out.println(result2);
        
        System.out.println("Projected New Likes by algo1= " + result1.size());
    }
    
    public static void algoMachOne(String post, HashMap<String,ArrayList<String>> hash)
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
            prob = (matches*1.0)/testWords.size();
            if (prob > 0.0)
            {
                result1.put(name, prob);
            }
        }   
    }
    
    public static void algoMachTwo(HashMap<String,Integer> hash)
    {
        for (String name : hash.keySet())
        {    
            result2.put(name, (hash.get(name)*1.0)/FBHashGet.totalPosts);
        }   
    }
}
