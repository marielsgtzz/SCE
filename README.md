# SCE

## WS_Musica_Pedidos

### WSMusicaPedidos.java

El archivo `WSMusicaPedidos.java` es el núcleo del sistema de la tienda Vinilos Vintach. Su propósito principal es exponer un conjunto de servicios web (Web Services) que permiten interactuar con las funciones clave del sistema para realizar pedidos, consultar catálogos de productos y clientes, y gestionar información sobre los pedidos realizados.

#### `altaPedido`

**Input**: `int` ID del cliente, `List<ClsItem>` lista de productos con las cantidades solicitadas <br>
**Salida**: `int` Número de pedido generado (0 si no habían cantidades disponibles.) <br><br>
Registra un nuevo pedido para un cliente, antes de procesarlo se verifica que haya suficientes existencias disponibles en el almacén y de ser cierto, actualiza las cantidades en el inventario, si no hay suficientes existencias el pedido no se genera.

#### `montoPedido`

**Input**: `int` ID del pedido <br>
**Salida**: `BigDecimal` Monto del pedido <br><br>
Recupera el monto total de un pedido en específico.
_qué pasa si se pide el monto de un pedido inexistente_

#### `clteDelPedido`

**Input**: `int` ID del pedido <br>
**Salida**: `Customer` Cliente del pedido <br><br>
Retorna el cliente asociado al pedido.

#### `catalogoCltes`

**Salida**: `List<entidades.Customer>` Lista de clientes <br><br>
Retorna una lista de todos los clientes registrados en la tienda.

#### `catalogoProds`

**Salida**: `List<entidades.Product>` Lista de productos <br><br>
Retorna una lista de todos los productos disponibles con sus detalles(nombre, descripción, precio y categoría).
