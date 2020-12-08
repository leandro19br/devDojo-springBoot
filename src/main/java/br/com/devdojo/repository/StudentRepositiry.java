package br.com.devdojo.repository;

import br.com.devdojo.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/*CrudRepository<Student, Long> Student é a entidade e Long o id
* CrudRepository já tem alguns métodos basicos de CRUD
* PagingAndSortingRepository - será utilizao pois existe métodos de paginação */
public interface StudentRepositiry extends PagingAndSortingRepository<Student, Long> {

    /*pode se criar uma outra forma de se fazer uma busca, como por exemplo por nome
    * pelo nome do atributo o Spring se encarrega de fazer a query*/

    List<Student> findByNomeIgnoreCaseContaining(String name);

}
