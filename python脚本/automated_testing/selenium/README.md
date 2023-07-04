# python-selenium-unittest

## 介绍
python3.9+selenium+unittest 实现ui自动化测试

## 软件目录
> 软件目录说明

```
|   README.md   项目指导文件
|   run.py      项目主要启动文件
|   demo        测试用例demo
|   testcase    测试用例存储文件夹
|   utils       工具包文件
|---|   log.py     日志打印工具包
|   result      测试用例执行结果
```


## 安装教程

1.  pip install selenium
2.  目前使用的是google chrome浏览器，需要下载跟自身浏览器版本相同的chromedriver，[下载网址](http://chromedriver.storage.googleapis.com/index.html)，将下载的文件替换原有的chromedriver。

## 使用教程

1. demo文件夹中存在几个案例，可供学习或cv，如不需要可以删除。
2. 根据自身需求，可以在testcase文件夹中创建多个测试用例py文件，并且在run.py文件中将文件引入。
3. 最后执行python run.py，即可运行测试用例。