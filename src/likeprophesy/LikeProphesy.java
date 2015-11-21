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
    
    static String accessToken = "CAACEdEose0cBAAhS2ADVC7hSnBZCn6NnWZCieNH0k5ys9gTrE2FRm78xF1pLJwrSfmXwHWuxstpUXBBpwfXLpEneBAcYcGpstEMDL71HsxwMjvNWrPSufq95QRbFAXKIyjzv3X1B40t05IPFqjZC2OLFjXhuDTKWcpZAQfXEwsmezXsAF3R3ZCyoLscrZCi26ZBl9uvC60WfgZDZD";
    
    public static void main(String[] args)
    {
        String newPost = "first time cool cool hololens";
        String name = "Arpit Gogia";
        System.out.println(prophesize(newPost, name));
    }
    
    public static Double prophesize(String post, String name)
    {
        HashMap<String,ArrayList<String>> hash = FBHashGet.getCleanHash();
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
        System.out.println(likedWords);
        System.out.println(testWords);        
        probability = (matches*1.0)/testWords.size();
        
        return probability;
    }
   
    

}
