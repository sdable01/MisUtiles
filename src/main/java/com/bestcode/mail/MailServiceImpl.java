package com.bestcode.mail;

import com.bestcode.misutiles.UtilesString;
import com.sun.mail.smtp.SMTPAddressFailedException;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.BodyPart;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
//import org.slf4j.Logger; 
//import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Servicio de envío de emails
 */
@Service("mailService")
public class MailServiceImpl implements IMailService {

    private JavaMailSenderImpl mailSender; // wrapper de Spring sobre javax.mail
    public boolean active = true; //flag para indicar si está activo el servicio
    private String from; //correo electrónico del remitente
        private String emailDesarrollador; //dirección de correo a donde se enviarán los errores de envío.

    @Autowired
    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmailDesarrollador() {
        return emailDesarrollador;
    }

    public void setEmailDesarrollador(String emailDesarrollador) {
        this.emailDesarrollador = emailDesarrollador;
    }
    private static final File[] NO_ATTACHMENTS = null;

    public void send(String from, String[] to, String subject, String text, File... attachments) {
        // chequeo de parámetros           
        Assert.hasLength(to[0], "email 'to' needed");
        Assert.hasLength(subject, "email 'subject' needed");
        Assert.hasLength(text, "email 'text' needed");

        // asegurando la trazabilidad  
//        if (log.isDebugEnabled()) {  
//            final boolean usingPassword = !"".equals(mailSender.getPassword());  
//            log.debug("Sending email to: '" + to + "' [through host: '" + mailSender.getHost() + ":"  
//                    + mailSender.getPort() + "', username: '" + mailSender.getUsername() + "' usingPassword:"  
//                    + usingPassword + "].");  
//            log.debug("isActive: " + active);  
//        }  
        // el servicio esta activo?  
        if (!active) {
            return;
        }

        // plantilla para el envío de email  
        final MimeMessage message = mailSender.createMimeMessage();

        try {
            // el flag a true indica que va a ser multipart  
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // settings de los parámetros del envío  
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(from);   //Asigno el emisor
            helper.setText(text);

            // adjuntando los ficheros  
            if (attachments != null) {
                for (int i = 0; i < attachments.length; i++) {
                    FileSystemResource file = new FileSystemResource(attachments[i]);
                    helper.addAttachment(attachments[i].getName(), file);
//                    if (log.isDebugEnabled()) {  
//                        log.debug("File '" + file + "' attached.");  
//                    }  
                }
            }

        } catch (MessagingException e) {
            new RuntimeException(e);
        }

        try {
            // el envío          
            this.mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Error al enviar el mensaje: " + e.getMessage() + " Se notificará del error a " + emailDesarrollador);
            this.sendBasico(from,
                    "ERROR ENVIANDO EMAIL",
                    "ERROR ENVIANDO EMAIL (" + subject + ") A LOS DESTINATARIOS (to)" + UtilesString.desfragmentarCadenaTexto(to)
                    + "\nError: " + e.getMessage());
        }
    }

    @Override
    /**
     *
     * @param from - Remitente.
     * @param to - Destinatarios.
     * @param cc - Destinatarios copiados.
     * @param cco - Destinatarios con copia oculta.
     * @param messageProvider - Proveedor de mensaje.
     * @param attachments - Archivo adjunto. Este parámetro puede ser omitido.
     * @throws SMTPAddressFailedException - Exception lanzada si alguno de los
     * destinatarios no existe.
     */
    public boolean send(InternetAddress from, String destinatarioError, String[] to, String[] cc, String[] cco, IEmailMessageProvider messageProvider, File... attachments) {
        try {
            // chequeo de parámetros   
            Assert.hasLength(to[0], "email 'to' needed");
            Assert.notNull(messageProvider);

            // asegurando la trazabilidad  
//            if (log.isDebugEnabled()) {
//                final boolean usingPassword = !"".equals(mailSender.getPassword());
//                log.debug("Sending email to: '" + to + "' [through host: '" + mailSender.getHost() + ":"
//                        + mailSender.getPort() + "', username: '" + mailSender.getUsername() + "' usingPassword:"
//                        + usingPassword + "].");
//                log.debug("isActive: " + active);
//            }
            // el servicio esta activo?  
            if (!active) {
                System.out.println("}}}}}}}} La wea no está activa");
                return false;
            }

            // plantilla para el envío de email  
            final MimeMessage message = mailSender.createMimeMessage();
            // PREPARE THE IMAGE
            // Prepare a multipart HTML
//            Multipart multipart = new MimeMultipart();
//            BodyPart imgPart = new MimeBodyPart();
//            imgPart.setHeader("Content-ID", "<cabecera>");
//            imgPart.setHeader("Content-ID", "<fonos>");
//            
//            multipart.addBodyPart(imgPart);
//            message.setContent(multipart);
            

            // el flag a true indica que va a ser multipart  
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);            
            
            
            helper.setSubject(messageProvider.getSubject());
            helper.setFrom(from);   //Asigno el emisor
            
            if (cc != null) {
                helper.setCc(cc);
            }

            if (cco != null) {
                helper.setBcc(cco);
            }

            // el flag a true indica que el cuerpo del mensaje es HTML  
            helper.setText(messageProvider.getBody(), true);

            // añadiendo los ficheros "en línea"  
            if (messageProvider.getInlineFiles() != null) {
                for (String key : messageProvider.getInlineFiles().keySet()) {
                    Resource value = messageProvider.getInlineFiles().get(key);
                    helper.addInline(key, value);
//                    if (log.isDebugEnabled()) {
//                        log.debug("File '" + value + "' added.");
//                    }
                }
            }

            // adjuntando los ficheros  
            if (attachments != null) {
                for (int i = 0; i < attachments.length; i++) {
                    FileSystemResource file = new FileSystemResource(attachments[i]);
                    helper.addAttachment(attachments[i].getName(), file);
//                    if (log.isDebugEnabled()) {  
//                        log.debug("File '" + file + "' attached.");  
//                    }  
                }
            }

            // Si el destinatario es null
            if (to == null) {
                to = cc;
                System.out.println("EL DESTINATARIO NO POSEE EMAIL, SE ENVIARÁ AL USUARIO ADMINISTRADOR (COPIA)");
            } 

            // el envío          
            this.mailSender.send(message);
            return true;
        } catch (SMTPAddressFailedException e) {
            System.out.println("Error al enviar el mensaje: " + e.getMessage() + " Se notificará del error a " + emailDesarrollador);
            this.sendBasico(destinatarioError,
                    "ERROR ENVIANDO EMAIL",
                    "ERROR ENVIANDO EMAIL (" + messageProvider.getSubject() + ") A LOS DESTINATARIOS (to)" + UtilesString.desfragmentarCadenaTexto(to)
                    + " (cc)" + (cc == null ? "Sin copias" : UtilesString.desfragmentarCadenaTexto(cc))
                    + " (cco)" + (cco == null ? "Sin copias ocultas" : UtilesString.desfragmentarCadenaTexto(cco))
                    + "\nError: " + e.getMessage());
            return false;
        } catch (MessagingException e) {
            new RuntimeException(e);
            return false;
        } catch (Exception e) {
            System.out.println("Error al enviar el mensaje: " + e.getMessage() + " Se notificará del error a " + emailDesarrollador);
            this.sendBasico(destinatarioError,
                    "ERROR ENVIANDO EMAIL",
                    "ERROR ENVIANDO EMAIL (" + messageProvider.getSubject() + ") A LOS DESTINATARIOS (to)" + UtilesString.desfragmentarCadenaTexto(to)
                    + " (cc)" + (cc == null ? "Sin copias" : UtilesString.desfragmentarCadenaTexto(cc))
                    + " (cco)" + (cco == null ? "Sin copias ocultas" : UtilesString.desfragmentarCadenaTexto(cco))
                    + "\nError: " + e.getMessage());
            return false;
        }

    }

    /**
     * Mensaje básico destinado a la dirección de la propiedad
     * emailDesarrollador, está orientado principalmente para notificar errores
     * en el envío de mensajes. El valor de la propiedad emailDesarrollador se
     * asigna en el archivo application.mail.xml (Spring). Ejemplo:
     * <bean id="mailService" class="com.elvolcan.utiles.mail.MailServiceImpl">
     * <property name="active" value="true"/>
     * <property name="mailSender" ref="mailSender"/>
     * <property name="from" value="${mail.username}"/>
     * <property name="emailDesarrollador" value="${mail.desarrollador}"/>
     * </bean>
     *
     * @param from - Desde
     * @param asunto - Asunto
     * @param mensaje - Mensaje
     */
    @Override
    public void sendBasico(String from, String asunto, String mensaje) {
        // chequeo de parámetros   
        Assert.hasLength(emailDesarrollador, "email 'to' needed");

        // asegurando la trazabilidad  
//        if (log.isDebugEnabled()) {
//            final boolean usingPassword = !"".equals(mailSender.getPassword());
//            log.debug("Sending email to: '" + emailDesarrollador + "' [through host: '" + mailSender.getHost() + ":"
//                    + mailSender.getPort() + "', username: '" + mailSender.getUsername() + "' usingPassword:"
//                    + usingPassword + "].");
//            log.debug("isActive: " + active);
//        }
        // el servicio esta activo?  
        if (!active) {
            return;
        }

        // plantilla para el envío de email  
        final MimeMessage message = mailSender.createMimeMessage();

        try {
            // el flag a true indica que va a ser multipart  
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailDesarrollador);
            helper.setSubject(asunto);
            helper.setFrom(from);   //Asigno el emisor

            // el flag a true indica que el cuerpo del mensaje es HTML  
            helper.setText(mensaje);

        } catch (MessagingException e) {
            new RuntimeException(e);
        }

        // Si el destinatario es null
        if (emailDesarrollador == null) {
            System.out.println("EL DESTINATARIO NO POSEE EMAIL, SE ENVIARÁ AL USUARIO ADMINISTRADOR (COPIA)");
        }

        // el envío          
        this.mailSender.send(message);

    }

    private boolean validateEmail(String email) {
        boolean isValid = false;
        try {
            //Create InternetAddress object and validated the email address.
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            isValid = true;

        } catch (AddressException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    private void printStatus(String email, boolean valid) {
        System.out.println(email + " is " + (valid ? "a" : "not a")
                + " valid email address");
    }

    public boolean validacionMail(String email) {
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        // El email a validar
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }
    
}
