package pojo_pedidos;
/**
 *
 * @author Alejandro Uribe
 */
public class POJO_Pedidos {

    long quienSoy;
    String host = null;

    java.util.List<wsmusicapedidos.Customer> listaCltes = new java.util.ArrayList<>();
    java.util.List<wsmusicapedidos.Product>  listaProds = new java.util.ArrayList<>();

    int num_cltes;
    int num_prods;

    public void prepara(long quienSoy, String host) {
        this.quienSoy = quienSoy;
        this.host     = host;

        listaCltes    = catalogoCltes();
        listaProds    = catalogoProds();
        num_cltes     = listaCltes.size();
        num_prods     = listaProds.size();
    }

    public void solicitaServicio(int vez) {
        java.util.List<wsmusicapedidos.ClsItem> listaIt = new java.util.ArrayList<>();
        
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

        int[] arrIdProd = new int[numItems];
        boolean yaEsta;

        for (int i = 0; i < numItems; i++) {
            yaEsta = true;
            while (yaEsta) {
                queProd = (int) (num_prods * Math.random());
                if (i == 0) {
                    yaEsta = false;
                } else {
                    yaEsta = false;
                    for (int j = 0; j < i; j++) {
                        yaEsta = yaEsta || queProd == arrIdProd[j];
                    }
                }
            }
            arrIdProd[i] = queProd;

            idProd = listaProds.get(queProd).getId();
            cantidad = (int) (1.0 + 10.0 * Math.random()); // Random cantidades

            item = new wsmusicapedidos.ClsItem();
            item.setIdProd(idProd);
            item.setCantidad(cantidad);
            listaIt.add(item);
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Cliente: " + idClte);
        System.out.println("Ítems en el pedido:");
        for (wsmusicapedidos.ClsItem it : listaIt) {
            System.out.println(" - Producto ID: " + it.getIdProd() + ", Cantidad: " + it.getCantidad());
        }
        System.out.println("-----------------------------------------------");

        numPedido = altaPedido(idClte, listaIt);

        if (numPedido == 0) {
            System.out.println("El pedido no pudo ser completado debido a falta de existencias. Se generaron backorders.");
        } else {
            System.out.println("Número de pedido generado: " + numPedido);
        }
    }

    public void probarBackorders() {
        System.out.println("-----------------------------------------------");
        System.out.println("Procesando backorders...");
        // Puedes agregar un método aquí si necesitas probar algún flujo relacionado con el backorder
        System.out.println("Backorders procesados.");
        System.out.println("-----------------------------------------------");
    }

    public void cierra() {
        System.out.println("El thread de stress " + this.quienSoy + " ha terminado su trabajo.");
    }

    public static void main(String[] args) {
        POJO_Pedidos pojo = new POJO_Pedidos();

        pojo.prepara(25, null);
        int veces = args.length > 0 ? Integer.parseInt(args[0]) : 5;
        for (int i = 1; i <= veces; i++) {
            pojo.solicitaServicio(i);
        }
        pojo.cierra();
    }

    private static int altaPedido(int idClte, java.util.List<wsmusicapedidos.ClsItem> listaIt) {
        wsmusicapedidos.WSMusicaPedidos_Service service = new wsmusicapedidos.WSMusicaPedidos_Service();
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();
        return port.altaPedido(idClte, listaIt);
    }

    private static java.util.List<wsmusicapedidos.Customer> catalogoCltes() {
        wsmusicapedidos.WSMusicaPedidos_Service service = new wsmusicapedidos.WSMusicaPedidos_Service();
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();
        return port.catalogoCltes();
    }

    private static java.util.List<wsmusicapedidos.Product> catalogoProds() {
        wsmusicapedidos.WSMusicaPedidos_Service service = new wsmusicapedidos.WSMusicaPedidos_Service();
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();
        return port.catalogoProds();
    }
    
}
