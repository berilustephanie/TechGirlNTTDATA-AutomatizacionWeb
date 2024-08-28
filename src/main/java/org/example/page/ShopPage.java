package org.example.page;

import org.openqa.selenium.By;

public class ShopPage {

    public static By cartPageTitle = By.cssSelector("div.card-block h1.h1");

    public static By cartTotal = By.cssSelector("span.product-price strong");

    public static By cartProductPrices = By.cssSelector("div.current-price span.price");

    public static By firstProduct = By.cssSelector("div.products .js-product:first-child a.product-thumbnail");

    public static By addToCartButton = By.cssSelector("button.add-to-cart");

    public static By getCategoryElement(String categoria) {
        return By.xpath("//li[@class='category']//a[contains(., '" + categoria + "')]");
    }

    public static By getSubCategoryElement(String subcategoria) {
        return By.xpath("//div[@id='subcategories']//a[@class='subcategory-name' and contains(text(), '" + subcategoria + "')]");
    }


}
