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
    static int countlikesFrom1 = 0;
    static int countlikesFrom2 = 0;
    static int fianlCount = 0;
    
    public static int prophesize(String newPost, String accessToken, Double sliderVal)
    {
        result1.clear();
        result2.clear();
        fianlCount = 0;
        
        HashMap<String,ArrayList<String>> hash = FBHashGet.getCleanHash(accessToken); 
        
        countlikesFrom1 = algoMachOne(newPost, hash, sliderVal);
        countlikesFrom2 = algoMachTwo(FBHashGet.userCountList, sliderVal);
        
        System.out.println(result1);
        System.out.println(result2);

        return countlikesFrom1;
    }
    
    public static int algoMachOne(String post, HashMap<String,ArrayList<String>> hash, Double sliderVal)
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
            if (prob > Math.abs(100.0 - sliderVal)/100)
            {
                result1.put(name, prob);
            }
        }   
        for (String name : result1.keySet())
        {
            if (result1.get(name) >= Math.abs(100.0 - sliderVal)/100)
            {
                count++;
            }
        }
        return count;
    }
    
    public static int algoMachTwo(HashMap<String,Integer> hash, Double sliderVal)
    {
        int count = 0;
        for (String name : hash.keySet())
        {    
            result2.put(name, (hash.get(name)*1.0)/FBHashGet.totalPosts);
            if (result2.get(name) >= Math.abs(100.0 - sliderVal)/100)
            {
                count++;
            }
        }   
        return count;
    }
}
