package app.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.service.CalculoService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CalculoControllerTest {
    //TESTES DE INTEGRAÇÃO
    @Mock
    private CalculoService calculoService;

    @InjectMocks
    private CalculoController calculoController;

    @Test
    @DisplayName("Cena 08 - Testando o método calcular com dados válidos")
    void cenario08() {
        Entrada entrada = new Entrada();
        entrada.setLista(Arrays.asList(10, 20, 30));

        Calculo calculoEsperado = new Calculo();
        calculoEsperado.setSoma(60);
        calculoEsperado.setMedia(20.0);
        calculoEsperado.setMediana(20.0);

        when(calculoService.calcular(entrada)).thenReturn(calculoEsperado);

        ResponseEntity<Calculo> resposta = calculoController.calcular(entrada);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(calculoEsperado, resposta.getBody());
    }
    // VERIFICA SE O CONTROLLER RETORNA A RESPOSTA CORRETA E A INTEGRAÇÃO ENTRE O CONTROLLER E O SERVICE

    @Test
    @DisplayName("Cena 09 - Testando o método calcular com erro no serviço")
    void cenario09() {
        Entrada entrada = new Entrada();
        entrada.setLista(Arrays.asList(10, null, 30));

        when(calculoService.calcular(entrada)).thenThrow(new RuntimeException("Erro ao calcular"));

        ResponseEntity<Calculo> resposta = null;
        
        try {
            resposta = calculoController.calcular(entrada);
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
            assertEquals("Erro ao calcular", e.getMessage());
        }
        
        assertNull(resposta);
    }
    // TESTA A RESPOSTA DO CONTROLLER QUANDO O SERVICE RETORNA UMA EXCEÇÃO
}