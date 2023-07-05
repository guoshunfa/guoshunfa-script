# Java Springboot框架 Jar文件简单部署模版

Java 项目，SpringBoot框架，Jar文件 简单部署模版

## 目录介绍

```
- bin 脚本放置目录
- - start.sh 项目启动脚本
- - stop.sh 项目停止脚本

- config 配置项目录
- - application.properties springboot配置文件
- - log4j2.yml 日志配置文件

- dist_lib 项目主要jar放置目录

- log 日志文件存放位置
```

## 前期准备

1. 将自己的java项目进行打包，打包成jar文件。
2. 将打包之后的jar文件放入dist_lib目录下。
3. 想要个性化配置的，前往config目录下，针对配置文件进行调整。

## 启动/关闭服务

直接执行bin目录下的start.sh或stop.sh。