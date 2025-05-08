import app.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculoControllerTest {
    @MockBean
    CalculoRepository calculoRepository;

    @Autowired
    CalculoController calculoController;

    @Test
    @DisplayName("Cenário 08 - Testando o método findAll com o mock")
    void cenario08() {
        List<Calculo> lista = new ArrayList<>();
        List<Integer> listaValores = Arrays.asList(1, 1, 1);
        lista.add(new Calculo(1, listaValores, 3, 2.0, 2.0));
        lista.add(new Calculo(2, listaValores, 4, 2.0, 2.0));
        lista.add(new Calculo(3, listaValores, 5, 2.0, 2.0));

        when(calculoRepository.findAll()).thenReturn(lista);

        ResponseEntity<List<Calculo>> response = calculoController.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().size());
    }

    @Test
    @DisplayName("Cenário 09 - Testando o método findById com o mock")
    void cenario09() {
        List<Integer> listaValores = Arrays.asList(1, 1, 1, 1);
        Calculo calculo = new Calculo(1, listaValores, 50, 2.0, 2.0);

        when(calculoRepository.findById(1L)).thenReturn(Optional.of(calculo));

        ResponseEntity<Calculo> response = calculoController.findById(1L);
        assertEquals(50, response.getBody().getSoma());
    }
}