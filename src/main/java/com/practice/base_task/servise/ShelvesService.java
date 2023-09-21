package com.practice.base_task.servise;

import com.practice.base_task.model.Bookshelf;
import com.practice.base_task.repository.ShelvesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShelvesService {

    private final ShelvesRepository shelvesRepository;
    private final NamedParameterJdbcTemplate template;

    @Autowired
    public ShelvesService(ShelvesRepository shelvesRepository, NamedParameterJdbcTemplate template) {
        this.shelvesRepository = shelvesRepository;
        this.template = template;
    }

    public Bookshelf findShelfById(Long id) {
        return shelvesRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Bookshelf not found - " + id));
    }

    public List<Bookshelf> findAllShelves(){
        return shelvesRepository.findAll();
    }

    public Bookshelf saveBookshelf(Bookshelf bookshelf){
       return shelvesRepository.save(bookshelf);
    }

//    public void updateBookshelf(Bookshelf bookshelf){
//        String sql = "UPDATE bookshelves set shelf_name = :shelfName, location = :location where shelf_id = :id";
//        SqlParameterSource parameterSource = new MapSqlParameterSource()
//                .addValue("id", bookshelf.getShelfId())
//                .addValue("shelfName", bookshelf.getShelfName())
//                .addValue("location", bookshelf.getLocation());
//        template.update(sql, parameterSource);
//    }
    public Bookshelf updateBookshelf(Bookshelf bookshelf){
        return shelvesRepository.save(bookshelf);
    }

    public void deleteBookshelf(Long id){
        shelvesRepository.deleteById(id);
    }
}
