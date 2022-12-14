package com.indocyber.Services.Implementations;

import com.indocyber.DTO.Author.AuthorDto;
import com.indocyber.DTO.Author.UpsertAuthorDTO;
import com.indocyber.Entities.Author;
import com.indocyber.ExceptionHandling.ObjectNotFound;
import com.indocyber.Repositories.AuthorRepository;
import com.indocyber.Services.Interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServe implements AuthorService {

    private AuthorRepository authorRepository;

    private int ROWS_PER_PAGE = 5;

    @Autowired
    public AuthorServe(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public void addAuthor(AuthorDto authorDto) {

        Author newAuthor = new Author();
        newAuthor.setId(authorDto.getId());
        newAuthor.setTitle(authorDto.getTitle());
        newAuthor.setFirstName(authorDto.getFirstName());
        newAuthor.setLastName(authorDto.getLastName());
        newAuthor.setBirthDate(authorDto.getBirthDate());
        newAuthor.setDeceasedDate(authorDto.getDeceasedDate());
        newAuthor.setEducation(authorDto.getEducation());
        newAuthor.setSummary(authorDto.getSummary());

        authorRepository.save(newAuthor);
    }

    @Override
    public void addAuthor(UpsertAuthorDTO upsertAuthorDTO) {
        Author newAuthor = new Author();
        newAuthor.setId(upsertAuthorDTO.getId());
        newAuthor.setTitle(upsertAuthorDTO.getTitle());
        newAuthor.setFirstName(upsertAuthorDTO.getFirstName());
        newAuthor.setLastName(upsertAuthorDTO.getLastName());
        newAuthor.setBirthDate(upsertAuthorDTO.getBirthDate());
        newAuthor.setDeceasedDate(upsertAuthorDTO.getDeceasedDate());
        newAuthor.setEducation(upsertAuthorDTO.getEducation());
        newAuthor.setSummary(upsertAuthorDTO.getSummary());

        authorRepository.save(newAuthor);
    }

    @Override
    public List<AuthorDto> findByName(String name, int page) {

        Pageable pagination = PageRequest.of(page - 1, ROWS_PER_PAGE, Sort.by("id") );

        return authorRepository.findByName(name,pagination);
    }

    @Override
    public List<AuthorDto> findByName(String name) {

        List<AuthorDto> filteredAuthor = new ArrayList<>();

        List<Author> allAuthor = authorRepository.findByNameSingle(name);

        for (Author author : allAuthor){
                AuthorDto authorDto = new AuthorDto();
                Integer age;

                authorDto.setId(author.getId());
                authorDto.setTitle(author.getTitle());
                authorDto.setFullName(
                       " " + author.getFirstName() + " " + author.getLastName()
                );

                if(author.getDeceasedDate() != null){
                    authorDto.setStatus("Alive");
                    age = (int) ChronoUnit.YEARS.between(author.getBirthDate(), LocalDate.now());
                    authorDto.setAge(age);
                } else {
                    authorDto.setStatus("Deceased");
                    age = (int) ChronoUnit.YEARS.between(author.getBirthDate(),author.getDeceasedDate());
                    authorDto.setAge(age);
                }

                authorDto.setEducation(author.getEducation());
                filteredAuthor.add(authorDto);
        }
        return filteredAuthor ;
    }

    @Override
    public void deleteAuthor(Author author) {
        this.authorRepository.delete(author);
    }

    @Override
    public Page<Author> findAllAuthor(Integer page) {

        Pageable pageable = PageRequest.of(page - 1 , ROWS_PER_PAGE, Sort.by("id"));

        return authorRepository.findAll(pageable);
    }

    @Override
    public List<Author> findAll(Integer page) {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    @Override
    public List<AuthorDto> findAllAuthorDto(int page) {
        List<Author> authors = authorRepository.findAll(PageRequest.of(page - 1, 10)).getContent();
        List<AuthorDto> authorTableDtos = new ArrayList<>();
        authors.forEach(author -> authorTableDtos.add(new AuthorDto(author)));
        return authorTableDtos;
    }

    @Override
    public UpsertAuthorDTO updateAuthor(Integer id) {
        Optional<Author> nullEntity = authorRepository.findById(id);
        Author entity = nullEntity.get();
        UpsertAuthorDTO updateDto = new UpsertAuthorDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthDate(),
                entity.getDeceasedDate(),
                entity.getEducation(),
                entity.getSummary()
        );

        return updateDto ;
    }

    @Override
    public AuthorDto findById(Integer id) {
        AuthorDto authorDto = new AuthorDto();

        Author entity = authorRepository.findById(id).get();
//                .orElseThrow(()->{
//                    throw new ObjectNotFound("Author Id : " + id + "Not Found/ not exist!");
//                });

        authorDto.setId(entity.getId());
        authorDto.setEducation(entity.getEducation());
        authorDto.setFirstName(entity.getFirstName());
        authorDto.setLastName(entity.getLastName());
        authorDto.setBirthDate(entity.getBirthDate());
        authorDto.setSummary(entity.getSummary());
        authorDto.setFullName(entity.getFirstName() + entity.getLastName());

        return authorDto;
    }

    @Override
    public List<AuthorDto> findAllAuthorDto(Integer page) {
        List<Author> authors = authorRepository.findAll(PageRequest.of(page - 1, 10)).getContent();
        List<AuthorDto> authorTableDtos = new ArrayList<>();
        authors.forEach(author -> authorTableDtos.add(new AuthorDto(author)));
        return authorTableDtos;
    }
}
