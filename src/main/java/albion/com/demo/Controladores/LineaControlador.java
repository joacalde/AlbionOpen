package albion.com.demo.Controladores;

import albion.com.demo.Entidades.Equipo;
import albion.com.demo.Entidades.Funcion;
import albion.com.demo.Entidades.Linea;
import albion.com.demo.Entidades.Producto;
import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Servicios.EquipoServicio;
import albion.com.demo.Servicios.LineaServicio;
import albion.com.demo.Servicios.ProductoServicio;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LineaControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private LineaServicio lineaServicio;
    
    @Autowired
    private EquipoServicio equipoServicio;

    @GetMapping("/linea/{idioma}/{producto}")
    public String linea(ModelMap model, @PathVariable("idioma") int idioma, @PathVariable("producto") int posicion) throws ErrorServicio {

        Producto producto = productoServicio.buscarPorPosicion(posicion);
        model.put("productoposicion", posicion);

        String titulolinea;

        switch (idioma) {
            case 1:
                model.put("title", "Albion - Lineas");
                model.put("nav1", "Inicio");
                model.put("nav2", "Nosotros");
                model.put("nav2_1", "Historia");
                model.put("nav2_2", "Servicios");
                model.put("nav2_3", "Representaciones");
                model.put("nav3", "Lineas");
                model.put("nav4", "Equipos");
                model.put("nav5", "Contactanos");
                model.put("producto_otro", "Otros");
                model.put("volver", "volver");
                
                model.put("tabla_1", "Modelo");
                model.put("tabla_2", "Produccion");
                model.put("tabla_3", "Fotos");
                model.put("tabla_4", "Cotización");
                model.put("tabla_5", "Equipos que la componen");
                model.put("tabla_6", "Descripcion");
                model.put("cotizar", "Solicitar Cotización");
                model.put("imagen", "ver imagen");
                model.put("ver", "ver");
                model.put("noimagen", "No hay imagen disponible");
                titulolinea = "Lineas de procesamiento para: " + producto.getProducto_es();
                break;
            case 2:
                model.put("title", "Albion - Processing Lines");
                model.put("nav1", "Home");
                model.put("nav2", "About us");
                model.put("nav2_1", "History");
                model.put("nav2_2", "Servicess");
                model.put("nav2_3", "Representatios");
                model.put("nav3", "Processing Lines");
                model.put("nav4", "Equipment");
                model.put("nav5", "Contact Us");
                model.put("producto_otro", "Others");
                model.put("volver", "back");
                
                model.put("tabla_1", "Model");
                model.put("tabla_2", "Productin");
                model.put("tabla_3", "Photos");
                model.put("tabla_4", "Quote");
                model.put("tabla_5", "Include rhis Equipment");
                model.put("tabla_6", "Description");
                model.put("cotizar", "Request Quote");
                model.put("imagen", "View Image");
                model.put("ver", "view");
                model.put("noimagen", "No image available");
                titulolinea = "Processing Lines for: " + producto.getProducto_en();
                break;
            case 3:
                model.put("title", "Albion - Processing Lines");
                model.put("nav1", "Home");
                model.put("nav2", "About us");
                model.put("nav2_1", "History");
                model.put("nav2_2", "Services");
                model.put("nav2_3", "Representations");
                model.put("nav3", "Processing Lines");
                model.put("nav4", "Equipment");
                model.put("nav5", "Contact Us");
                model.put("producto_otro", "Others");
                model.put("volver", "back");
                
                model.put("tabla_1", "Modèle");
                model.put("tabla_2", "Production");
                model.put("tabla_3", "Photos");
                model.put("tabla_4", "Cotisation");
                model.put("tabla_5", "Titre");
                model.put("tabla_6", "Description");
                model.put("cotizar", "Citation Requise");
                model.put("imagen", "voir l'image");
                model.put("ver", "voir");
                model.put("noimagen", "Pas d'image disponible");
                titulolinea = "Lignes de traitement pour: " + producto.getProducto_fr();
                break;
            default:
                model.put("title", "Albion - Processing Lines");
                model.put("nav1", "Home");
                model.put("nav2", "About us");
                model.put("nav2_1", "History");
                model.put("nav2_2", "Servicess");
                model.put("nav2_3", "Representatios");
                model.put("nav3", "Processing Lines");
                model.put("nav4", "Equipment");
                model.put("nav5", "Contact Us");
                model.put("producto_otro", "Others");
                model.put("volver", "back");
                
                model.put("tabla_1", "Modelo");
                model.put("tabla_2", "Produção");
                model.put("tabla_3", "Fotos");
                model.put("tabla_4", "Cotação");
                model.put("tabla_5", "Título");
                model.put("tabla_6", "Descrição");
                model.put("cotizar", "Solicitar Orçamento");
                model.put("imagen", "ver imagen");
                model.put("ver", "visualizar");
                model.put("noimagen", "Nenhuma imagem disponível");
                titulolinea = "Linhas de Processamento para:" + producto.getProducto_br();
                break;
        }

        model.put("idioma", idioma);
        model.put("productotitulo", titulolinea);
        List<Funcion> funciones = producto.getFunciones();
        for (Funcion funcion : funciones) {
            List<Linea> lineas = funcion.getLineas();
            lineas.sort(Comparator.comparingInt(Linea::getPosicion));
            funcion.setLineas(lineas);
        }
        producto.setFunciones(funciones);
        model.put("producto", producto);

        List<Producto> productos = productoServicio.todos();
        if (productos != null) {
            productos.sort(Comparator.comparingInt(Producto::getPosicion));
        }
        model.put("productos", productos);
        List<Equipo> equipos = equipoServicio.todos();
        if (equipos != null) {
            equipos.sort(Comparator.comparingInt(Equipo::getPosicion));
        }
        model.put("equipos", equipos);
        
        return "linea";
    }

    @GetMapping("/verfoto/{id}")
    public String verfoto(ModelMap model, @PathVariable("id") String id) throws ErrorServicio {
        Linea linea = lineaServicio.buscar(id);
        System.out.println(linea.getId());
        model.put("linea", linea);
        return "foto";
    }

}
