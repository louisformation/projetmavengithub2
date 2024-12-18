#!/bin/bash
echo "Debut du script"
date
echo "Creation du container claculator"
docker run -d --name calculator --hostname calculator.gretadevops.com --net gretadevops.com -p 8080:8080 kbeber/calculator
sleep 10
response=$(curl -s http://localhostcalculator.gretadevops.com:8080/sum?a=5\&b=6)
if  [ "$response" = "11" ] ; then
  	echo "ok"
 else 
echo "response $response"
echo "pas ok"
fi 
echo "Script termine"
