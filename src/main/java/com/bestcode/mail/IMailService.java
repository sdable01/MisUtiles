package com.bestcode.mail;
import java.io.File;  
import javax.mail.internet.InternetAddress;
  
public interface IMailService {  

      
    public void send(String from, String[] to, String subject, String text, File... attachments) ;  
    
    public boolean send(InternetAddress from, String destinatarioError, String[] to, String[] cc, String[] cco, IEmailMessageProvider messageProvider, File... attachments);    
    
    public void sendBasico(String from, String asunto, String mensaje);
} 