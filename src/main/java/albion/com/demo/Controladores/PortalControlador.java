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

        String producto_1;
        String producto_2;
        String producto_3;
        String producto_4;
        String producto_5;
        String producto_6;
        String producto_7;
        String producto_8;
        String producto_9;
        String producto_10;
        String producto_11;
        String producto_otro;

        model.put("title", "Albion - Inicio");
        model.put("nav1", "Inicio");
        model.put("nav2", "Nosotros");
        model.put("nav3", "Lineas");
        producto_1 = "Ajo";
        producto_2 = "Almendra";
        producto_3 = "Cebolla";
        producto_4 = "Cereza";
        producto_5 = "Ciruela Seca";
        producto_6 = "Nueces";
        producto_7 = "Papas";
        producto_8 = "Pasas";
        producto_9 = "Tomate Seco";
        producto_10 = "Zanahoria";
        producto_11 = "Zapallo Anco";
        producto_otro = "Otros";
        model.put("producto_1", producto_1);
        model.put("producto_2", producto_2);
        model.put("producto_3", producto_3);
        model.put("producto_4", producto_4);
        model.put("producto_5", producto_5);
        model.put("producto_6", producto_6);
        model.put("producto_7", producto_7);
        model.put("producto_8", producto_8);
        model.put("producto_9", producto_9);
        model.put("producto_10", producto_10);
        model.put("producto_11", producto_11);
        model.put("producto_otro", producto_otro);
        model.put("nav4", "Representaciones");
        model.put("nav5", "Servicios");
        model.put("nav6", "Contactanos");
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

        String producto_1;
        String producto_2;
        String producto_3;
        String producto_4;
        String producto_5;
        String producto_6;
        String producto_7;
        String producto_8;
        String producto_9;
        String producto_10;
        String producto_11;
        String producto_otro;

        switch (idioma) {
            case 1:
                model.put("title", "Albion - Inicio");
                model.put("nav1", "Inicio");
                model.put("nav2", "Nosotros");
                model.put("nav3", "Lineas");
                producto_1 = "Ajo";
                producto_2 = "Almendra";
                producto_3 = "Cebolla";
                producto_4 = "Cereza";
                producto_5 = "Ciruela Seca";
                producto_6 = "Nueces";
                producto_7 = "Papas";
                producto_8 = "Pasas";
                producto_9 = "Tomate Seco";
                producto_10 = "Zanahoria";
                producto_11 = "Zapallo Anco";
                producto_otro = "Otros";
                model.put("producto_1", producto_1);
                model.put("producto_2", producto_2);
                model.put("producto_3", producto_3);
                model.put("producto_4", producto_4);
                model.put("producto_5", producto_5);
                model.put("producto_6", producto_6);
                model.put("producto_7", producto_7);
                model.put("producto_8", producto_8);
                model.put("producto_9", producto_9);
                model.put("producto_10", producto_10);
                model.put("producto_11", producto_11);
                model.put("producto_otro", producto_otro);
                model.put("nav4", "Representaciones");
                model.put("nav5", "Servicios");
                model.put("nav6", "Contactanos");
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
                model.put("nav3", "Processing Lines");
                producto_1 = "Garlic";
                producto_2 = "Almond";
                producto_3 = "Onion";
                producto_4 = "Cherry";
                producto_5 = "Dried Plum";
                producto_6 = "Walnuts";
                producto_7 = "Potato";
                producto_8 = "Raisins";
                producto_9 = "Dried Tomato";
                producto_10 = "Carrot";
                producto_11 = "Squash";
                producto_otro = "Others";
                model.put("producto_1", producto_1);
                model.put("producto_2", producto_2);
                model.put("producto_3", producto_3);
                model.put("producto_4", producto_4);
                model.put("producto_5", producto_5);
                model.put("producto_6", producto_6);
                model.put("producto_7", producto_7);
                model.put("producto_8", producto_8);
                model.put("producto_9", producto_9);
                model.put("producto_10", producto_10);
                model.put("producto_11", producto_11);
                model.put("producto_otro", producto_otro);
                model.put("nav4", "Representations");
                model.put("nav5", "Services");
                model.put("nav6", "Contact Us");
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
                producto_1 = "Ajo";
                producto_2 = "Almendra";
                producto_3 = "Cebolla";
                producto_4 = "Cereza";
                producto_5 = "Ciruela Seca";
                producto_6 = "Nueces";
                producto_7 = "Papas";
                producto_8 = "Pasas";
                producto_9 = "Tomate Seco";
                producto_10 = "Zanahoria";
                producto_11 = "Zapallo Anco";
                producto_otro = "Otros";
                model.put("producto_1", producto_1);
                model.put("producto_2", producto_2);
                model.put("producto_3", producto_3);
                model.put("producto_4", producto_4);
                model.put("producto_5", producto_5);
                model.put("producto_6", producto_6);
                model.put("producto_7", producto_7);
                model.put("producto_8", producto_8);
                model.put("producto_9", producto_9);
                model.put("producto_10", producto_10);
                model.put("producto_11", producto_11);
                model.put("producto_otro", producto_otro);
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
                producto_1 = "Ajo";
                producto_2 = "Almendra";
                producto_3 = "Cebolla";
                producto_4 = "Cereza";
                producto_5 = "Ciruela Seca";
                producto_6 = "Nueces";
                producto_7 = "Papas";
                producto_8 = "Pasas";
                producto_9 = "Tomate Seco";
                producto_10 = "Zanahoria";
                producto_11 = "Zapallo Anco";
                producto_otro = "Otros";
                model.put("producto_1", producto_1);
                model.put("producto_2", producto_2);
                model.put("producto_3", producto_3);
                model.put("producto_4", producto_4);
                model.put("producto_5", producto_5);
                model.put("producto_6", producto_6);
                model.put("producto_7", producto_7);
                model.put("producto_8", producto_8);
                model.put("producto_9", producto_9);
                model.put("producto_10", producto_10);
                model.put("producto_11", producto_11);
                model.put("producto_otro", producto_otro);
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
