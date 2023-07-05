#!/bin/sh
export CUR_DIR=$(
  cd $(dirname $0)
  echo $(/bin/pwd)
)
cd ${CUR_DIR}

main()
{
  #取上级目录
  tmpServicePath=${CUR_DIR%/*}
  logDir="${tmpServicePath}/log"
  if [[ ! -d ${logDir} ]]; then
    mkdir ${logDir}
  fi
  if [[ ! -f ${logDir}/all.log ]]; then
    touch ${logDir}/all.log
  fi
  jarfileName=$(find ../dist_lib | grep -E "jar$")
  projectName=$(echo ${jarfileName%-*} | awk -F '/' '{print $NF}')
  projectVersion=$(echo ${jarfileName%.*} | awk -F '-' '{print $NF}')
  executeDir=${CUR_DIR}
  echo "executeDir : $executeDir"
  cd ${executeDir}
  timezone=$(awk -F '=' '{if(/server.time-zone=/){print $2}}' ../config/application.properties)
  echo "startup : $projectName"
  echo "step 1 : shutdown alive $projectName server"
  pidlist=`ps -ef|grep ${projectName}-${projectVersion}.jar|grep -v grep|awk '{print $2}'`
  if [ "$pidlist" = "" ]
          then
                  echo "no $projectName server pid alive !"
  else
          echo "$projectName server pid list : $pidlist"
          kill -9 $pidlist
          echo "kill $projectName server success $pidlist"
  fi
  echo "step 2 : startup $projectName server"
  cd ../dist_lib
  if [[ "${timezone}" != "" ]];then
    java -jar -Xms2048m -Xmx2048m -Xss1024k -XX:SurvivorRatio=8 -XX:NewSize=1024M -Duser.timezone=${timezone} -Dclient.encoding.override=UTF-8 -Dfile.encoding=UTF-8 -Dspring.config.location=./../config/application.properties -Dlog4j.configurationFile=./../config/log4j2.yml ./${projectName}-${projectVersion}.jar > ../log/all.log 2>&1 &
    echo $! > ../bin/service.pid
  else
    java -jar -Xms2048m -Xmx2048m -Xss1024k -XX:SurvivorRatio=8 -XX:NewSize=1024M -Dclient.encoding.override=UTF-8 -Dfile.encoding=UTF-8 -Dspring.config.location=./../config/application.properties -Dlog4j.configurationFile=./../config/log4j2.yml ./${projectName}-${projectVersion}.jar > ../log/all.log 2>&1 &
    echo $! > ../bin/service.pid
  fi
  echo "startup $projectName server success"
}

main
