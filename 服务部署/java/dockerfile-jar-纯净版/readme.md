## 将jar包放入本目录

## 打包镜像 -f:指定Dockerfile文件路径 --no-cache:构建镜像时不使用缓存
docker build -t "my-java-app" . --no-cache

## 运行
docker run -d -p 9991:9991 --name springboot my-java-app

## 删除旧容器
docker rm -f springboot

## 删除旧镜像
docker rmi -f my-java-app