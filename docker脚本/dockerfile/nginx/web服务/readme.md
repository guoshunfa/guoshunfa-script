# 构建
docker build -t "panda-nginx" . --no-cache

# 运行
docker run -d --name panda-nginx-web -p 9992:80 --restart=always panda-nginx

# 删除旧容器
docker ps -a | grep panda-nginx-web | grep prod | awk '{print $1}' | xargs -I docker stop {} | xargs -I docker rm {}

# 删除旧镜像
docker images | grep -E panda-nginx-web | grep prod | awk '{print $3}' | uniq | xargs -I {} docker rmi --force {}
