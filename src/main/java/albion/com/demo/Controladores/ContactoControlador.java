package albion.com.demo.Controladores;

import albion.com.demo.Entidades.Equipo;
import albion.com.demo.Entidades.Producto;
import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Servicios.EquipoServicio;
import albion.com.demo.Servicios.NotificacionServicio;
import albion.com.demo.Servicios.ProductoServicio;
import java.util.Comparator;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/contacto/{idioma}")
    public String contacto(ModelMap model, @PathVariable("idioma") int idioma, @RequestParam(required = false, value = "exito") String exito, @RequestParam(required = false, value = "error") String error, @RequestParam(required = false, value = "nombre") String nombre, @RequestParam(required = false, value = "apellido") String apellido, @RequestParam(required = false, value = "empresa") String empresa, @RequestParam(required = false, value = "pais") String pais, @RequestParam(required = false, value = "correo") String correo, @RequestParam(required = false, value = "telefono") String telefono, @RequestParam(required = false, value = "direccion") String direccion, @RequestParam(required = false, value = "producto") String producto, @RequestParam(required = false, value = "modelo") String modelo, @RequestParam(required = false, value = "mensaje") String mensaje) throws ErrorServicio {

        model.put("exito", exito);
        model.put("error", error);
        model.put("nombre", nombre);
        model.put("apellido", apellido);
        model.put("empresa", empresa);
        model.put("pais", pais);
        model.put("correo", correo);
        model.put("telefono", telefono);
        model.put("direccion", direccion);
        model.put("producto", producto);
        model.put("modelo", modelo);
        model.put("mensaje", mensaje);

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

                model.put("titulo_contacto", "Contactanos");
                model.put("form_nombre", "Nombre");
                model.put("form_nombre_e", "El nombre no puede contener símbolos. Debe completar este campo.");
                model.put("form_apellido", "Apellido");
                model.put("form_apellido_e", "El apellido no puede contener símbolos. Debe completar este campo.");
                model.put("form_empresa", "Empresa");
                model.put("form_empresa_e", "Debe completar este campo.");
                model.put("form_pais", "Pais");
                model.put("form_correo", "Correo Electrónico");
                model.put("form_correo_e", "Debe ingresar un mail válido.");
                model.put("form_tel", "Teléfono");
                model.put("form_tel_e", "El telefono solo puede contener numeros y el maximo son 14 dígitos.");
                model.put("form_direccion", "Dirección");
                model.put("form_direccion_e", "Debe completar este campo.");
                model.put("form_producto", "Producto");
                model.put("form_modelo", "Modelo");
                model.put("form_configuracion", "Configuracion");
                model.put("form_mensaje", "Mensaje");
                model.put("form_mensaje_e", "El mensaje no puede estar vacío.");
                model.put("form_error", "Error:");
                model.put("form_error_m", " Por favor rellena el formulario correctamente.");
                model.put("form_enviar", "Enviar");
                model.put("form_enviar_m", "Formulario enviado exitosamente!");
                break;
            case 2:
                model.put("title", "Albion - Home");
                model.put("nav1", "Home");
                model.put("nav2", "About us");
                model.put("nav2_1", "History");
                model.put("nav2_2", "Services");
                model.put("nav2_3", "Representatios");
                model.put("nav3", "Processing Lines");
                model.put("nav4", "Equipment");
                model.put("nav5", "Contact Us");
                model.put("producto_otro", "Others");
                model.put("volver", "back");

                model.put("titulo_contacto", "Contact Us");
                model.put("form_nombre", "Name");
                model.put("form_nombre_e", "The name cannot contain symbols. You must fill this field.");
                model.put("form_apellido", "Surname");
                model.put("form_apellido_e", "The surname cannot contain symbols. You must fill this field.");
                model.put("form_empresa", "Company");
                model.put("form_empresa_e", "You must fill this field.");
                model.put("form_pais", "Pais");
                model.put("form_correo", "Email");
                model.put("form_correo_e", "You must enter a valid email.");
                model.put("form_tel", "Phone Number");
                model.put("form_tel_e", "The phone number can only contain numbers and the maximum is 14 digits.");
                model.put("form_direccion", "Address");
                model.put("form_direccion_e", "You must fill this field.");
                model.put("form_producto", "Product");
                model.put("form_modelo", "Model");
                model.put("form_configuracion", "Configuration");
                model.put("form_mensaje", "Message");
                model.put("form_mensaje_e", "The message cannot be empty.");
                model.put("form_error", "Error:");
                model.put("form_error_m", " Please fill out this form correctly.");
                model.put("form_enviar", "Send");
                model.put("form_enviar_m", "Form submitted successfully!");
                break;
            case 3:
                model.put("title", "Albion - Home");
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

                model.put("titulo_contacto", "Nous Contacter");
                model.put("form_nombre", "Nom");
                model.put("form_nombre_e", "El nombre no puede contener símbolos. Debe completar este campo.");
                model.put("form_apellido", "Le Nom");
                model.put("form_apellido_e", "El apellido no puede contener símbolos. Debe completar este campo.");
                model.put("form_empresa", "Entreprise");
                model.put("form_empresa_e", "Debe completar este campo.");
                model.put("form_pais", "Pays");
                model.put("form_correo", "Courrier Électronique");
                model.put("form_correo_e", "Debe ingresar un mail válido.");
                model.put("form_tel", "Téléphone");
                model.put("form_tel_e", "El telefono solo puede contener numeros y el maximo son 14 dígitos.");
                model.put("form_direccion", "Adresse");
                model.put("form_direccion_e", "Debe completar este campo.");
                model.put("form_producto", "Produit");
                model.put("form_modelo", "Modèle");
                model.put("form_mensaje", "Message");
                model.put("form_mensaje_e", "El mensaje no puede estar vacío.");
                model.put("form_error", "Erreur:");
                model.put("form_error_m", " Veuillez remplir le formulaire correctement.");
                model.put("form_enviar", "Envoyer");
                model.put("form_enviar_m", "Formulaire envoyé avec succès !");
                break;
            default:
                model.put("title", "Albion - Contact Us");
                model.put("nav1", "Home");
                model.put("nav2", "About us");
                model.put("nav2_1", "History");
                model.put("nav2_2", "Servicess");
                model.put("nav2_3", "Representations");
                model.put("nav3", "Processing Lines");
                model.put("nav4", "Equipment");
                model.put("nav5", "Contact Us");
                model.put("producto_otro", "Others");
                model.put("volver", "back");

                model.put("titulo_contacto", "Contate-Nos");
                model.put("form_nombre", "Nome");
                model.put("form_nombre_e", "El nombre no puede contener símbolos. Debe completar este campo.");
                model.put("form_apellido", "Sobrenome");
                model.put("form_apellido_e", "El apellido no puede contener símbolos. Debe completar este campo.");
                model.put("form_empresa", "Companhia");
                model.put("form_empresa_e", "Debe completar este campo.");
                model.put("form_pais", "País");
                model.put("form_correo", "Correio eletrônico");
                model.put("form_correo_e", "Debe ingresar un mail válido.");
                model.put("form_tel", "Telefone");
                model.put("form_tel_e", "El telefono solo puede contener numeros y el maximo son 14 dígitos.");
                model.put("form_direccion", "Direção");
                model.put("form_direccion_e", "Debe completar este campo.");
                model.put("form_producto", "Produto");
                model.put("form_modelo", "Modelo");
                model.put("form_mensaje", "Mensagem");
                model.put("form_mensaje_e", "El mensaje no puede estar vacío.");
                model.put("form_error", "Erro:");
                model.put("form_error_m", " Por favor, preencha o formulário corretamente.");
                model.put("form_enviar", "Mandar");
                model.put("form_enviar_m", "Formulário enviado com sucesso!");
                break;
        }
        
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
        
        model.put("idioma", idioma);
        return "contacto";
    }

    @GetMapping("/contacto/{idioma}/{producto}/{modelo}/{configuracion}")
    public String contacto(ModelMap model, @PathVariable("idioma") int idioma, @PathVariable("producto") int producto, @PathVariable("modelo") String modelo, @PathVariable("configuracion") String configuracion) throws ErrorServicio {

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

                model.put("titulo_contacto", "Contactanos");
                model.put("form_nombre", "Nombre");
                model.put("form_nombre_e", "El nombre no puede contener símbolos. Debe completar este campo.");
                model.put("form_apellido", "Apellido");
                model.put("form_apellido_e", "El apellido no puede contener símbolos. Debe completar este campo.");
                model.put("form_empresa", "Empresa");
                model.put("form_empresa_e", "Debe completar este campo.");
                model.put("form_pais", "Pais");
                model.put("form_correo", "Correo Electrónico");
                model.put("form_correo_e", "Debe ingresar un mail válido.");
                model.put("form_tel", "Teléfono");
                model.put("form_tel_e", "El telefono solo puede contener numeros y el maximo son 14 dígitos.");
                model.put("form_direccion", "Dirección");
                model.put("form_direccion_e", "Debe completar este campo.");
                model.put("form_producto", "Producto");
                model.put("form_modelo", "Modelo");
                model.put("form_configuracion", "Configuracion");
                model.put("form_mensaje", "Mensaje");
                model.put("form_mensaje_e", "El mensaje no puede estar vacío.");
                model.put("form_error", "Error:");
                model.put("form_error_m", " Por favor rellena el formulario correctamente.");
                model.put("form_enviar", "Enviar");
                model.put("form_enviar_m", "Formulario enviado exitosamente!");
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

                model.put("titulo_contacto", "Contact Us");
                model.put("form_nombre", "Name");
                model.put("form_nombre_e", "The name cannot contain symbols. You must fill this field.");
                model.put("form_apellido", "Surname");
                model.put("form_apellido_e", "The surname cannot contain symbols. You must fill this field.");
                model.put("form_empresa", "Company");
                model.put("form_empresa_e", "You must fill this field.");
                model.put("form_pais", "Pais");
                model.put("form_correo", "Email");
                model.put("form_correo_e", "You must enter a valid email.");
                model.put("form_tel", "Phone Number");
                model.put("form_tel_e", "The phone number can only contain numbers and the maximum is 14 digits.");
                model.put("form_direccion", "Address");
                model.put("form_direccion_e", "You must fill this field.");
                model.put("form_producto", "Product");
                model.put("form_modelo", "Model");
                model.put("form_configuracion", "Configuration");
                model.put("form_mensaje", "Message");
                model.put("form_mensaje_e", "The message cannot be empty.");
                model.put("form_error", "Error:");
                model.put("form_error_m", " Please fill out this form correctly.");
                model.put("form_enviar", "Send");
                model.put("form_enviar_m", "Form submitted successfully!");
                break;
            case 3:
                model.put("title", "Albion - Home");
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

                model.put("titulo_contacto", "Nous Contacter");
                model.put("form_nombre", "Nom");
                model.put("form_nombre_e", "El nombre no puede contener símbolos. Debe completar este campo.");
                model.put("form_apellido", "Le Nom");
                model.put("form_apellido_e", "El apellido no puede contener símbolos. Debe completar este campo.");
                model.put("form_empresa", "Entreprise");
                model.put("form_empresa_e", "Debe completar este campo.");
                model.put("form_pais", "Pays");
                model.put("form_correo", "Courrier Électronique");
                model.put("form_correo_e", "Debe ingresar un mail válido.");
                model.put("form_tel", "Téléphone");
                model.put("form_tel_e", "El telefono solo puede contener numeros y el maximo son 14 dígitos.");
                model.put("form_direccion", "Adresse");
                model.put("form_direccion_e", "Debe completar este campo.");
                model.put("form_producto", "Produit");
                model.put("form_modelo", "Modèle");
                model.put("form_mensaje", "Message");
                model.put("form_mensaje_e", "El mensaje no puede estar vacío.");
                model.put("form_error", "Erreur:");
                model.put("form_error_m", " Veuillez remplir le formulaire correctement.");
                model.put("form_enviar", "Envoyer");
                model.put("form_enviar_m", "Formulaire envoyé avec succès !");
                break;
            default:
                model.put("title", "Albion - Contact Us");
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

                model.put("titulo_contacto", "Contate-Nos");
                model.put("form_nombre", "Nome");
                model.put("form_nombre_e", "El nombre no puede contener símbolos. Debe completar este campo.");
                model.put("form_apellido", "Sobrenome");
                model.put("form_apellido_e", "El apellido no puede contener símbolos. Debe completar este campo.");
                model.put("form_empresa", "Companhia");
                model.put("form_empresa_e", "Debe completar este campo.");
                model.put("form_pais", "País");
                model.put("form_correo", "Correio eletrônico");
                model.put("form_correo_e", "Debe ingresar un mail válido.");
                model.put("form_tel", "Telefone");
                model.put("form_tel_e", "El telefono solo puede contener numeros y el maximo son 14 dígitos.");
                model.put("form_direccion", "Direção");
                model.put("form_direccion_e", "Debe completar este campo.");
                model.put("form_producto", "Produto");
                model.put("form_modelo", "Modelo");
                model.put("form_mensaje", "Mensagem");
                model.put("form_mensaje_e", "El mensaje no puede estar vacío.");
                model.put("form_error", "Erro:");
                model.put("form_error_m", " Por favor, preencha o formulário corretamente.");
                model.put("form_enviar", "Mandar");
                model.put("form_enviar_m", "Formulário enviado com sucesso!");
                break;
        }
        
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
        
        
        model.put("idioma", idioma);
        
        model.put("producto", producto);
        model.put("modelo", modelo);
        model.put("configuracion", configuracion);
        return "contacto";
    }

    @GetMapping("/contacto/{idioma}/{producto}/{modelo}")
    public String contacto(ModelMap model, @PathVariable("idioma") int idioma, @PathVariable("producto") int producto, @PathVariable("modelo") String modelo) throws ErrorServicio {

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

                model.put("titulo_contacto", "Contactanos");
                model.put("form_nombre", "Nombre");
                model.put("form_nombre_e", "El nombre no puede contener símbolos. Debe completar este campo.");
                model.put("form_apellido", "Apellido");
                model.put("form_apellido_e", "El apellido no puede contener símbolos. Debe completar este campo.");
                model.put("form_empresa", "Empresa");
                model.put("form_empresa_e", "Debe completar este campo.");
                model.put("form_pais", "Pais");
                model.put("form_correo", "Correo Electrónico");
                model.put("form_correo_e", "Debe ingresar un mail válido.");
                model.put("form_tel", "Teléfono");
                model.put("form_tel_e", "El telefono solo puede contener numeros y el maximo son 14 dígitos.");
                model.put("form_direccion", "Dirección");
                model.put("form_direccion_e", "Debe completar este campo.");
                model.put("form_producto", "Producto");
                model.put("form_modelo", "Modelo");
                model.put("form_configuracion", "Configuracion");
                model.put("form_mensaje", "Mensaje");
                model.put("form_mensaje_e", "El mensaje no puede estar vacío.");
                model.put("form_error", "Error:");
                model.put("form_error_m", " Por favor rellena el formulario correctamente.");
                model.put("form_enviar", "Enviar");
                model.put("form_enviar_m", "Formulario enviado exitosamente!");
                break;
            case 2:
                model.put("title", "Albion - Home");
                model.put("nav1", "Home");
                model.put("nav2", "About us");
                model.put("nav2_1", "History");
                model.put("nav2_2", "Services");
                model.put("nav2_3", "Representatioss");
                model.put("nav3", "Processing Lines");
                model.put("nav4", "Equipment");
                model.put("nav5", "Contact Us");
                model.put("producto_otro", "Others");
                model.put("volver", "back");

                model.put("titulo_contacto", "Contact Us");
                model.put("form_nombre", "Name");
                model.put("form_nombre_e", "The name cannot contain symbols. You must fill this field.");
                model.put("form_apellido", "Surname");
                model.put("form_apellido_e", "The surname cannot contain symbols. You must fill this field.");
                model.put("form_empresa", "Company");
                model.put("form_empresa_e", "You must fill this field.");
                model.put("form_pais", "Pais");
                model.put("form_correo", "Email");
                model.put("form_correo_e", "You must enter a valid email.");
                model.put("form_tel", "Phone Number");
                model.put("form_tel_e", "The phone number can only contain numbers and the maximum is 14 digits.");
                model.put("form_direccion", "Address");
                model.put("form_direccion_e", "You must fill this field.");
                model.put("form_producto", "Product");
                model.put("form_modelo", "Model");
                model.put("form_configuracion", "Configuration");
                model.put("form_mensaje", "Message");
                model.put("form_mensaje_e", "The message cannot be empty.");
                model.put("form_error", "Error:");
                model.put("form_error_m", " Please fill out this form correctly.");
                model.put("form_enviar", "Send");
                model.put("form_enviar_m", "Form submitted successfully!");
                break;
            case 3:
                model.put("title", "Albion - Home");
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

                model.put("titulo_contacto", "Nous Contacter");
                model.put("form_nombre", "Nom");
                model.put("form_nombre_e", "El nombre no puede contener símbolos. Debe completar este campo.");
                model.put("form_apellido", "Le Nom");
                model.put("form_apellido_e", "El apellido no puede contener símbolos. Debe completar este campo.");
                model.put("form_empresa", "Entreprise");
                model.put("form_empresa_e", "Debe completar este campo.");
                model.put("form_pais", "Pays");
                model.put("form_correo", "Courrier Électronique");
                model.put("form_correo_e", "Debe ingresar un mail válido.");
                model.put("form_tel", "Téléphone");
                model.put("form_tel_e", "El telefono solo puede contener numeros y el maximo son 14 dígitos.");
                model.put("form_direccion", "Adresse");
                model.put("form_direccion_e", "Debe completar este campo.");
                model.put("form_producto", "Produit");
                model.put("form_modelo", "Modèle");
                model.put("form_mensaje", "Message");
                model.put("form_mensaje_e", "El mensaje no puede estar vacío.");
                model.put("form_error", "Erreur:");
                model.put("form_error_m", " Veuillez remplir le formulaire correctement.");
                model.put("form_enviar", "Envoyer");
                model.put("form_enviar_m", "Formulaire envoyé avec succès !");
                break;
            default:
                model.put("title", "Albion - Contact Us");
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

                model.put("titulo_contacto", "Contate-Nos");
                model.put("form_nombre", "Nome");
                model.put("form_nombre_e", "El nombre no puede contener símbolos. Debe completar este campo.");
                model.put("form_apellido", "Sobrenome");
                model.put("form_apellido_e", "El apellido no puede contener símbolos. Debe completar este campo.");
                model.put("form_empresa", "Companhia");
                model.put("form_empresa_e", "Debe completar este campo.");
                model.put("form_pais", "País");
                model.put("form_correo", "Correio eletrônico");
                model.put("form_correo_e", "Debe ingresar un mail válido.");
                model.put("form_tel", "Telefone");
                model.put("form_tel_e", "El telefono solo puede contener numeros y el maximo son 14 dígitos.");
                model.put("form_direccion", "Direção");
                model.put("form_direccion_e", "Debe completar este campo.");
                model.put("form_producto", "Produto");
                model.put("form_modelo", "Modelo");
                model.put("form_mensaje", "Mensagem");
                model.put("form_mensaje_e", "El mensaje no puede estar vacío.");
                model.put("form_error", "Erro:");
                model.put("form_error_m", " Por favor, preencha o formulário corretamente.");
                model.put("form_enviar", "Mandar");
                model.put("form_enviar_m", "Formulário enviado com sucesso!");
                break;
        }
        
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
        
        model.put("idioma", idioma);
        
        model.put("producto", producto);
        model.put("modelo", modelo);
        return "contacto";
    }

    @GetMapping("/enviarMail/{idioma}")
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
        return "redirect:/contacto/{idioma}";
    }

}
