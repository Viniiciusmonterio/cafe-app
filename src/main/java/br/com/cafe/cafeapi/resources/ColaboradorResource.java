package br.com.cafe.cafeapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cafe.cafeapi.entities.Colaborador;
import br.com.cafe.cafeapi.repositories.ColaboradorRepository;

@RestController
@RequestMapping(value = "/colaboradores")
public class ColaboradorResource {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@GetMapping
	public ResponseEntity<List<Colaborador>> findAll(){
		List<Colaborador> list = colaboradorRepository.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Colaborador> findById(@PathVariable Long id){
		Colaborador colaborador = colaboradorRepository.findById(id).get();
		return ResponseEntity.ok().body(colaborador);
	}
	
	@PostMapping
	public Colaborador salvar(@RequestBody Colaborador colaborador) {
		return colaboradorRepository.save(colaborador);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Colaborador colaborador = colaboradorRepository.getById(id);

		if (colaborador == null) {
			return ResponseEntity.notFound().build();
		}

		colaboradorRepository.delete(colaborador);

		return ResponseEntity.noContent().build();
	}
	
	
}
