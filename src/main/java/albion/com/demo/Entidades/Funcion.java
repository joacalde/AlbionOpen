package albion.com.demo.Entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Funcion {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String funcion_es;
    private String funcion_en;
    private String funcion_fr;
    private String funcion_br;
    
    private int posicion;
    private boolean alta;
    
    @OneToMany (cascade = {CascadeType.ALL})
    private List<Linea> lineas;

    public Funcion() {
    }

    public Funcion(String funcion_es) {
        this.funcion_es = funcion_es;
    }
    
    public Funcion(String funcion_es, String funcion_en, String funcion_fr, String funcion_br, int posicion, boolean alta, List<Linea> lineas) {
        this.funcion_es = funcion_es;
        this.funcion_en = funcion_en;
        this.funcion_fr = funcion_fr;
        this.funcion_br = funcion_br;
        this.posicion = posicion;
        this.alta = alta;
        this.lineas = lineas;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuncion_es() {
        return funcion_es;
    }

    public void setFuncion_es(String funcion_es) {
        this.funcion_es = funcion_es;
    }

    public String getFuncion_en() {
        return funcion_en;
    }

    public void setFuncion_en(String funcion_en) {
        this.funcion_en = funcion_en;
    }

    public String getFuncion_fr() {
        return funcion_fr;
    }

    public void setFuncion_fr(String funcion_fr) {
        this.funcion_fr = funcion_fr;
    }

    public String getFuncion_br() {
        return funcion_br;
    }

    public void setFuncion_br(String funcion_br) {
        this.funcion_br = funcion_br;
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
    
    public List<Linea> getLineas() {
        return lineas;
    }

    public void setLineas(List<Linea> lineas) {
        this.lineas = lineas;
    }
    
    
    
}
