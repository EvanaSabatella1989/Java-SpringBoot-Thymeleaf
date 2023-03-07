
package com.egg.News.repositorios;

import com.egg.News.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface NoticiaRepositorio extends JpaRepository<Noticia, Integer> {
    
    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    public Noticia buscarPorTitulo(@Param("titulo") String titulo);
}
