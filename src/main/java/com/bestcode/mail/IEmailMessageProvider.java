package com.bestcode.mail;

import java.util.Map;  
  
import org.springframework.core.io.Resource;  
  
public interface IEmailMessageProvider {  
      
    public String getSubject();  
      
    public String getBody();  
      
    public Map<String, Resource> getInlineFiles();  
  
}  