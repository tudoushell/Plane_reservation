package objectfactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



public class ObjectFactory {
	static Map<String,Object> map=new HashMap<String,Object>();
	static{
		BufferedReader br=null;
		
		try {
			br=new BufferedReader(new FileReader("src/Object.properties"));
			String str=br.readLine();
			while(str!=null){
				String[] arr=str.split("=");
				Object o2= Class.forName(arr[1]).newInstance();
//				System.out.println(o2);
				map.put(arr[0].toLowerCase().trim(), o2);
				str=br.readLine();	
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Object getObject(String name){
		name=name.toLowerCase().trim();
		return map.get(name);
	}
	
//	
	
}
