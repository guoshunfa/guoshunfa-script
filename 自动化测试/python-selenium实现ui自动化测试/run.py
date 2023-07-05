# coding=utf-8

import unittest

# from testcase import * 
from testcase import test_baidu
from testcase import test_youdao

#构造测试集
# print(testcase)
# print(test_baidu)
# print(testcase.test_youdao)
suite = unittest.TestSuite()
suite.addTest(test_baidu.BaiduTest('test_baidu'))
suite.addTest(test_youdao.YoudaoTest('test_youdao'))

if __name__=='__main__':
    #执行测试
    runner = unittest.TextTestRunner()
    runner.run(suite)
