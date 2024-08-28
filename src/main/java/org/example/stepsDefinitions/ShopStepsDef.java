package org.example.stepsDefinitions;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.example.steps.LoginSteps;
import org.example.steps.ShopSteps;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static org.example.core.DriverManager.getDriver;
import static org.example.core.DriverManager.screenShot;


public class ShopStepsDef {

    private WebDriver driver;
    private boolean categoryExists = true;
    private ShopSteps shopSteps(WebDriver driver){
        return new ShopSteps(driver);
    }

    @Dado("estoy en la página de la tienda")
    public void estoy_en_la_página_de_la_tienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe/iniciar-sesion?back=https%3A%2F%2Fqalab.bensg.com%2Fstore%2Fpe%2F");
        screenShot();
    }

    @Y("me logueo con mi usuario {string} y clave {string}")
    public void me_logueo_con_mi_usuario_y_clave(String user, String password) {
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.typeUser(user);
        loginSteps.typePassword(password);
        loginSteps.login();
        screenShot();
    }

    @Cuando("navego a la categoría {string} y subcategoría {string}")
    public void navegoALaCategoríaYSubcategoría(String categoria, String subcategoria) {
        categoryExists = shopSteps(driver).navigateToCategory(categoria);
        if (categoryExists) {
            shopSteps(driver).navigateToSubCategory(subcategoria);
        }
        screenShot();
    }

    @Y("agrego {int} unidades del primer producto al carrito")
    public void agrego_unidades_del_primer_producto_al_carrito(int cantidad) {
        shopSteps(driver).addFirstProductToCart(cantidad);
        screenShot();
    }

    @Entonces("valido en el popup la confirmación del producto agregado con la cantidad {int}")
    public void valido_en_el_popup_la_confirmación_del_producto_agregado(int expectedQuantity ) {
        boolean isProductAdded = shopSteps(driver).isProductAddedConfirmationVisible(expectedQuantity );
        screenShot();
        Assertions.assertTrue(isProductAdded, "La confirmación del producto agregado no es visible");
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void valido_en_el_popup_que_el_monto_total_sea_calculado_correctamente() {
        double calculatedTotal = shopSteps(driver).calculateTotal();
        double popupTotal = shopSteps(driver).getPopupTotal();
        screenShot();
        Assertions.assertEquals(calculatedTotal, popupTotal, "El monto total en el popup no es correcto");
    }

    @Cuando("finalizo la compra")
    public void finalizo_la_compra() {
        shopSteps(driver).completePurchase();
        screenShot();
    }

    @Entonces("valido el título de la página del carrito")
    public void validoElTítuloDeLaPáginaDelCarrito() {
        String title = shopSteps(driver).getCartPageTitle();
        screenShot();
        Assertions.assertEquals("CARRITO", title, "El título de la página del carrito no es correcto");
    }

    @Y("vuelvo a validar el cálculo de precios en el carrito {int}")
    public void vuelvoAValidarElCálculoDePreciosEnElCarrito(int cantidad) {
        double calculatedTotal = shopSteps(driver).calculateCartTotal();
        calculatedTotal *= cantidad;
        double cartTotal = shopSteps(driver).getCartTotal();
        screenShot();
        Assertions.assertEquals(calculatedTotal, cartTotal, "El cálculo de precios en el carrito no es correcto");
    }

}
