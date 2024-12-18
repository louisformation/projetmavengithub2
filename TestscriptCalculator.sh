#!/bin/bash
echo "Debut du script"
date
echo "Suppression du container calculator"
docker rm -f calculator 2>/dev/null
echo "Creation du container calculator"
docker run -d --name calculator --hostname calculator.gretadevops.com --net gretadevops.com -p 8080:8080 kbeber/calculator  2>/dev/null
sleep 10
response=$(curl -s http://calculator.gretadevops.com:8080/sum?a=5\&b=6)
if  [ "$response" = "11" ] ; then
  	echo "ok"
 else 
echo "response $response"
echo "pas ok"
fi 
echo "Script termine"
