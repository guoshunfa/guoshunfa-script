# coding=utf-8

from selenium import webdriver
import unittest, time

class YoudaoTest(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome("./chromedriver")
        self.driver.implicitly_wait(30) #隐性等待时间为30秒
        self.base_url = "http://fanyi.baidu.com/" # baidu翻译
    
    def test_youdao(self):
        driver = self.driver
        driver.get(self.base_url)
        driver.find_element_by_id("baidu_translate_input").clear()
        driver.find_element_by_id("baidu_translate_input").send_keys("你好")
        #driver.find_element_by_id("translate_button").click()
        time.sleep(3)
        page_source=driver.page_source
        self.assertIn( "hello",page_source) 

    def tearDown(self):
        self.driver.quit()

if __name__ == "__main__":
    unittest.main()
