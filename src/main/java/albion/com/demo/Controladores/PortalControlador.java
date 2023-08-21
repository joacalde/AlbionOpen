package albion.com.demo.Controladores;

import albion.com.demo.Entidades.Producto;
import albion.com.demo.Errores.ErrorServicio;
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
public class PortalControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/")
    public String index(ModelMap model) throws ErrorServicio {

        model.put("title", "Albion - Inicio");
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

        model.put("header_1", "Años de experiencia");
        model.put("header_2", "Trabajadores");
        model.put("header_3", "Paises alcanzados");
        model.put("tit_1", "Historia");
        model.put("tit_1_vermas", "Ver mas");
        model.put("tit_2", "Lineas de Procesamiento");
        model.put("tit_3", "Representaciones");
        model.put("tit_4", "Servicios");
        model.put("ser_1", "Acesoramiento, Puesta en marcha");
        model.put("ser_2", "Mantenimiento preventivo y correctivo");

        model.put("idioma", 1);
        List<Producto> productos = productoServicio.todos();
        productos.sort(Comparator.comparingInt(Producto::getPosicion));
        model.put("productos", productos);
        return "index";
    }

    @GetMapping("/index/{idioma}")
    public String index2(ModelMap model, @PathVariable("idioma") int idioma) throws ErrorServicio {

        switch (idioma) {
            case 1:
                model.put("title", "Albion - Inicio");
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

                model.put("header_1", "Años de experiencia");
                model.put("header_2", "Trabajadores");
                model.put("header_3", "Paises alcanzados");
                model.put("tit_1", "Historia");
                model.put("tit_1_vermas", "Ver mas");
                model.put("tit_2", "Lineas de Procesamiento");
                model.put("tit_3", "Representaciones");
                model.put("tit_4", "Servicios");
                model.put("ser_1", "Acesoramiento, Puesta en marcha");
                model.put("ser_2", "Mantenimiento preventivo y correctivo");
                break;
            case 2:
                model.put("title", "Albion - Home");
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

                model.put("header_1", "Years of experience");
                model.put("header_2", "Workers");
                model.put("header_3", "Countries reached");
                model.put("tit_1", "History");
                model.put("tit_1_vermas", "See more");
                model.put("tit_2", "Processing Lines");
                model.put("tit_3", "Representations");
                model.put("tit_4", "Services");
                model.put("ser_1", "Advice, Starting up");
                model.put("ser_2", "Preventive and corrective maintenance");
                break;
            case 3:
                model.put("title", "Albion - Inicio");
                model.put("nav1", "Inicio");
                model.put("nav2", "Nosotros");
                model.put("nav3", "Lineas");
                model.put("producto_otro", "Others");
                model.put("nav4", "Representaciones");
                model.put("nav5", "Servicios");
                model.put("nav6", "Contactanos");
                model.put("volver", "volver");

                model.put("header_1", "Años de experiencia");
                model.put("header_2", "Trabajadores");
                model.put("header_3", "Paises alcanzados");
                model.put("tit_1", "Historia");
                model.put("tit_1_vermas", "Ver mas");
                model.put("tit_2", "Lineas");
                model.put("tit_3", "Representaciones");
                model.put("tit_4", "Servicios");
                model.put("ser_1", "Puesta en marcha");
                model.put("ser_2", "Mantenimiento preventivo y correctivo");
                break;
            default:
                model.put("title", "Albion - Inicio");
                model.put("nav1", "Inicio");
                model.put("nav2", "Nosotros");
                model.put("nav3", "Lineas");
                model.put("producto_otro", "Others");
                model.put("nav4", "Representaciones");
                model.put("nav5", "Servicios");
                model.put("nav6", "Contactanos");
                model.put("volver", "volver");

                model.put("header_1", "Años de experiencia");
                model.put("header_2", "Trabajadores");
                model.put("header_3", "Paises alcanzados");
                model.put("tit_1", "Historia");
                model.put("tit_1_vermas", "Ver mas");
                model.put("tit_2", "Lineas");
                model.put("tit_3", "Representaciones");
                model.put("tit_4", "Servicios");
                model.put("ser_1", "Puesta en marcha");
                model.put("ser_2", "Mantenimiento preventivo y correctivo");
                break;
        }

        model.put("idioma", idioma);

        List<Producto> productos = productoServicio.todos();
        productos.sort(Comparator.comparingInt(Producto::getPosicion));
        model.put("productos", productos);

        return "index";
    }

    @GetMapping("/nosotros/{idioma}")
    public String nosotros(ModelMap model, @PathVariable("idioma") int idioma) {
        return "redirect:/index/{idioma}#nosotros";
    }

    @GetMapping("/lineas/{idioma}")
    public String lineas(ModelMap model, @PathVariable("idioma") int idioma) {
        return "redirect:/index/{idioma}#lineas";
    }

    @GetMapping("/representaciones/{idioma}")
    public String representaciones(ModelMap model, @PathVariable("idioma") int idioma) {
        return "redirect:/index/{idioma}#representaciones";
    }

    @GetMapping("/servicios/{idioma}")
    public String servicios(ModelMap model, @PathVariable("idioma") int idioma) {
        return "redirect:/index/{idioma}#servicios";
    }

}
