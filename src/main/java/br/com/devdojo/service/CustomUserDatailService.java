package br.com.devdojo.service;

import br.com.devdojo.model.User;
import br.com.devdojo.repository.UseRepository;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/*customizando o m�todo da classe UserDetail*/
@Component//para o Spring gerenciar as depend�ncias
public class CustomUserDatailService implements UserDetailsService {
    /*vamos usar para buscar o usu�rio no BD*/
    private final UseRepository DAO;

    @Autowired
    public CustomUserDatailService(UseRepository DAO) {
        this.DAO = DAO;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //utilizando Optional se for null o nome ent�o lan�a uma exeption
        User user = Optional.ofNullable(DAO.findByUserName(userName))
                .orElseThrow(() -> new UsernameNotFoundException("User not Found"));

        //lista de autoriza��es ADD ROLE ANTES DA PALAVRA
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
        //se for admin usar a lista de admin sen�o usar a lista de usuario
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),user.isAdmin() ? authorityListAdmin :authorityListUser);
    }
}
