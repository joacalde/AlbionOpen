package albion.com.demo.Controladores;

import albion.com.demo.Entidades.Equipo;
import albion.com.demo.Entidades.Foto;
import albion.com.demo.Entidades.Funcion;
import albion.com.demo.Entidades.Linea;
import albion.com.demo.Entidades.Producto;
import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Servicios.EquipoServicio;
import albion.com.demo.Servicios.LineaServicio;
import albion.com.demo.Servicios.ProductoServicio;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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

    @GetMapping("/linea/{producto}")
    public String linea(ModelMap model, @PathVariable("producto") int posicion) throws ErrorServicio {

        Producto producto = productoServicio.buscarPorPosicion(posicion);
        model.put("productoposicion", posicion);

        String titulolinea;
        titulolinea = "Lineas de procesamiento para: " + producto.getProducto_es();
        model.put("productotitulo", titulolinea);

        List<Funcion> funciones = producto.getFunciones();
        for (Funcion funcion : funciones) {
            List<Linea> lineas = funcion.getLineas();
            lineas.sort(Comparator.comparingInt(Linea::getPosicion));

            // Ordenar las fotos dentro de cada línea por su posición
            for (Linea linea : lineas) {
                List<Foto> fotos = linea.getFotos();
                if (fotos != null) {
                    fotos.sort(Comparator.comparingInt(Foto::getPosicion));
                    linea.setFotos(fotos);
                }
            }

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
        
        Locale currentLocale = LocaleContextHolder.getLocale();
        model.addAttribute("currentLocale", currentLocale);

        return "linea";
    }

    @GetMapping("/verfoto/{id}")
    public String verfoto(ModelMap model, @PathVariable("id") String id) throws ErrorServicio {
        Linea linea = lineaServicio.buscar(id);
        List<Foto> fotos = linea.getFotos();
            if (fotos != null && !fotos.isEmpty()) {
                fotos.sort(Comparator.comparingInt(Foto::getPosicion));
                linea.setFotos(fotos);
            }
        model.put("linea", linea);
        return "foto";
    }

}
