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

	let mut b = BufReader::new(f);
    let mut s = String::new();
    loop {
        s.clear();
        b.read_line(&mut s).unwrap();
        if s.is_empty() { break; }

        // ~ s.starts_with(..)???
	    if s.find("2015-").is_some() {
	    	count += 1;
	    	let mut v = s.split_whitespace();
	    	let k = format!("{} {}",
                            v.next().unwrap(),
                            &v.next().unwrap()[.. 2]);
	    	*map.entry(k).or_insert(0) += 1;
	    }
	}
	for (a,b) in &map {
		println!("{},{}", a,b);
	}
	println!("total count : {:?}", count);

	let et = time::get_time();
	println!("{}", et-st);
}
