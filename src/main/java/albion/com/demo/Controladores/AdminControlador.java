package albion.com.demo.Controladores;

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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class AdminControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/productos/{id}/funciones")
    public ResponseEntity<List<String>> obtenerFunciones(@PathVariable String id) throws ErrorServicio {
        try {
            Producto producto = productoServicio.buscar(id);
            List<Funcion> funciones = producto.getFunciones();
            List<String> opciones = new ArrayList<>();
            for (Funcion funcion : funciones) {
                opciones.add("<option value=\"" + funcion.getId() + "\">" + funcion.getFuncion_es() + "</option>");
            }
            return ResponseEntity.ok(opciones);
        } catch (ErrorServicio e) {
            return ResponseEntity.notFound().build();
        }
    }

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
            @RequestParam("titulo_fr") String titulo_fr,
            @RequestParam("titulo_br") String titulo_br,
            @RequestParam("descripcion_es") String descripcion_es,
            @RequestParam("descripcion_en") String descripcion_en,
            @RequestParam("descripcion_fr") String descripcion_fr,
            @RequestParam("descripcion_br") String descripcion_br,
            @RequestParam("archivo1") MultipartFile archivo1,
            @RequestParam("archivo2") MultipartFile archivo2,
            @RequestParam("archivo3") MultipartFile archivo3,
            @RequestParam("archivo4") MultipartFile archivo4,
            @RequestParam("archivo5") MultipartFile archivo5,
            @RequestParam("archivo6") MultipartFile archivo6,
            @RequestParam("archivo7") MultipartFile archivo7,
            @RequestParam("archivo8") MultipartFile archivo8,
            @RequestParam String funcionId,
            ModelMap model,
            RedirectAttributes ra) {
        try {
            lineaServicio.crear(modelo, configuracion, produccion, titulo_es, titulo_en, titulo_fr, titulo_br, descripcion_es, descripcion_en, descripcion_fr, descripcion_br, archivo1, archivo2, archivo3, archivo4, archivo5, archivo6, archivo7, archivo8, funcionId);
            ra.addAttribute("exito", "La Linea se ha creado con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

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
    @GetMapping("/productos/{productoId}/buscarFunciones")
    public ResponseEntity<List<Funcion>> buscarFunciones(@PathVariable String productoId) throws ErrorServicio {
        Producto producto = productoServicio.buscar(productoId);
        List<Funcion> funciones = producto.getFunciones();
        return ResponseEntity.ok(funciones);
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/funciones/{funcionId}/buscarLineas")
    public ResponseEntity<List<Linea>> buscarLineas(@PathVariable String funcionId) throws ErrorServicio {
        Funcion funcion = funcionServicio.buscar(funcionId);
        List<Linea> lineas = funcion.getLineas();
        return ResponseEntity.ok(lineas);
    }

    //EDITAR
    //PRODUCTO
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
            @RequestParam("foto1") List<MultipartFile> fotos1,
            @RequestParam("foto2") List<MultipartFile> fotos2,
            @RequestParam("foto3") List<MultipartFile> fotos3,
            @RequestParam("foto4") List<MultipartFile> fotos4,
            @RequestParam("foto5") List<MultipartFile> fotos5,
            @RequestParam("foto6") List<MultipartFile> fotos6,
            @RequestParam("foto7") List<MultipartFile> fotos7,
            @RequestParam("foto8") List<MultipartFile> fotos8,
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
                if (!fotos1.get(i).isEmpty()) {
                    if (linea.getFoto1() == null) {
                        linea.setFoto1(fotoServicio.guardar(fotos1.get(i)));
                    } else {
                        linea.setFoto1(fotoServicio.actualizar(linea.getFoto1().getId(), fotos1.get(i)));
                    }
                }
                if (!fotos2.get(i).isEmpty()) {
                    if (linea.getFoto2() == null) {
                        linea.setFoto2(fotoServicio.guardar(fotos2.get(i)));
                    } else {
                        linea.setFoto2(fotoServicio.actualizar(linea.getFoto2().getId(), fotos2.get(i)));
                    }
                }
                if (!fotos3.get(i).isEmpty()) {
                    if (linea.getFoto3() == null) {
                        linea.setFoto3(fotoServicio.guardar(fotos3.get(i)));
                    } else {
                        linea.setFoto3(fotoServicio.actualizar(linea.getFoto3().getId(), fotos3.get(i)));
                    }
                }
                if (!fotos4.get(i).isEmpty()) {
                    if (linea.getFoto4() == null) {
                        linea.setFoto4(fotoServicio.guardar(fotos4.get(i)));
                    } else {
                        linea.setFoto4(fotoServicio.actualizar(linea.getFoto4().getId(), fotos4.get(i)));
                    }
                }
                if (!fotos5.get(i).isEmpty()) {
                    if (linea.getFoto5() == null) {
                        linea.setFoto5(fotoServicio.guardar(fotos5.get(i)));
                    } else {
                        linea.setFoto5(fotoServicio.actualizar(linea.getFoto5().getId(), fotos5.get(i)));
                    }
                }
                if (!fotos6.get(i).isEmpty()) {
                    if (linea.getFoto6() == null) {
                        linea.setFoto6(fotoServicio.guardar(fotos6.get(i)));
                    } else {
                        linea.setFoto6(fotoServicio.actualizar(linea.getFoto6().getId(), fotos6.get(i)));
                    }
                }
                if (!fotos7.get(i).isEmpty()) {
                    if (linea.getFoto7() == null) {
                        linea.setFoto7(fotoServicio.guardar(fotos7.get(i)));
                    } else {
                        linea.setFoto7(fotoServicio.actualizar(linea.getFoto7().getId(), fotos7.get(i)));
                    }
                }
                if (!fotos8.get(i).isEmpty()) {
                    if (linea.getFoto8() == null) {
                        linea.setFoto8(fotoServicio.guardar(fotos8.get(i)));
                    } else {
                        linea.setFoto8(fotoServicio.actualizar(linea.getFoto8().getId(), fotos8.get(i)));
                    }
                }

                lineaServicio.editar(linea);
                i++;
            }
            ra.addAttribute("exito", "Las Lineas se han editado con éxito");
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
            ra.addFlashAttribute("exito", "La línea se ha eliminado con éxito");
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
    public String eliminarFoto(@PathVariable("id") String id, ModelMap model, RedirectAttributes ra) throws ErrorServicio {
        try {
            Linea linea = lineaServicio.lineaPorFotoId(id);
            if (linea.getFoto1() != null && linea.getFoto1().getId().equals(id)) {
                linea.setFoto1(null);
                fotoServicio.eliminar(id);
                lineaRepositorio.save(linea);
            }
// Foto 2
            if (linea.getFoto2() != null && linea.getFoto2().getId().equals(id)) {
                linea.setFoto2(null);
                fotoServicio.eliminar(id);
                lineaRepositorio.save(linea);
            }

// Foto 3
            if (linea.getFoto3() != null && linea.getFoto3().getId().equals(id)) {
                linea.setFoto3(null);
                fotoServicio.eliminar(id);
                lineaRepositorio.save(linea);
            }

// Foto 4
            if (linea.getFoto4() != null && linea.getFoto4().getId().equals(id)) {
                linea.setFoto4(null);
                fotoServicio.eliminar(id);
                lineaRepositorio.save(linea);
            }

// Foto 5
            if (linea.getFoto5() != null && linea.getFoto5().getId().equals(id)) {
                linea.setFoto5(null);
                fotoServicio.eliminar(id);
                lineaRepositorio.save(linea);
            }

// Foto 6
            if (linea.getFoto6() != null && linea.getFoto6().getId().equals(id)) {
                linea.setFoto6(null);
                fotoServicio.eliminar(id);
                lineaRepositorio.save(linea);
            }

// Foto 7
            if (linea.getFoto7() != null && linea.getFoto7().getId().equals(id)) {
                linea.setFoto7(null);
                fotoServicio.eliminar(id);
                lineaRepositorio.save(linea);
            }

// Foto 8
            if (linea.getFoto8() != null && linea.getFoto8().getId().equals(id)) {
                linea.setFoto8(null);
                fotoServicio.eliminar(id);
                lineaRepositorio.save(linea);
            }
            ra.addAttribute("exito", "La Foto se ha eliminado con éxito");
        } catch (ErrorServicio e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/adminsesion";
    }

}
