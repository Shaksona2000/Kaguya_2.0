package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.CategoryPage;
import org.example.pages.ProductPage;
import org.example.utils.ExcelWriter;
import org.testng.annotations.Test;

import java.util.*;

public class ScrapTest extends BaseTest {

    @Test
    public void scrapeCategory() throws Exception {
        String categoryUrl = "https://extra.ge/catalog/silamaze-da-tavis-movla/344";
        String filePath = "C:\\Users\\sh.beridze\\Desktop\\scraped.xlsx";

        driver.get(categoryUrl);
        CategoryPage categoryPage = new CategoryPage(driver);
        int pages = categoryPage.getNumberOfPages();

        ExcelWriter writer = new ExcelWriter(filePath, "Products");

        int rowNum = 1;
        Set<String> visited = new HashSet<>();

        for (int i = 1; i <= pages; i++) {
            driver.get(categoryUrl + "?page=" + i);
            for (String url : categoryPage.getProductUrls()) {
                if (!visited.contains(url)) {
                    visited.add(url);
                    driver.get(url);
                    ProductPage product = new ProductPage(driver);

                    List<String> row = new ArrayList<>();
                    row.add(product.getName());
                    row.add(product.getId());

                    String[] prices = product.getPrices();
                    if (prices.length > 1) {
                        row.add(prices[1]);
                        row.add(prices[0]);
                        row.add(prices.length > 2 ? prices[2] : "");
                    } else {
                        row.add(prices[0]);
                        row.add("");
                        row.add("");
                    }

                    writer.writeRow(rowNum++, row);
                }
            }
        }
        writer.save();
    }
}
