
package com.egg.News.controladores;

import com.egg.News.entidades.Noticia;
import com.egg.News.excepciones.MiException;
import com.egg.News.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/noticias")
public class NoticiaControlador {
    
    @Autowired
    private NoticiaServicio noticiaServicio;
    
    @GetMapping("/")
    public String listar(ModelMap modelo)  {
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);   
        return "noticias.html";
    }
    
    
    
    @GetMapping("/registrar") //localhost:8080/noticia/registrar
    public String registrar(){
        return "noticia_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String titulo,@RequestParam String img, @RequestParam String cuerpo, ModelMap modelo){
        
        try {
            noticiaServicio.crearNoticia(titulo, img, cuerpo);
            
            modelo.put("exito", "La Noticia fue registrada correctamente!");
        } catch (MiException ex) {
                       
            modelo.put("error", ex.getMessage());
            return "noticia_form.html";
        }
        
        return "index.html";        
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable int id, ModelMap modelo) {
      
        modelo.put("noticia", noticiaServicio.getOne(id));
        
       
        return "noticia_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable int id, String titulo,String img, String cuerpo, ModelMap modelo) {
        try {
            

            noticiaServicio.modificarNoticia(id, titulo, img, cuerpo);
            
                        
            return "redirect:../";

        } catch (MiException ex) {
            
            
            modelo.put("error", ex.getMessage());
            
            
            
            return "noticia_modificar.html";
        }

    }
}
