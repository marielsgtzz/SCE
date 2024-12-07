/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo_envio;

/**
 *
 * @author RGAMBOAH
 */
public class Clte 
{
  
  public static java.util.List<Clte> cltes = new java.util.ArrayList<Clte>(); 
    
  /*
  id_Tda            INT NOT NULL,	 
  
  id_CUSTOMER_ORDER INT NOT NULL,	 
  name              VARCHAR(45) NOT NULL ,
  email             VARCHAR(45) NOT NULL ,
  phone             VARCHAR(45) NOT NULL ,
  address           VARCHAR(45) NOT NULL ,
  city_region       VARCHAR(2) NOT NULL
    */ 
    int id_tda;
    int id_pedido;
    String name;
    String email;
    String phone;
    String Address;
    String city_region;
    
    public static void carga_lista_Cltes()
    {
        cltes.add(new Clte(1,1,"Ditirambo Farfulla","ditirambo.farfulla@mkt.bond","56284000","Arroyo Bajo 10 int 10","AO"));        
        cltes.add(new Clte(1,2,"Gandulfo Roncante","gandulfo.roncante@mkt.bond","56284000","Arroyo Bajo 10 int 12","AO"));
        cltes.add(new Clte(1,3,"Vagonzo Durmiente","vagonzo.durminte@mkt.bond","56284000","Arroyo Bajo 10 int 45","AO"));
        cltes.add(new Clte(1,4,"Hambrosio Comensal","hambrosio.comensal@mkt.bond","56284000","Arroyo Bajo 10 int 58","AO"));
    } 
    
    public static Clte selClte()
    {
        int num_cltes = cltes.size();
        int cual = ((int)( 1000.0 * Math.random())) % num_cltes;
        
        return cltes.get(cual);
    }        
           
    public Clte( int id_tda,
                 int id_pedido,
                 String name,
                 String email,
                 String phone,
                 String Address,
                 String city_region )
    {
       this.id_tda      = id_tda;
       this.id_pedido   = id_pedido;
       this.name        = name;
       this.email       = email;
       this.phone       = phone;
       this.Address     = Address;
       this.city_region = city_region;
    }
    
    public int getId_tda() {
        return id_tda;
    }

    public void setId_tda(int id_tda) {
        this.id_tda = id_tda;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity_region() {
        return city_region;
    }

    public void setCity_region(String city_region) {
        this.city_region = city_region;
    }
    
    
    
    
}
