package main

import (
    "os"
    "fmt"
    "strings"
    "bufio"
    "io"
    "time"
)
func main() {
	st := time.Now().UnixNano()
    userFile := "data.log"
    fin,err := os.Open(userFile)
    defer fin.Close()
    if err != nil {
        fmt.Println(userFile,err)
        return
    }
    m := map[string]int{}
    inputReader := bufio.NewReader(fin)
    lineCounter := 0
    for{
        str, readerError := inputReader.ReadString('\n')
        if readerError == io.EOF {
        	break
        }

        lineCounter++
        if strings.Index(str,"2015-")>-1 {
        	arr := strings.Split(str, " ");
        	k := arr[0] + " " + arr[1][0:2]
        	v,ok := m[k]
        	if ok {
        		m[k] = v+1;
        		//fmt.Printf("%s\n", "true")
    		}else{
    			m[k] = 1;
    			//fmt.Printf("%s\n", "false")
    		}
        	//fmt.Printf("%s\n", k)
        }
    }

    for key, value := range m {
	  fmt.Printf("Key: %s  Value: %d\n", key, value)
	}

	fmt.Printf("time: %d\n", (time.Now().UnixNano() - st)/1000000)
}
