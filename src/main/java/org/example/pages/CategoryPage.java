package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.*;

import java.util.*;

public class CategoryPage extends BasePage {
    private By pager = By.xpath("//ul[@class='_x_list-unstyled _x_mt-10 _x_flex _x_items-center _x_justify-center lg:_x_mt-25']");
    private By productCards = By.xpath("//*[@class='_x_relative _x_flex _x_max-h-289 _x_w-full _x_flex-row _x_justify-between _x_border-b _x_border-dark-100 _x_pb-0 _x_pb-7 _x_pt-7 _x_text-black hover:_x_text-black sm:_x_flex-col sm:_x_p-5 sm:_x_py-17 md:_x_px-10 md:_x_py-9']");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfPages() {
        WebElement pagerElement = waitForVisible(pager);
        List<WebElement> buttons = pagerElement.findElements(By.xpath(".//li[@class='_x_ml-5 _x_flex _x_flex-col']"));
        return Integer.parseInt(buttons.get(buttons.size() - 1).getText());
    }

    public List<String> getProductUrls() {
        List<String> urls = new ArrayList<>();
        List<WebElement> products = driver.findElements(productCards);
        for (WebElement e : products) {
            try {
                urls.add(e.findElement(By.tagName("a")).getAttribute("href"));
            } catch (Exception ignored) {}
        }
        return urls;
    }
}
