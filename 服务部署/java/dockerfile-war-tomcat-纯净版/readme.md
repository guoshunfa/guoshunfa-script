1. 将war包拷贝到当前目录，并且改名为app.war

2. 打包镜像 -f:指定Dockerfile文件路径 --no-cache:构建镜像时不使用缓存

`docker build -t "my-tomcat-war" . --no-cache`

3. 启动容器

`docker run -d -p 8871:8080 --name tomcat my-tomcat-war`

### 删除旧容器
docker rm -f tomcat

### 删除旧镜像
docker rmi -f my-tomcat-war