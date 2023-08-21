package albion.com.demo.Controladores;

import albion.com.demo.Entidades.Funcion;
import albion.com.demo.Entidades.Linea;
import albion.com.demo.Entidades.Producto;
import albion.com.demo.Errores.ErrorServicio;
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

    @GetMapping("/linea/{idioma}/{producto}")
    public String linea(ModelMap model, @PathVariable("idioma") int idioma, @PathVariable("producto") int posicion) throws ErrorServicio {

        Producto producto = productoServicio.buscarPorPosicion(posicion);

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
        String titulolinea;

        switch (idioma) {
            case 1:
                model.put("title", "Albion - Lineas");
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
                model.put("nav3", "Lines");
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
                producto_11 = "Butternut Squash";
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
                model.put("title", "Albion - Lignes de Traitement");
                model.put("nav1", "D'accueil");
                model.put("nav2", "À propos");
                model.put("nav3", "Lignes de Traitement");
                producto_1 = "Ail";
                producto_2 = "Amande";
                producto_3 = "Oignon";
                producto_4 = "Cerise";
                producto_5 = "Prune Séchée";
                producto_6 = "Noix";
                producto_7 = "Pommes de Terre";
                producto_8 = "Raisins Secs";
                producto_9 = "Tomate Sèche";
                producto_10 = "Carotte";
                producto_11 = "Courge Butternut";
                producto_otro = "Autres";
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
                model.put("nav4", "Représentations");
                model.put("nav5", "Service");
                model.put("nav6", "Nous Contacter");
                model.put("volver", "Revenir");
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
                model.put("title", "Albion - Linhas de Processamento");
                model.put("nav1", "Início");
                model.put("nav2", "Quem Somos");
                model.put("nav3", "Linhas");
                producto_1 = "Alho";
                producto_2 = "Amêndoa";
                producto_3 = "Cebola";
                producto_4 = "Cereja";
                producto_5 = "Ameixa Seca";
                producto_6 = "Nozes";
                producto_7 = "Batatas";
                producto_8 = "Uva-Passas";
                producto_9 = "Tomate Seco";
                producto_10 = "Cenoura";
                producto_11 = "Abóra Menina Creme";
                producto_otro = "Outros";
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
                model.put("nav4", "Representações");
                model.put("nav5", "Serviços");
                model.put("nav6", "Contate-Nos");
                model.put("volver", "Para Trás");
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
        productos.sort(Comparator.comparingInt(Producto::getPosicion));
        model.put("productos", productos);

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
