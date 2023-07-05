package com.panda.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;

/**
 * WebDriver工具类
 */
public class WebDriverUtils {

    public static WebDriver init() {
        //参数配置
        String path = WebDriverUtils.class.getClassLoader().getResource("").getPath();
        System.setProperty("webdriver.chrome.driver", path + "chromedriver");
        WebDriver driver;
        ChromeOptions option = new ChromeOptions();
        option.addArguments("no-sandbox");//禁用沙盒
        //通过ChromeOptions的setExperimentalOption方法，传下面两个参数来禁止掉谷歌受自动化控制的信息栏
        option.setExperimentalOption("useAutomationExtension", false);
        option.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(option);
        return driver;
    }

}
