package pojo_pedidos;

import java.util.ArrayList;
import java.util.List;

public class POJO_Pedidos {
    long quienSoy;
    String host = null;

    List<wsmusicapedidos.Customer> listaCltes = new ArrayList<>();
    List<wsmusicapedidos.Product> listaProds = new ArrayList<>();

    int num_cltes;
    int num_prods;

    public void prepara(long quienSoy, String host) {
        this.quienSoy = quienSoy;
        this.host = host;

        listaCltes = catalogoCltes();
        listaProds = catalogoProds();
        num_cltes = listaCltes.size();
        num_prods = listaProds.size();
    }

    public void solicitaServicio(int vez) {
        java.util.List<wsmusicapedidos.ClsItem> listaIt = new java.util.ArrayList<>();
        java.util.List<wsmusicapedidos.Backorder> backorderList = new java.util.ArrayList<>();

        int numPedido;
        int idClte;
        int numItems;
        int idProd, cantidad;
        int queClte, queProd = 0;

        wsmusicapedidos.ClsItem item;

        queClte = (int) (num_cltes * Math.random());
        idClte = listaCltes.get(queClte).getId();

        numItems = (int) (1.0 + 10.0 * Math.random());
        numItems = Math.min(numItems, num_prods);

        // para controlar que no se repitan los id_prod de los items
        int[] arrIdProd = new int[numItems];
        boolean yaEsta;

        // Se generan los items para el pedido
        for (int k = 0; k < numItems; k++) {
            yaEsta = true;
            while (yaEsta) {
                queProd = (int) (num_prods * Math.random());
                if (k == 0) {
                    yaEsta = false;
                } else {
                    yaEsta = false;
                    for (int j = 0; j < k; j++) {
                        yaEsta = yaEsta || queProd == arrIdProd[j];
                    }
                }
            }
            arrIdProd[k] = queProd;

            idProd = listaProds.get(queProd).getId();
            cantidad = (int) (1.0 + 10.0 * Math.random()); // Cantidades razonables para evitar errores

            item = new wsmusicapedidos.ClsItem();
            item.setIdProd(idProd);
            item.setCantidad(cantidad);
            listaIt.add(item);
        }

        // Mostrar los detalles del pedido generado
        System.out.println("===============================================");
        System.out.println("Cliente: " + idClte);
        System.out.println("Items en el pedido:");
        for (wsmusicapedidos.ClsItem it : listaIt) {
            System.out.println("- Producto ID: " + it.getIdProd() + ", Cantidad: " + it.getCantidad());
        }
        System.out.println("===============================================");

        numPedido = altaPedido(idClte, listaIt);

        if (numPedido == 0) {
            System.out.println("Falta de existencias.");
        } else {
            System.out.println("Número de pedido generado: " + numPedido);
        }

        System.out.println("===============================================");
    }



    public void cierra() {
        System.out.println("El thread de stress " + this.quienSoy + " ha terminado su trabajo.");
    }

    private static int altaPedido(int idClte, List<wsmusicapedidos.ClsItem> listaIt) {
        wsmusicapedidos.WSMusicaPedidos_Service service = new wsmusicapedidos.WSMusicaPedidos_Service();
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();
        return port.altaPedido(idClte, listaIt);
    }

    private static List<wsmusicapedidos.Customer> catalogoCltes() {
        wsmusicapedidos.WSMusicaPedidos_Service service = new wsmusicapedidos.WSMusicaPedidos_Service();
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();
        return port.catalogoCltes();
    }

    private static List<wsmusicapedidos.Product> catalogoProds() {
        wsmusicapedidos.WSMusicaPedidos_Service service = new wsmusicapedidos.WSMusicaPedidos_Service();
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();
        return port.catalogoProds();
    }   

    public static void main(String[] args) {
        POJO_Pedidos pojo = new POJO_Pedidos();
        pojo.prepara(25, null);

        int n_veces = args.length > 0 ? Integer.parseInt(args[0]) : 5;
        for (int i = 1; i <= n_veces; i++) {
            pojo.solicitaServicio(i);
        }
        pojo.cierra();
    }

    private static void procesarBackorders(int numPedido, java.util.List<wsmusicapedidos.Backorder> backorderList) {
        wsmusicapedidos.WSMusicaPedidos_Service service = new wsmusicapedidos.WSMusicaPedidos_Service();
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();
        port.procesarBackorders(numPedido, backorderList);
    }
}
