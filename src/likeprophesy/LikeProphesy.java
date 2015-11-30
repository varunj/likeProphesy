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
        
        HashMap<String,Double> result1 = algoMachOne(newPost, hash);
        System.out.println(result1);
        
        HashMap<String,Double> result2 = algoMachTwo(FBHashGet.userCountList);
        System.out.println(result2);
        
        System.out.println("Projected New Likes = " + result1.size());
        return result1.size();
    }
    
    public static HashMap<String,Double> algoMachOne(String post, HashMap<String,ArrayList<String>> hash)
    {
        HashMap<String,Double> result = new HashMap<String,Double>();
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
                result.put(name, prob);
            }
        }   
        return result;
    }
    
    public static HashMap<String,Double> algoMachTwo(HashMap<String,Integer> hash)
    {
        HashMap<String,Double> result = new HashMap<String,Double>();
        for (String name : hash.keySet())
        {    
            result.put(name, (hash.get(name)*1.0)/FBHashGet.totalPosts);
        }   
        return result;
    }
}
