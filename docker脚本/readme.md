# docker 笔记

```shell
# 查看docker版本。
docker -v
docker version
# 查看docker系统信息
docker info
# 搜索镜像
docker search 镜像
# 获取镜像
docker pull 镜像仓库地址
# 上传镜像
docker push 镜像仓库地址
# 查看镜像的创建历史
docker history 镜像仓库地址
# 运行容器
# -name 定义一个容器的名字
# -d 标识是让 docker 容器的后台运行。
# -p 标识通知 docker 将容器内部使用的网络端口映射到我们使用的主机上。
docker run --name nginx -p 8080:80 nginx
# 查询容器内部ip地址
docker inspect 容器ID/容器名 | grep IPAddress
# 列出容器 -- 仅运行的容器
docker ps
# 列出容器 -- 包含停止的容器
docker ps -a
# 查看当前本地所有的镜像
docker images
# 停止容器
docker stop 容器ID/容器名
# 开始容器
docker start 容器ID/容器名
# 重启容器
docker restart 容器ID/容器名
# 杀掉一个运行中的容器
docker kill -s KILL 容器ID/容器名
# 删除容器
docker rm 容器ID/容器名
# 删除一个或多个容器
docker rm -f xx,xx2
# 删除镜像【顺序：停止镜像里的容器，再删除容器，最后再删除镜像】
docker rmi 镜像id/镜像名
# 列出所有的容器 ID
docker ps -aq
# 停止所有的容器
docker stop $(docker ps -aq)
# 删除所有的容器
docker rm $(docker ps -aq)
# 删除所有的镜像
docker rmi $(docker images -q)
# 停止并删除指定容器
docker ps -a | grep 容器ID/容器名 | awk '{print $1}' \ | xargs -i docker stop {} | xargs -i docker rm {}
# 删除镜像
docker images | grep -E '镜像id/镜像名' | awk '{print $3}' \ | uniq | xargs -I {} docker rmi --force {}
# ex: 删除镜像 `nginx:latest`
docker images | grep -E 'nginx' | grep 'latest' | awk '{print $3}' \ | uniq | xargs -I {} docker rmi --force {}
# 删除所有停止的容器
docker container prune
# 删除所有部使用的镜像
docker image prune --force --all
docker image prune --f --a
# 限制容器内存 -m
docker run --name nginx -d -p 8080:80 -m 100m nginx
# 查看容器运行内存信息
docker stats nginx
# 进入容器
docker exec -it 容器ID/容器名 /bin/bash
# 以交互模式启动一个容器，在容器内执行/bin/bash命令
docker run -i -t 容器ID/容器名 /bin/bash
# 查看容器日志 -t: 显示时间戳
docker logs -f -t 容器ID/容器名
docker logs -fn10 -t 容器ID/容器名
# 构建镜像
# 用法：docker build -t 镜像名称 .
docker build -t docker_demo .
2. docker 启动jar包
2.1. 编写dockerFile文件
# 引用哪个镜像，没有镜像则pull
FROM centos:7
# 为Dockerfile中所有RUN、CMD、ENTRYPOINT、COPY和ADD指令设定工作目录
WORKDIR /usr
# 执行命令(这里创建了一个目录)
RUN mkdir /usr/local/java
# 和copy一样，复制文件到指定目录，但是copy不能解压，add自动解压
ADD jdk-8u111-linux-x64.tar.gz /usr/local/java
# 重命名(不知道文件名可以现在宿主机解压后看一下)
RUN ln -s /usr/local/java/jdk1.8.0_111 /usr/local/java/jdk 
# 设置环境变量 
ENV JAVA_HOME /usr/local/java/jdk 
ENV JRE_HOME ${JAVA_HOME}/jre 
ENV CLASSPATH .:${JAVA_HOME}/lib:${JRE_HOME}/lib 
ENV PATH ${JAVA_HOME}/bin:$PATH 

# 创建一个目录
RUN mkdir /demo
# 将jar包copy到指定目录
ADD job.jar /demo/app.jar
# 启动命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Xmx512m","-Xms512m","/demo/app.jar"]
```