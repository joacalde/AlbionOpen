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
public class Linea {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String modelo;
    private String configuracion;
    private String produccion;
    
    private String titulo_es;
    private String titulo_en;
    private String titulo_fr;
    private String titulo_br;
    
    private String descripcion_es;
    private String descripcion_en;
    private String descripcion_fr;
    private String descripcion_br;
    
    private int posicion;
    private boolean alta;
    
    @OneToMany (cascade = {CascadeType.ALL})
    private List<Foto> fotos;

    public Linea() {
    }

    public Linea(String modelo, String configuracion, String produccion, String titulo_es, String titulo_en, String titulo_fr, String titulo_br, String descripcion_es, String descripcion_en, String descripcion_fr, String descripcion_br, int posicion, boolean alta, List<Foto> fotos) {
        this.modelo = modelo;
        this.configuracion = configuracion;
        this.produccion = produccion;
        this.titulo_es = titulo_es;
        this.titulo_en = titulo_en;
        this.titulo_fr = titulo_fr;
        this.titulo_br = titulo_br;
        this.descripcion_es = descripcion_es;
        this.descripcion_en = descripcion_en;
        this.descripcion_fr = descripcion_fr;
        this.descripcion_br = descripcion_br;
        this.posicion = posicion;
        this.alta = alta;
        this.fotos = fotos;
    }
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getProduccion() {
        return produccion;
    }

    public void setProduccion(String produccion) {
        this.produccion = produccion;
    }

    public String getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(String configuracion) {
        this.configuracion = configuracion;
    }

    public String getTitulo_es() {
        return titulo_es;
    }

    public void setTitulo_es(String titulo_es) {
        this.titulo_es = titulo_es;
    }

    public String getTitulo_en() {
        return titulo_en;
    }

    public void setTitulo_en(String titulo_en) {
        this.titulo_en = titulo_en;
    }

    public String getTitulo_fr() {
        return titulo_fr;
    }

    public void setTitulo_fr(String titulo_fr) {
        this.titulo_fr = titulo_fr;
    }

    public String getTitulo_br() {
        return titulo_br;
    }

    public void setTitulo_br(String titulo_br) {
        this.titulo_br = titulo_br;
    }

    public String getDescripcion_es() {
        return descripcion_es;
    }

    public void setDescripcion_es(String descripcion_es) {
        this.descripcion_es = descripcion_es;
    }

    public String getDescripcion_en() {
        return descripcion_en;
    }

    public void setDescripcion_en(String descripcion_en) {
        this.descripcion_en = descripcion_en;
    }

    public String getDescripcion_fr() {
        return descripcion_fr;
    }

    public void setDescripcion_fr(String descripcion_fr) {
        this.descripcion_fr = descripcion_fr;
    }

    public String getDescripcion_br() {
        return descripcion_br;
    }

    public void setDescripcion_br(String descripcion_br) {
        this.descripcion_br = descripcion_br;
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

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }
     
}
