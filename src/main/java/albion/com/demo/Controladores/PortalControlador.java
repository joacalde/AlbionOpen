package albion.com.demo.Controladores;

import albion.com.demo.Entidades.Equipo;
import albion.com.demo.Entidades.Producto;
import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Servicios.EquipoServicio;
import albion.com.demo.Servicios.ProductoServicio;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private EquipoServicio equipoServicio;
    
    @ModelAttribute("currentLocale")
    public Locale getCurrentLocale(Locale locale) {
        return locale;
    }

    @GetMapping("/")
    public String index(ModelMap model, Locale locale) throws ErrorServicio {
        
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
        
        return "index";
    }

    @GetMapping("/nosotros")
    public String nosotros(ModelMap model) {
        return "redirect:/index#nosotros";
    }

    @GetMapping("/lineas")
    public String lineas(ModelMap model, Locale locale) {
        return "redirect:/index#lineas";
    }

    @GetMapping("/representaciones")
    public String representaciones(ModelMap model, Locale locale) {
        return "redirect:/index#representaciones";
    }

    @GetMapping("/servicios")
    public String servicios(ModelMap model, Locale locale) {
        return "redirect:/index#servicios";
    }

}
