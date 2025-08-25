package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.*;

public class ProductPage extends BasePage {
    private By name = By.xpath("(//h1[@class='_x_m-none _x_break-words _x_font-bold _x_text-5 _x_text-dark md:_x_text-10'])[2]");
    private By id = By.xpath("(//div[@class='_x_mb-3 _x_hidden md:_x_inline-block'])[2]");
    private By salePrice = By.xpath("(//div[@class='_x_mr-4 _x_flex _x_items-center'])[2]");
    private By regularPrice = By.xpath("//div[@class='_x_mr-4 _x_flex _x_items-center']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getName() {
        return getText(name);
    }

    public String getId() {
        return getText(id);
    }

    public String[] getPrices() {
        try {
            String priceText = getText(salePrice);
            return priceText.split("\n");
        } catch (Exception e) {
            return new String[]{getText(regularPrice)};
        }
    }
}
