package albion.com.demo.Controladores;

import albion.com.demo.Entidades.Equipo;
import albion.com.demo.Entidades.Producto;
import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Servicios.EquipoServicio;
import albion.com.demo.Servicios.NotificacionServicio;
import albion.com.demo.Servicios.ProductoServicio;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class ContactoControlador {

    @Autowired
    NotificacionServicio notificacionServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private EquipoServicio equipoServicio;

    @GetMapping("/contacto")
    public String contacto(ModelMap model, @RequestParam(required = false, value = "exito") String exito, @RequestParam(required = false, value = "error") String error, @RequestParam(required = false, value = "nombre") String nombre, @RequestParam(required = false, value = "apellido") String apellido, @RequestParam(required = false, value = "empresa") String empresa, @RequestParam(required = false, value = "pais") String pais, @RequestParam(required = false, value = "correo") String correo, @RequestParam(required = false, value = "telefono") String telefono, @RequestParam(required = false, value = "direccion") String direccion, @RequestParam(required = false, value = "producto") String producto, @RequestParam(required = false, value = "modelo") String modelo, @RequestParam(required = false, value = "mensaje") String mensaje) throws ErrorServicio {

        Locale currentLocale = LocaleContextHolder.getLocale();
        model.addAttribute("currentLocale", currentLocale);

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

        return "contacto";
    }

    @GetMapping("/contacto/{producto}/{modelo}/{configuracion}")
    public String contacto(ModelMap model, @PathVariable("producto") int producto, @PathVariable("modelo") String modelo, @PathVariable("configuracion") String configuracion) throws ErrorServicio {

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

        model.put("producto", producto);
        model.put("modelo", modelo);
        model.put("configuracion", configuracion);
        Locale currentLocale = LocaleContextHolder.getLocale();
        model.addAttribute("currentLocale", currentLocale);

        return "contacto";
    }

    @GetMapping("/contacto/{producto}/{modelo}")
    public String contacto(ModelMap model, @PathVariable("producto") int producto, @PathVariable("modelo") String modelo) throws ErrorServicio {

        Locale currentLocale = LocaleContextHolder.getLocale();
        model.addAttribute("currentLocale", currentLocale);

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

        model.put("producto", producto);
        model.put("modelo", modelo);
        
        return "contacto";
    }

    @GetMapping("/enviarMail")
    public String enviarMail(ModelMap model, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, @RequestParam("empresa") String empresa, @RequestParam("pais") String pais, @RequestParam("correo") String correo, @RequestParam("telefono") String telefono, @RequestParam("direccion") String direccion, @RequestParam("producto") int producto, @RequestParam("modelo") String modelo, @RequestParam("mensaje") String mensaje, RedirectAttributes ra) throws MessagingException, ErrorServicio {
        try {
            notificacionServicio.enviarHtml(nombre, apellido, empresa, pais, correo, telefono, direccion, producto, modelo, mensaje);
            ra.addAttribute("exito", "Se ha enviado su mensaje correctamente. Nos estaremos comunicanco con usted en breve.");
        } catch (Exception e) {
            ra.addAttribute("nombre", nombre);
            ra.addAttribute("apellido", apellido);
            ra.addAttribute("empresa", empresa);
            ra.addAttribute("pais", pais);
            ra.addAttribute("correo", correo);
            ra.addAttribute("telefono", telefono);
            ra.addAttribute("direccion", direccion);
            ra.addAttribute("producto", producto);
            ra.addAttribute("modelo", modelo);
            ra.addAttribute("mensaje", mensaje);
            ra.addAttribute("error", e.getMessage());
        }
        Locale currentLocale = LocaleContextHolder.getLocale();
        model.addAttribute("currentLocale", currentLocale);
        return "redirect:/contacto";
    }

}
