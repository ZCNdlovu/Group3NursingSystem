package za.ac.cput.service;

import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Document;

import java.util.List;

public interface IService<T,ID>{
    T create(T t);

    T read (ID id );

    T update (T t);

    List<T> getAll();

 }
