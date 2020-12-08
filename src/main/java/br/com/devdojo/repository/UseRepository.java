package br.com.devdojo.repository;

import br.com.devdojo.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
/*vai azer a busca no BD*/
public interface UseRepository  extends PagingAndSortingRepository<User, String> {

    User findByUserName(String userName);

}
