package com.lingkong.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
		List<String> list = FileUtils.readLines(new File("data.log"));
		System.out.println("t1=" + (System.currentTimeMillis() - st));
		for(String s: list){
			if(s.indexOf("2015-") > -1){
				String[] arr = StringUtils.split(s);
				String key = arr[0] + " " +StringUtils.substring(arr[1], 0,2);
				if(map.containsKey(key)){
					map.put(key, map.get(key)+1);
				}else{
					map.put(key, 1);
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
