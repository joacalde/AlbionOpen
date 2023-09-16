package albion.com.demo.Controladores;

import albion.com.demo.Entidades.Foto;
import albion.com.demo.Entidades.Funcion;
import albion.com.demo.Entidades.Linea;
import albion.com.demo.Entidades.Producto;
import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Repositorios.LineaRepositorio;
import albion.com.demo.Servicios.FotoServicio;
import albion.com.demo.Servicios.FuncionServicio;
import albion.com.demo.Servicios.LineaServicio;
import albion.com.demo.Servicios.ProductoServicio;
import albion.com.demo.Servicios.UsuarioServicio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class AdminControlador {

    @Autowired
    private LineaServicio lineaServicio;

    @Autowired
    private LineaRepositorio lineaRepositorio;

    @Autowired
    private FuncionServicio funcionServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private FotoServicio fotoServicio;

    @GetMapping("/admin")
    public String admin(@RequestParam(required = false) String error, ModelMap model) {
        return "admin";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/adminsesion")
    public String adminsesion(
            ModelMap model,
            @RequestParam(required = false, value = "exito") String exito,
            @RequestParam(required = false, value = "error") String error,
            @ModelAttribute(name = "newproducto") Producto newproducto,
            @ModelAttribute(name = "newfuncion") Funcion newfuncion,
            @ModelAttribute(name = "newlinea") Linea newlinea
    )
            throws ErrorServicio {

//        // 1. Ordenar los productos según su posición.
//        List<Producto> productos = productoServicio.todos();
//        if (productos != null) {
//            productos.sort(Comparator.comparingInt(Producto::getPosicion));
//
//            // 2. Ordenar las Funcion de cada Producto según su posición.
//            for (Producto producto : productos) {
//                List<Funcion> funciones = producto.getFunciones();
//                if (funciones != null) {
//                    funciones.sort(Comparator.comparingInt(Funcion::getPosicion));
//
//                    // 3. Ordenar las Linea de cada Funcion según su posición.
//                    for (Funcion funcion : funciones) {
//                        List<Linea> lineas = funcion.getLineas();
//                        if (lineas != null) {
//                            lineas.sort(Comparator.comparingInt(Linea::getPosicion));
//
//                            // 4. Ordenar las Foto de cada Linea según su posición.
//                            for (Linea linea : lineas) {
//                                List<Foto> fotos = linea.getFotos();
//                                if (fotos != null) {
//                                    fotos.sort(Comparator.comparingInt(Foto::getPosicion));
//                                    linea.setFotos(fotos);
//                                }
//                            }
//                            funcion.setLineas(lineas);
//                        }
//                    }
//                    producto.setFunciones(funciones);
//                }
//            }
//        }
//        model.put("producto", productos);
        model.put("producto", productoServicio.todos());
        model.put("funcion", funcionServicio.todos());

        if (newproducto == null) {
            model.put("newproducto", new Producto());
        } else {
            model.put("newproducto", newproducto);
        }
        if (newfuncion == null) {
            model.put("newfuncion", new Funcion());
        } else {
            model.put("newfuncion", newfuncion);
        }
        if (newproducto == null) {
            model.put("newlinea", new Linea());
        } else {
            model.put("newlinea", newlinea);
        }
        model.put("exito", exito);
        model.put("error", error);
        return "adminsesion.html";
    }

    //BUSCAR
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/productos/{productoId}/buscarFunciones")
    public ResponseEntity<List<Funcion>> buscarFunciones(@PathVariable String productoId) throws ErrorServicio {
        Producto producto = productoServicio.buscar(productoId);
        List<Funcion> funciones = producto.getFunciones();
        funciones.sort(Comparator.comparingInt(Funcion::getPosicion));
        return ResponseEntity.ok(funciones);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/funciones/{funcionId}/buscarLineas")
    public ResponseEntity<List<Linea>> buscarLineas(@PathVariable String funcionId) throws ErrorServicio {
        Funcion funcion = funcionServicio.buscar(funcionId);
        List<Linea> lineas = funcion.getLineas();
        lineas.sort(Comparator.comparingInt(Linea::getPosicion));

        // Ordenar las fotos de cada Linea
        for (Linea linea : lineas) {
            List<Foto> fotos = linea.getFotos();
            if (fotos != null && !fotos.isEmpty()) {
                fotos.sort(Comparator.comparingInt(Foto::getPosicion));
                linea.setFotos(fotos);
            }
        }

        return ResponseEntity.ok(lineas);
    }

    //CREAR
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/crearProducto")
    public String crearProducto(
            @RequestParam("producto_es") String producto_es,
            @RequestParam("producto_en") String producto_en,
            @RequestParam("producto_fr") String producto_fr,
            @RequestParam("producto_br") String producto_br,
            @RequestParam("foto") MultipartFile foto,
            ModelMap model,
            RedirectAttributes ra) throws ErrorServicio {
        try {
            productoServicio.crear(producto_es, producto_en, producto_fr, producto_br, foto);
            ra.addAttribute("exito", "El producto se ha creado con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/crearFuncion")
    public String crearFuncion(@ModelAttribute Funcion newfuncion, @RequestParam String producto_es, ModelMap model, RedirectAttributes ra) throws ErrorServicio {
        try {
            funcionServicio.crear(newfuncion, producto_es);
            ra.addAttribute("exito", "La Funcion se ha creado con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
            ra.addFlashAttribute("newfuncion", newfuncion);
        }
        return "redirect:/adminsesion";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/crearLinea")
    public String crearLinea(
            @RequestParam("modelo") String modelo,
            @RequestParam("configuracion") String configuracion,
            @RequestParam("produccion") String produccion,
            @RequestParam("titulo_es") String titulo_es,
            @RequestParam("titulo_en") String titulo_en,
            @RequestParam("descripcion_es") String descripcion_es,
            @RequestParam("descripcion_en") String descripcion_en,
            @RequestParam String funcionId,
            ModelMap model,
            RedirectAttributes ra) {
        try {
            lineaServicio.crear(modelo, configuracion, produccion, titulo_es, titulo_en, descripcion_es, descripcion_en, funcionId);
            ra.addAttribute("exito", "La Linea se ha creado con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    //EDITAR
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/producto/editar")
    public String editarProducto(@ModelAttribute("producto") Producto producto, ModelMap model, RedirectAttributes ra) throws ErrorServicio {
        try {
            productoServicio.editar(producto);
            ra.addAttribute("exito", "El producto se ha editado con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/funcion/editar")
    public String editarFuncion(@RequestParam("funcion_es") List<String> funcionEs,
            @RequestParam("funcion_en") List<String> funcionEn,
            @RequestParam("funcion_fr") List<String> funcionFr,
            @RequestParam("funcion_br") List<String> funcionBr,
            @RequestParam("id") List<String> ids,
            ModelMap model, RedirectAttributes ra) throws ErrorServicio {
        try {
            int i = 0;
            for (String id : ids) {
                Funcion funcion = funcionServicio.buscar(id);
                funcion.setFuncion_es(funcionEs.get(i));
                funcion.setFuncion_en(funcionEn.get(i));
                funcion.setFuncion_fr(funcionFr.get(i));
                funcion.setFuncion_br(funcionBr.get(i));
                funcionServicio.editar(funcion);
                i++;
            }
            ra.addAttribute("exito", "Las funciones se han editado con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/linea/editar")
    public String lineaEditar(
            @RequestParam("id") List<String> ids,
            @RequestParam("modelo") List<String> modelo,
            @RequestParam("configuracion") List<String> configuracion,
            @RequestParam("produccion") List<String> produccion,
            @RequestParam("titulo_es") List<String> titulo_es,
            @RequestParam("titulo_en") List<String> titulo_en,
            @RequestParam("titulo_fr") List<String> titulo_fr,
            @RequestParam("titulo_br") List<String> titulo_br,
            @RequestParam("descripcion_es") List<String> descripcion_es,
            @RequestParam("descripcion_en") List<String> descripcion_en,
            @RequestParam("descripcion_fr") List<String> descripcion_fr,
            @RequestParam("descripcion_br") List<String> descripcion_br,
            ModelMap model, RedirectAttributes ra) throws ErrorServicio, IOException {
        try {
            int i = 0;
            for (String id : ids) {
                Linea linea = lineaServicio.buscar(id);
                linea.setModelo(modelo.get(i));
                linea.setConfiguracion(configuracion.get(i));
                linea.setProduccion(produccion.get(i));
                linea.setTitulo_es(titulo_es.get(i));
                linea.setTitulo_en(titulo_en.get(i));
                linea.setTitulo_fr(titulo_fr.get(i));
                linea.setTitulo_br(titulo_br.get(i));
                linea.setDescripcion_es(descripcion_es.get(i));
                linea.setDescripcion_en(descripcion_en.get(i));
                linea.setDescripcion_fr(descripcion_fr.get(i));
                linea.setDescripcion_br(descripcion_br.get(i));
                lineaServicio.editar(linea);
                i++;
            }
            ra.addAttribute("exito", "Las Lineas se han editado con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    //ORDENAR
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/ordenarProducto")
    public String ordenarProductos(@RequestParam("id") List<String> ids, @RequestParam("posicion") List<Integer> posiciones, RedirectAttributes ra) throws ErrorServicio {
        try {
            productoServicio.ordenarProductos(ids, posiciones);
            ra.addAttribute("exito", "Los Productos se han ordenado con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/ordenarFuncion")
    public String ordenarFuncion(@RequestParam("id") List<String> ids, @RequestParam("posicion") List<Integer> posiciones, RedirectAttributes ra) throws ErrorServicio {
        try {
            funcionServicio.ordenarFunciones(ids, posiciones);
            ra.addAttribute("exito", "Las Funciones se han ordenado con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/ordenarLinea")
    public String ordenarLinea(@RequestParam("id") List<String> ids, @RequestParam("posicion") List<Integer> posiciones, RedirectAttributes ra) throws ErrorServicio {
        try {
            lineaServicio.ordenarLineas(ids, posiciones);
            ra.addAttribute("exito", "Las Lineas se han ordenado con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    //PRODUCTO
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/producto/alta")
    public String altaProducto(@ModelAttribute String id, ModelMap model, RedirectAttributes ra) throws ErrorServicio {
        try {
            productoServicio.alta(id);
            ra.addAttribute("exito", "El producto se ha dado de Alta con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/producto/baja")
    public String bajaProducto(@ModelAttribute String id, ModelMap model, RedirectAttributes ra) throws ErrorServicio {
        try {
            productoServicio.baja(id);
            ra.addAttribute("exito", "El producto se ha dado de Baja con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/producto/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") String id, ModelMap model, RedirectAttributes ra) throws ErrorServicio {
        try {
            productoServicio.eliminar(id);
            ra.addAttribute("exito", "El producto se ha dado de Baja con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    //FUNCION
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/funcion/alta")
    public String altaFuncion(@ModelAttribute String id, ModelMap model, RedirectAttributes ra) throws ErrorServicio {
        try {
            funcionServicio.alta(id);
            ra.addAttribute("exito", "La Función se ha dado de Alta con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/funcion/baja")
    public String bajaFuncion(@ModelAttribute String id, ModelMap model, RedirectAttributes ra) throws ErrorServicio {
        try {
            funcionServicio.baja(id);
            ra.addAttribute("exito", "La Función se ha dado de Baja con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/funcion/eliminar/{id}")
    public String eliminarFuncion(@PathVariable("id") String id, ModelMap model, RedirectAttributes ra) throws ErrorServicio {
        try {
            funcionServicio.eliminar(id);
            ra.addAttribute("exito", "La Función se ha dado de Baja con éxito");
        } catch (ErrorServicio e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    //LINEA
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/linea/alta/{id}")
    public String altaLinea(@PathVariable("id") String id, ModelMap model, RedirectAttributes ra) throws ErrorServicio {
        try {
            lineaServicio.alta(id);
            ra.addFlashAttribute("exito", "La línea se dió de alta");
        } catch (ErrorServicio e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/linea/baja/{id}")
    public String bajaLinea(@PathVariable("id") String id, ModelMap model, RedirectAttributes ra) throws ErrorServicio {
        try {
            lineaServicio.baja(id);
            ra.addFlashAttribute("exito", "La línea se dió de baja");
        } catch (ErrorServicio e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/linea/eliminar/{id}")
    public String eliminarLinea(@PathVariable("id") String id, ModelMap model, RedirectAttributes ra) throws ErrorServicio {
        try {
            lineaServicio.eliminar(id);
            ra.addFlashAttribute("exito", "La línea se ha eliminado con éxito");
        } catch (ErrorServicio e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/foto/eliminar/{id}")
    public ResponseEntity<?> eliminarFoto(@PathVariable("id") String id) {
        try {
            fotoServicio.eliminar(id);
            return ResponseEntity.ok().body("La Foto se ha eliminado con éxito");
        } catch (ErrorServicio e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/linea/{lineaId}/eliminarTodasFotos")
    public ResponseEntity<?> eliminarTodasFotos(@PathVariable("lineaId") String lineaId) {
        try {
            fotoServicio.eliminarTodas(lineaId);
            return ResponseEntity.ok().body("Todas las fotos se han eliminado con éxito");
        } catch (ErrorServicio e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/fotoLinea")
    public String fotoLinea(@RequestParam("id") List<String> ids, MultipartHttpServletRequest request, RedirectAttributes ra) throws ErrorServicio {

        // Crear un mapa para almacenar las fotos por línea
        Map<String, List<MultipartFile>> fotosPorLinea = new HashMap<>();

        for (String id : ids) {
            List<MultipartFile> fotos = request.getFiles("fotos_linea_" + id);
            if (fotos != null && !fotos.isEmpty()) {
                fotosPorLinea.put(id, fotos);
            }
        }

        try {
            lineaServicio.agregarFotoLineas(ids, fotosPorLinea);
            ra.addAttribute("exito", "Las fotos se han agregado correctamente");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

}
