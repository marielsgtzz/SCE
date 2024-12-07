# Vinilos Vintach

En este documento se pueden ver los detalles de los 3 web services principales de este proyecto: [WS_Musica_Pedidos](#ws_musica_pedidos), [WS_Musica_Creditos](#ws_musica_credito) y [WS_Musica_Envio](#WS_Musica_Envio).

Los web services utilizan entendidades y fachadas. Las **entidades** son las representaciones de los datos persistentes en las bases de datos.

- [SCE](#sce)
  - [Indice](#indice)
  - [WS_Musica_Pedidos](#ws_musica_pedidos)
    - [WSMusicaPedidos.java](#wsmusicapedidosjava)
      - [`altaPedido`](#altapedido)
      - [`montoPedido`](#montopedido)
      - [`procesoDeCompra`](#procesodecompra)
      - [`clteDelPedido`](#cltedelpedido)
      - [`catalogoCltes`](#catalogocltes)
      - [`catalogoProds`](#catalogoprods)
  - [WS_Musica_Credito](#ws_musica_credito)
    - [WSMusicaCredito.java](#wsmusicacreditojava)
      - [`autoriza`](#autoriza)
      - [`autoriza_double`](#autoriza_double)
      - [Métodos CRUD](#métodos-crud)
    - [Entidades](#entidades)
      - [**ExcepNoCredito**](#excepnocredito)
      - [**ExcepNoExisteClte**](#excepnoexisteclte)
    - [Fachadas](#fachadas)
      - [CreditoFacade](#creditofacade)
  - [WS_Musica_Envio](#ws_musica_envio)

## WS_Musica_Pedidos

### WSMusicaPedidos.java

El archivo `WSMusicaPedidos.java` es el núcleo del sistema de la tienda Vinilos Vintach. Su propósito principal es exponer un conjunto de servicios web (Web Services) que permiten interactuar con las funciones clave del sistema para realizar pedidos, consultar catálogos de productos y clientes, y gestionar información sobre los pedidos realizados.

#### `altaPedido`

**Input**: `int` ID del cliente, `List<ClsItem>` lista de productos con las cantidades solicitadas <br>
**Salida**: `int` Número de pedido generado (0 si no habían cantidades disponibles.) <br><br>
**Descripción**: Registra un nuevo pedido para un cliente, antes de procesarlo se verifica que haya suficientes existencias disponibles en el almacén y de ser cierto, actualiza las cantidades en el inventario, si no hay suficientes existencias el pedido no se genera.

#### `montoPedido`

**Input**: `int` ID del pedido <br>
**Salida**: `BigDecimal` Monto del pedido <br><br>
**Descripción**: Recupera el monto total de un pedido en específico.
_qué pasa si se pide el monto de un pedido inexistente_

#### `procesoDeCompra`

#### `clteDelPedido`

**Input**: `int` ID del pedido <br>
**Salida**: `Customer` Cliente del pedido <br><br>
**Descripción**:Retorna el cliente asociado al pedido.

#### `catalogoCltes`

**Salida**: `List<entidades.Customer>` Lista de clientes <br><br>
**Descripción**: Retorna una lista de todos los clientes registrados en la tienda.

#### `catalogoProds`

**Salida**: `List<entidades.Product>` Lista de productos <br><br>
**Descripción**: Retorna una lista de todos los productos disponibles con sus detalles(nombre, descripción, precio y categoría).

## WS_Musica_Credito

### WSMusicaCredito.java

Este Web Service (WS) permite realizar operaciones CRUD básicas sobre créditos, además de exponer servicios específicos para autorizar créditos y actualizar información relevante.

#### `autoriza`

**Input**: `int` ID del cliente, `BigDecimal` monto solicitado
**Salida**:`boolean` Indica si el crédito fue autorizado.
**Descripción**:
Este método verifica si el cliente tiene suficiente crédito para cubrir el monto solicitado. Si el cliente no existe o no tiene crédito suficiente, lanza las excepciones `ExcepNoExisteClte` o `ExcepNoCredito`.

#### `autoriza_double`

**Input**: `int` ID del cliente, `double` monto solicitado
**Salida**: `boolean` Indica si el crédito fue autorizado.
**Descripción**: Es una variante del método `autoriza`, pero recibe el monto como un valor de tipo `double`, convirtiéndolo internamente a `BigDecimal`.

#### Métodos CRUD

- **`create`**: Registra un nuevo cliente con un crédito en la base de datos.
  <br> **Input**: Credito entidad con los datos del crédito

- **`edit`**: Actualiza los datos de un cliente existente.
  <br> **Input**: Credito entidad con los datos actualizados del crédito en la base de datos.

- **`remove`**: Elimina un cliente y su información de crédito.
  <br> **Input**: Credito entidad del crédito a eliminar <br>

- **`find`**: Recupera los datos de un cliente específico por su ID.
  <br> **Input**: Object ID del crédito <br>
  **Salida**: Credito entidad encontrada o null si no existe

- **`findAll`**: Devuelve una lista de todos los clientes registrados.
  <br> **Salida**: List<Credito> Lista de todos los créditos registrados

- **`findRange`**: Recupera un subconjunto de clientes basado en un rango especificado.
  <br> **Input**: int[] rango con los índices de inicio y fin <br>
  **Salida**: List<Credito> Lista de créditos dentro del rango especificado

- **`count`**: Devuelve la cantidad total de clientes registrados.
  <br> **Salida**: int Cantidad total de créditos registrados <br><br>

### Entidades

Representan los datos de un cliente y su información de crédito. Está mapeada a la tabla `CREDITO` en la base de datos.

**Atributos**:

- `id`: Identificador único del cliente.
- `name`: Nombre del cliente.
- `email`: Correo electrónico del cliente.
- `phone`: Teléfono del cliente.
- `address`: Dirección del cliente.
- `cityRegion`: Región o ciudad del cliente.
- `ccNumber`: Número de tarjeta de crédito asociada.
- `credito`: Monto disponible de crédito del cliente.

**Named Queries**:

- `Credito.findAll`: Recupera todos los registros de la tabla `CREDITO`.
- `Credito.findById`: Busca un cliente por su ID.
- `Credito.findByName`: Busca un cliente por su nombre.

#### **ExcepNoCredito**

Maneja casos en los que el cliente no tiene crédito suficiente para autorizar una operación.

**Atributos**:

- `id_clte`: Identificador del cliente relacionado con la excepción.

**Funciones**:

- Devuelve un mensaje indicando que el cliente no tiene crédito.

#### **ExcepNoExisteClte**

Maneja casos en los que el cliente no existe en la base de datos.

**Atributos**:

- `id_clte`: Identificador del cliente no encontrado.

**Funciones**:

- Devuelve un mensaje indicando que el cliente no está registrado.

### Fachadas

#### CreditoFacade

Actúa como intermediario entre el Web Service y la base de datos para realizar operaciones sobre la entidad `Credito`.

**Funciones**:

- **CRUD**:
  - `create`: Crea un nuevo registro en la tabla `CREDITO`.
  - `edit`: Modifica un registro existente.
  - `remove`: Elimina un registro de la tabla.
  - `find`: Recupera un registro específico por su ID.
  - `findAll`: Recupera todos los registros.
  - `findRange`: Obtiene un subconjunto de registros basado en un rango.
  - `count`: Devuelve el número total de registros.
- **Métodos adicionales**:
  - `actualizaCredito`:
    - **Input**: ID del cliente, monto solicitado.
    - **Salida**: `boolean` indicando si la actualización fue exitosa.
    - **Descripción**: Verifica si el cliente tiene suficiente crédito y actualiza el monto disponible en caso de autorización.

## WS_Musica_Envio
