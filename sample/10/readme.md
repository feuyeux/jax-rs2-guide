# Java Restful Web Service使用指南 #
## 10 JAX-RS调优 ##

[INDEX](/README.md)

ab - Apache HTTP server benchmarking tool

Last-Modified test:

    erichan@erichan-HP-Compaq-6520s:~$ ab -n1000 -c100 -H 'If-Modifed-Since:' http://192.168.0.163:8080/simple-service10/webapi/rest/last_modified?userId=eric 

Result:

	Concurrency Level:      100
	Time taken for tests:   6.320 seconds
	Complete requests:      1000
	Failed requests:        0
	Write errors:           0
	Total transferred:      5128000 bytes
	HTML transferred:       4889000 bytes
	Requests per second:    158.23 [#/sec] (mean)
	Time per request:       632.006 [ms] (mean)
	Time per request:       6.320 [ms] (mean, across all concurrent requests)
	Transfer rate:          792.37 [Kbytes/sec] received
	
	Percentage of the requests served within a certain time (ms)
	  50%    563
	  66%    591
	  75%    608
	  80%    627
	  90%    848
	  95%   1767
	  98%   2007
	  99%   2082
	 100%   2175 (longest request)


Basic Test:

	erichan@erichan-HP-Compaq-6520s:~$ ab -n1000 -c100 http://192.168.0.163:8080/simple-service10/webapi/rest

Result:

	Concurrency Level:      100
	Time taken for tests:   7.626 seconds
	Complete requests:      1000
	Failed requests:        0
	Write errors:           0
	Total transferred:      5039000 bytes
	HTML transferred:       4889000 bytes
	Requests per second:    131.13 [#/sec] (mean)
	Time per request:       762.574 [ms] (mean)
	Time per request:       7.626 [ms] (mean, across all concurrent requests)
	Transfer rate:          645.30 [Kbytes/sec] received
	
	Percentage of the requests served within a certain time (ms)
	  50%    543
	  66%    613
	  75%    638
	  80%    723
	  90%   1300
	  95%   3365
	  98%   3487
	  99%   3543
	 100%   3580 (longest request)