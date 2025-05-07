package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dto.Entrada;
import app.entity.Calculo;
import app.service.CalculoService;


@RestController
@RequestMapping("/api/calculos")
@CrossOrigin("*")
public class CalculoController {
	@Autowired
	private CalculoService calculoService;

	@GetMapping
    public ResponseEntity<List<Calculo>> findAll() {
        return ResponseEntity.ok(calculoService.findAll());
    }

	@GetMapping("/{id}")
	public ResponseEntity<Calculo> findById(long id) {
		return ResponseEntity.ok(calculoService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Calculo> calcular(@RequestBody Entrada entrada){
		Calculo calculo = this.calculoService.calcular(entrada);
		return new ResponseEntity<>(calculo, HttpStatus.OK);
	}
}