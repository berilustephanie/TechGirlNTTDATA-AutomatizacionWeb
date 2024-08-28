#language: es
@testfeature
Característica: Comprar productos en la tienda
  Yo, como usuario
  Quiero, poder navegar por la tienda, agregar productos al carrito y completar la compra
  Para comprar los productos que deseo

  @test
  Escenario: Compra de productos en la categoría "Clothes" y subcategoría "Men"
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "berilu.castaneda@gmail.com" y clave "Passw00rd1!"
    Cuando navego a la categoría "Clothes" y subcategoría "Men"
    Y agrego 2 unidades del primer producto al carrito
    Entonces valido en el popup la confirmación del producto agregado con la cantidad 2
    Y valido en el popup que el monto total sea calculado correctamente
    Cuando finalizo la compra
    Entonces valido el título de la página del carrito
    Y vuelvo a validar el cálculo de precios en el carrito 2

  @test
  Escenario: Login Errado
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "berilu.castaneda@gmail.com" y clave "Passw00rd2!"

  @test
  Escenario: Compra de productos en la categoría "Autos"
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "berilu.castaneda@gmail.com" y clave "Passw00rd1!"
    Cuando navego a la categoría "Autos" y subcategoría "Motores"