package com.lingkong.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		long st = System.currentTimeMillis();
		Map<String,Integer> map = new HashMap<String,Integer>();
		File srcFile = new File("data.log");
//		List<String> list = FileUtils.readLines(srcFile);
//		System.out.println("t1=" + (System.currentTimeMillis() - st));
//		for(String s: list){
//			if(s.indexOf("2015-") > -1){
//				String[] arr = StringUtils.split(s);
//				String key = arr[0] + " " +StringUtils.substring(arr[1], 0,2);
//				if(map.containsKey(key)){
//					map.put(key, map.get(key)+1);
//				}else{
//					map.put(key, 1);
//				}
//			}
//		}
		
		BufferedReader reader = null;
		try {
            reader = new BufferedReader(new FileReader(srcFile));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
            	if(tempString.indexOf("2015-") > -1){
    				String[] arr = StringUtils.split(tempString);
    				String key = arr[0] + " " +StringUtils.substring(arr[1], 0,2);
    				if(map.containsKey(key)){
    					map.put(key, map.get(key)+1);
    				}else{
    					map.put(key, 1);
    				}
    			}
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		
		
		System.out.println("t2=" + (System.currentTimeMillis() - st));
		for(Entry<String, Integer>  e : map.entrySet()){
			System.out.println(e.getKey() + "," + e.getValue());
		}
		System.out.println("time:" + (System.currentTimeMillis()-st));
		
	}
}
