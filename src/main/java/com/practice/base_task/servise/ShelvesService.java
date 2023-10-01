package com.practice.base_task.servise;

import com.practice.base_task.dto.BookshelfDTO;
import com.practice.base_task.model.Bookshelf;
import com.practice.base_task.repository.ShelvesRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShelvesService {
    private final ModelMapper modelMapper;
    private final ShelvesRepository shelvesRepository;

    public Bookshelf findShelfById(Long id) {
        return shelvesRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Bookshelf not found - " + id));
    }

    public List<Bookshelf> findAllShelves() {
        return shelvesRepository.findAll();
    }

    public BookshelfDTO createOrUpdateBookshelf(BookshelfDTO bookshelfDTO) {
        Bookshelf bookshelf = modelMapper.map(bookshelfDTO, Bookshelf.class);
        Bookshelf savedBookshelf = shelvesRepository.save(bookshelf);
        return modelMapper.map(savedBookshelf, BookshelfDTO.class);
    }

    public void deleteBookshelf(Long id) {
        shelvesRepository.deleteById(id);
    }

    public Bookshelf convertBookshelfDtoToEntity(BookshelfDTO bookshelfDTO) {
        return modelMapper.map(bookshelfDTO, Bookshelf.class);
    }

    public BookshelfDTO convertEntityToBookshelfDTO(Bookshelf bookshelf) {
        return modelMapper.map(bookshelf, BookshelfDTO.class);
    }
}
