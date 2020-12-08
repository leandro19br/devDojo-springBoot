package br.com.devdojo.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component//mostra que a classe � m componente e deve ser gerenciada e injetada
//@Repository//especial quando estiver trabalhando com DAO para gerenciar exe��es
//@Service//igual a anota��o Componet ou utilizado na camada de servi�o
public class DateUtil {
    public String formatarData(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    }

}
