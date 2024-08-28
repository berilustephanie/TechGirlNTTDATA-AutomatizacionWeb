package org.example.steps;

import org.example.page.ShopPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShopSteps {

    private WebDriver driver;

    // Constructor
    public ShopSteps(WebDriver driver) {
        this.driver = driver;
    }

    public boolean navigateToCategory(String categoria) {
        List<WebElement> categories = driver.findElements(By.cssSelector("ul#top-menu > li.category > a"));

        boolean categoryFound = false;

        for (WebElement categoryElement : categories) {
            String categoryText = categoryElement.getText().trim();
            if (categoryText.equalsIgnoreCase(categoria)) {
                categoryElement.click();
                categoryFound = true;
                break;
            }
        }
        return categoryFound;
    }

    public void navigateToSubCategory(String subcategoria) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement subCategoryElement = wait.until(ExpectedConditions.presenceOfElementLocated(ShopPage.getSubCategoryElement(subcategoria)));
            subCategoryElement.click();
        } catch (TimeoutException | NoSuchElementException e) {
            throw new AssertionError("La subcategoría '" + subcategoria + "' no existe.", e);
        }
    }

    public void addFirstProductToCart(int cantidad) {
        selectFirstProduct();
        WebElement incrementButton = driver.findElement(By.cssSelector("button.bootstrap-touchspin-up"));
        for (int i = 1; i < cantidad; i++) {
            incrementButton.click();
        }
    }

    public boolean isProductAddedConfirmationVisible(int expectedQuantity) {
        WebElement addToCartButton = driver.findElement(ShopPage.addToCartButton);
        addToCartButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement modalContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("blockcart-modal")));

        WebElement modalContentBody = modalContainer.findElement(By.xpath(".//div[@class='modal-body']"));

        WebElement quantityElement = modalContentBody.findElement(By.xpath(".//span[@class='product-quantity']/strong"));

        wait.until(ExpectedConditions.visibilityOf(quantityElement));

        int actualQuantity = Integer.parseInt(quantityElement.getText().trim());

        return actualQuantity == expectedQuantity;
    }

    public double getPopupTotal() {
        WebElement totalElement = driver.findElement(By.cssSelector(".product-total .value"));
        String totalText = totalElement.getText();
        String cleanedTotalText = totalText.replaceAll("S/\\s*", "").trim();
        return Double.parseDouble(cleanedTotalText);
    }

    public double calculateTotal() {
        WebElement priceElement = driver.findElement(By.cssSelector(".product-price"));
        String priceText = priceElement.getText();
        String cleanedPriceText = priceText.replaceAll("S/\\s*", "").replaceAll("\\s*\\d+%\\s*DE\\s*DESCUENTO", "").trim();
        double unitPrice = Double.parseDouble(cleanedPriceText);
        WebElement quantityElement = driver.findElement(By.cssSelector(".product-quantity strong"));
        int quantity = Integer.parseInt(quantityElement.getText().trim());
        return unitPrice * quantity;
    }

    public void completePurchase() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modalContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("blockcart-modal")));
        WebElement completePurchaseButton = modalContainer.findElement(By.cssSelector("a.btn.btn-primary"));
        wait.until(ExpectedConditions.elementToBeClickable(completePurchaseButton)).click();
    }

    public String getCartPageTitle() {
        return driver.findElement(ShopPage.cartPageTitle).getText();
    }

    public double calculateCartTotal() {
        List<WebElement> prices = driver.findElements(ShopPage.cartProductPrices);
        double total = 0;
        for (WebElement price : prices) {
            String priceText = price.getText().replace("S/ ", "").trim();
            total += Double.parseDouble(priceText);
        }
        return total;
    }

    public double getCartTotal() {
        String totalText = driver.findElement(ShopPage.cartTotal).getText();
        String priceText = totalText.replace("S/ ", "").trim();
        return Double.parseDouble(priceText);
    }

    public void selectFirstProduct() {
        WebElement firstProduct = driver.findElement(ShopPage.firstProduct);
        firstProduct.click();
    }

}
