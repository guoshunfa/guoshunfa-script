#!/bin/sh
export CUR_DIR=$(dirname $0)
cd ${CUR_DIR}

main()
{
  jarfileName=$(find ../dist_lib | grep -E "jar$")
  projectName=$(echo ${jarfileName%-*} | awk -F '/' '{print $NF}')
  projectVersion=$(echo ${jarfileName%.*} | awk -F '-' '{print $NF}')
  echo "shutdown : $projectName"
  pidlist=`ps -ef|grep ${projectName}-${projectVersion}.jar|grep -v grep|awk '{print $2}'`
  if [ "$pidlist" = "" ]
    then
      echo "no $projectName pid alive !"
  else
    echo "$projectName pid list : $pidlist"
    kill -9 $pidlist
    echo "kill $projectName success $pidlist"
    echo "shutdown $projectName success"
  fi
}

main