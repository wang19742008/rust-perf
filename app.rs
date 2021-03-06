use std::path::Path;
use std::fs::File;
use std::error::Error;
use std::io::prelude::*;
use std::io::BufReader;
use std::collections::HashMap;
extern crate time;


fn main() {
	let st = time::get_time();
	let path = Path::new("data.log");
	//println!("{:?}", path.display());
	let f = match File::open(&path) {
		Err(why) => panic!("fail{}", Error::description(&why)),
		Ok(file) => file
	};

	let mut count = 0u32;
	let mut map =  HashMap::new();

	let b = BufReader::new(f);
	/*
	for line in b.lines() {
	    let s = line.unwrap();
	    if s.find("2015-").is_some() {
	    	count +=1 ;
	    	let v : Vec<&str> = s.split_whitespace().collect();
	    	let mut k = v[0].to_string();
	    	k = k + " " + &v[1][.. 2];
	    	
	    	match map.get_mut(&k) {
	    		Some(&mut c) => map.insert(k, c+1),
	    		None => map.insert(k,1),
	    	};
	    }
	}
	 */
	
	for line in b.lines() {
        let s = line.unwrap();
        if s.starts_with("2015-") {
            count += 1 ;

            let mut sp = s.split_whitespace();
            // 不需要使用 collect 来遍历所有的数据并分配内存
            let key = match (sp.next(), sp.next()) {
                (Some(t1), Some(t2)) => {
                    format!("{} {}", t1, &t2[.. 2])
                },
                _ => unreachable!(),
            };

            // 使用 entry
            *map.entry(key).or_insert(0) += 1;
        }
    }
   
	for (a,b) in &map {
		println!("{},{}", a,b);
	}
	println!("total count : {:?}", count);

	let et = time::get_time();
	println!("{}", et-st);
}
