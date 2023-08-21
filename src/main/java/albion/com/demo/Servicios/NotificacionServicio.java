package albion.com.demo.Servicios;

import albion.com.demo.Errores.ErrorServicio;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServicio {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void enviar(String cuerpo, String titulo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo("joacalde3@gmail.com");
        mensaje.setFrom("joacalde3@gmail.com");
        mensaje.setSubject(titulo);
        mensaje.setText(cuerpo);

        mailSender.send(mensaje);
    }

    @Async
    public void enviarHtml(String nombre, String apellido, String empresa, String pais, String correo, String telefono, String direccion, int producto, String modelo, String mensaje) throws MessagingException, ErrorServicio {

        validar(nombre, apellido, correo, telefono, mensaje);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("joacalde3@gmail.com");
        helper.setTo("joacalde3@gmail.com");

        String titulo = "De: " + correo + ", consulta de página Albion";

        helper.setSubject(titulo);
        
        String producto2 = "";

        String content
                = "<b>Nombre: </b>" + nombre + "<br/>"
                + "<b>Apellido: </b>" + apellido + "<br/>"
                + "<b>Empresa: </b>" + empresa + "<br/>"
                + "<b>Pais: </b>" + pais + "<br/>"
                + "<b>Correo: </b>" + correo + "<br/>"
                + "<b>Telefono: </b>" + telefono + "<br/>"
                + "<b>Direccion: </b>" + direccion + "<br/>"
                + "<b>Producto: </b>" + producto2 + "<br/>"
                + "<b>Modelo: </b>" + modelo + "<br/>"
                + "<b>Mensaje: </b>" + mensaje;

        helper.setText(content, true);

        mailSender.send(message);

    }

    public void validar(String nombre, String apellido, String correo, String telefono, String mensaje) throws ErrorServicio {
        if (nombre.isEmpty()) {
            throw new ErrorServicio("Debe completar nombre, apellido, correo y mensaje.");
        }
        if (apellido.isEmpty()) {
            throw new ErrorServicio("Debe completar nombre, apellido, correo y mensaje.");
        }
        if (mensaje.isEmpty()) {
            throw new ErrorServicio("Debe completar nombre, apellido, correo y mensaje.");
        }
        if (correo.isEmpty()) {
            throw new ErrorServicio("Debe completar nombre, apellido, correo y mensaje.");
        }
    }
}