package albion.com.demo.Entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Producto {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String producto_es;
    private String producto_en;
    private String producto_fr;
    private String producto_br;
    
    private int posicion;
    private boolean alta;
    
    @OneToOne
    private Foto foto;
    
    @OneToMany (cascade = {CascadeType.ALL})
    private List<Funcion> funciones;

    public Producto() {
    }

    public Producto(String producto_es) {
        this.producto_es = producto_es;
    }
    
    public Producto(String producto_es, String producto_en, String producto_fr, String producto_br, int posicion, boolean alta, List<Funcion> funciones) {
        this.producto_es = producto_es;
        this.producto_en = producto_en;
        this.producto_fr = producto_fr;
        this.producto_br = producto_br;
        this.posicion = posicion;
        this.alta = alta;
        this.funciones = funciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducto_es() {
        return producto_es;
    }

    public void setProducto_es(String producto_es) {
        this.producto_es = producto_es;
    }

    public String getProducto_en() {
        return producto_en;
    }

    public void setProducto_en(String producto_en) {
        this.producto_en = producto_en;
    }

    public String getProducto_fr() {
        return producto_fr;
    }

    public void setProducto_fr(String producto_fr) {
        this.producto_fr = producto_fr;
    }

    public String getProducto_br() {
        return producto_br;
    }

    public void setProducto_br(String producto_br) {
        this.producto_br = producto_br;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public boolean getAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
    
    public List<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }
    
    
}
