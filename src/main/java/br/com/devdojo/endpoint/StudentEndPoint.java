package br.com.devdojo.endpoint;

import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController//anota��o do Spring MVC ou Controller @RestController coloca automatico o @ResponseBody retorna valores
@RequestMapping("v1")//para acessar a API pelo navegador
public class StudentEndPoint {

   /* @Autowired//faz a injeção de dependencia e estancia o objeto
    private DateUtil dateUtil;*/

    private final StudentRepositiry studentDAO;

    /*ao momento de criar o objeto, o studentDAO será injetado*/
    @Autowired
    public StudentEndPoint(StudentRepositiry studentDAO) {
        this.studentDAO = studentDAO;
    }


    //@RequestMapping( method = RequestMethod.GET)//tipo de requisi��o GET e path para acessar
    //Pageable paginação
    @GetMapping(path = "protected/students")
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(studentDAO.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "protected/students/findByName/{nome}")
    public ResponseEntity<?> findStudentByName(@PathVariable("nome") String nome) {
        return new ResponseEntity<>(studentDAO.findByNomeIgnoreCaseContaining(nome), HttpStatus.OK);
    }

    //indepotent
    //@RequestMapping( method = RequestMethod.GET, path = "/{id}")//passando parâmetro id
    //@AuthenticationPrincipal UserDetails details mostra detalhes de autenticação do usuáriio chamando a requisição
    @GetMapping(path = "protected/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails details) {
        System.out.println(details.getUsername());
        Optional<Student> byId = studentDAO.findById(id);
        verificaSeExsiteEstudante(id);//valida se o estudante existe
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    //@RequestMapping( method = RequestMethod.POST)//dentro do Bady dessa requisição tem que vir um objeto studant
    // @Transactional faz o controle de transação se for lençar uma exeção checada tem que ser explicito
    //@Transactional(rollbackFor = Exception.class)
    @PostMapping(path = "admin/students")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }

    //indepotent
    //@RequestMapping( method = RequestMethod.DELETE)
    @DeleteMapping(path = "admin/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        verificaSeExsiteEstudante(id);
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@RequestMapping( method = RequestMethod.PUT)
    @PutMapping(path = "admin/students")
    public ResponseEntity<?> update(@RequestBody Student student) {
        /*Se student estiver com o id será atualizado se não tiver id será criado*/
        verificaSeExsiteEstudante(student.getId());
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verificaSeExsiteEstudante(Long id) {
        final Optional<Student> byId = studentDAO.findById(id);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("Estudante não encontrado " + id);
        }
    }


}
