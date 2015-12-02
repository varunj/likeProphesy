/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package likeprophesy;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.restfb.types.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static likeprophesy.LikeProphesy.postToPredict;

/**
 *
 * @author VarunJain, Ayush Agarwal
 */
public class FBHashGet {
    
    static HashMap<String,Integer> userCountList = new HashMap<String,Integer>();
    static ArrayList<Integer> numberLikes = new ArrayList<>();
    static int totalPosts = 0;
    static int chk = 1;
    static LocalDate datee = null;
    
    public static HashMap<String,ArrayList<String>> getCleanHash(String accessToken)
    {
        HashMap<String,ArrayList<String>> userLikedList = new HashMap<String,ArrayList<String>>(); 
        try
        {
            FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
            
            User user = facebookClient.fetchObject("me", User.class);
            
            Connection<Post> myFeedConnection = facebookClient.fetchConnection(user.getId() + "/feed", Post.class,
                                                    Parameter.with("fields", "likes.limit(100),comments.summary(true),"
                                                            + "type,with_tags,updated_time,shares,place,picture,message_tags,name,description,"
                                                            + "message,link")
                                                     );
            for (List<Post> feed : myFeedConnection)
            {
                for (Post post : feed)
                {
                    if (post.getLikes() != null)
                    {
                        ArrayList<String> names = getNames(post.getLikes().getData().toString());
                        ArrayList<String> postWords = getPost(post.getDescription(), post.getMessage(), post.getName());   // -type1: answer: i have not -type2: normal text -type3: should i choose
                        
                        if (!postWords.isEmpty())
                        {
                            numberLikes.add(names.size());
                            totalPosts++;
                            for(String person : names)
                            {
                                
                                if (postWords.toString().equals(postToPredict))     chk = 1;
                                datee = LocalDate.parse(post.getUpdatedTime().toString().split(" ")[5] + "-" + "12" + "-" + post.getUpdatedTime().toString().split(" ")[2]);
                                
                                if (userLikedList.containsKey(person) && chk == 1)
                                {
                                    ArrayList<String> newWords = new ArrayList<String>();
                                    newWords.addAll(userLikedList.get(person));
                                    newWords.addAll(postWords);
                                    userLikedList.put(person, newWords);
                                    userCountList.put(person, userCountList.get(person)+1);
                                }
                                else
                                {
                                    userLikedList.put(person, postWords);
                                    userCountList.put(person, 1);
                                }
                            }
                        }
                    }
                }
            }
            for (String key : userLikedList.keySet())
            {
                userLikedList.put(key, cleanMyHash(userLikedList.get(key)));
            }
        }
        catch(com.restfb.exception.FacebookOAuthException ex)
        {
              System.out.println("\nBooyah Token Expired!!!!!!!!!\n");
        }
        return userLikedList;
    }
    
    public static ArrayList<String> getNames(String x)
    {
        String[] levOne = x.split("name=");
        ArrayList<String> nameList = new ArrayList<String>();
        for (String y : levOne)
        {
            String name = y.split(" type=")[0];
            nameList.add(name);
        } 
        nameList.remove(0);
        return nameList;
    }
    
    public static ArrayList<String> getPost(String x, String y, String z)
    {
        String str = x + " " + y + " " + z;
        str = str.replaceAll("//", "/"); 
        str = str.replaceAll("\n", "").replace("\r", "");; 
        str = str.replaceAll("[^A-Za-z]+", " ");
        str = str.replaceAll("  ", " ");
        str = str.replaceAll("    ", " ");
        str = str.replaceAll("  ", " ");
        str = str.replaceAll("  ", " ");
        str = str.replaceAll("null ", "");
        str = str.replaceAll("null", "");
        str = str.replaceAll("  ", " ");
        str = str.replaceAll("  ", " ");
        
        ArrayList<String> strArr = new ArrayList<String>(); 
        for (String eachWord : str.split(" "))
        {
            if (eachWord.length() > 0)
            {
                strArr.add(eachWord.toLowerCase());
            }
        }
        
        return strArr;
    }
    
    public static ArrayList<String> cleanMyHash(ArrayList<String> inp)
    {
        List<String> stopWords = Arrays.asList("a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any", "are", "arent", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both", "but", "by", "cant", "cannot", "could", "couldnt", "did", "didnt", "do", "does", "doesnt", "doing", "dont", "down", "during", "each", "few", "for", "from", "further", "had", "hadnt", "has", "hasnt", "have", "havent", "having", "he", "hed", "hell", "hes", "her", "here", "heres", "hers", "herself", "him", "himself", "his", "how", "hows", "i", "id", "ill", "im", "ive", "if", "in", "into", "is", "isnt", "it", "its", "its", "itself", "lets", "me", "more", "most", "mustnt", "my", "myself", "no", "nor", "not", "of", "off", "on", "once", "only", "or", "other", "ought", "our", "ours", "ourselves", "out", "over", "own", "same", "shant", "she", "shed", "shell", "shes", "should", "shouldnt", "so", "some", "such", "than", "that", "thats", "the", "their", "theirs", "them", "themselves", "then", "there", "theres", "these", "they", "theyd", "theyll", "theyre", "theyve", "this", "those", "through", "to", "too", "under", "until", "up", "very", "was", "wasnt", "we", "wed", "well", "were", "weve", "were", "werent", "what", "whats", "when", "whens", "where", "wheres", "which", "while", "who", "whos", "whom", "why", "whys", "with", "wont", "would", "wouldnt", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves");
        ArrayList<String> newWords = new ArrayList<String>();
        for (String x : inp)
        {
            if (!stopWords.contains(x))
            {
                if (!newWords.contains(x))
                    newWords.add(x);
            }            
        }
        return newWords;
    }

}
