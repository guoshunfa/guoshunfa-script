package com.panda.demo;

import com.panda.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Demo01 {
    public static void main(String[] args) {
        WebDriver driver = WebDriverUtils.init();
        driver.get("https://www.baidu.com");
        driver.findElement(new By.ById("kw")).sendKeys("pandacode");
//        WebElement su = driver.findElement(new By.ById("su"));
        driver.findElement(new By.ById("su")).click();
        driver.get(driver.getCurrentUrl());
        String url = driver.findElement(new By.ById("content_left")).findElement(new By.ById("1")).getDomAttribute("mu");
//        String url = driver.findElement(new By.ById("1")).getDomAttribute("mu");
        System.out.println("url: " + url);
        driver.close();
    }
}
