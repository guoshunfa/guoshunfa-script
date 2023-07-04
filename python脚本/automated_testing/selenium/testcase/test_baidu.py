# coding=utf-8

from selenium import webdriver
import unittest, time
from utils.log import logger

class BaiduTest(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome("./chromedriver")
        self.driver.implicitly_wait(30) #隐性等待时间为30秒
        self.base_url = "https://www.baidu.com"
    
    def test_baidu(self):
        driver = self.driver
        driver.get(self.base_url + "/")
        driver.find_element_by_id("kw").clear()
        logger.info("Input search str")#Print input unittest
        driver.find_element_by_id("kw").send_keys("unittest")
        driver.find_element_by_id("su").click()
        time.sleep(3)
        title=driver.title
        self.assertEqual(title, "unittest_百度搜索") 

    def tearDown(self):
        self.driver.quit()

if __name__ == "__main__":
    unittest.main()
