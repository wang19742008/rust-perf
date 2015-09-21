# rust-perf

背景：
写了个日志分析的程序，用于验证rust,go,java的性能差异，基本逻辑是，读一个日志文件，总行数1500，大小：150k，对每一行文本处理逻辑如下：
1. 只处理文本中包含 “2015-”的数据
2. 对文本按空格，切分成数组
3. 取数据中数据组合成 key，统计出现的次数。

测试环境：
笔记本，windows，cpu:i5，内存：8G，disk:SSD
测试结果：
java: 90ms
rust: 8ms
go:3ms

java慢我能理解，但这个差距也出乎我的预料，但go比rust也快了1倍，谁能看出问题吗？
