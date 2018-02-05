package clinivid;
import java.util.*;
import java.util.regex.Pattern;

import org.json.JSONObject;

public class TestClass {
    
    static String[] keys = {"id","name","dob","locations","imageId"};

	static LinkedHashMap<String,Object> jsonMap = new LinkedHashMap<String,Object>();

    public static LinkedHashMap<String,Object> process(String name){
//        Pattern ptn = Pattern.compile("<*>");
//        String[] str = ptn.split(name);
//        System.out.println(str.length);
    	String[] str = {"","",""};
        LinkedHashMap<String,Object> hm = new LinkedHashMap<String,Object>();
        int k=0;
        for(int i=0;i<name.length();i++){
        	if(name.charAt(i)=='<'){
        		i++;
        		while(i<name.length() && name.charAt(i)!='>'){
        			str[k] += name.charAt(i)+"";
        			i++;
        		}
        	}
        	k++;
        }
        hm.put("first",str[0]);
        hm.put("middle",str[1]);
        hm.put("last",str[2]);
        return hm;
    }
    
    public static ArrayList<Object> locations(String location){
        ArrayList<Object> result = new ArrayList<Object>();
        String[] loc = location.split(",");
        for(int i=0;i<loc.length;i++){
//            Pattern ptn = Pattern.compile("<*>");
//            String[] str = ptn.split(loc[i]);
//            System.out.println(str.length);
        	String[] str = {"","","",""};
        	int k=0;
            for(int j=0;j<loc[i].length();j++){
            	if(loc[i].charAt(j)=='<'){
            		j++;
            		while(j<loc[i].length() && loc[i].charAt(j)!='>'){
            			str[k] += loc[i].charAt(j)+"";
            			j++;
            		}
            	}
            	k++;
            }
            LinkedHashMap<String,Object> lhm = new LinkedHashMap<String,Object>();
            lhm.put("name",str[0].replaceAll("<",""));
            LinkedHashMap<String,Object> coord = new LinkedHashMap<String,Object>();
            coord.put("long",str[1].replaceAll("<",""));
            coord.put("lat",str[2].replaceAll("<",""));
            lhm.put("coords",coord);
            result.add(lhm);
        }
        return result;
    }
    
	public static void convertProfileToJson(String profile){

		String[] data = profile.split("\\|");
		jsonMap.put(keys[0],data[1]);
		jsonMap.put(keys[2],data[3]);
		if(data.length<6){
		    jsonMap.put(keys[keys.length-1],"");
		}else{
    		jsonMap.put(keys[keys.length-1],data[5]);
		}
		jsonMap.put(keys[1],process(data[2]));
		jsonMap.put(keys[3],locations(data[4]));
		JSONObject json = new JSONObject(jsonMap);
		System.out.println(json);
	}
	
    public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
            System.out.println("hello world");
        }
        */

        Scanner sc = new Scanner(System.in);
		String profileDetails = sc.nextLine();

		convertProfileToJson(profileDetails);
    }
}
