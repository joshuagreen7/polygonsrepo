package com.cibertec.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.service.imp.SquareServiceImpl;

@RestController
@RequestMapping("/square")
public class SquareController {

    @Autowired
    private SquareServiceImpl squareService;

    //Cuando hay un cuadrado
    @GetMapping("/intersections")
    public List<Integer> findIntersections() {
        return squareService.findIntersections();
    }
    
    @GetMapping
	public ResponseEntity<List<Square>> list(){
		return new ResponseEntity<>(squareService.list(), HttpStatus.OK);
	}
  	//Eligr un Cuadrado por su ID
	@GetMapping("/intersections/{squareId}") public Square
	findIntersections(@PathVariable Integer squareId) { return
	squareService.findIntersectionsAndUpdateSquare(squareId); }
	
	@PostMapping
	public ResponseEntity<Square> create(@RequestBody Square s) {
		return new ResponseEntity<>(squareService.create(s), HttpStatus.CREATED);
	}
	@PutMapping
	public ResponseEntity<Square> update(@RequestBody Square s) {
		return new ResponseEntity<>(squareService.update(s), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		squareService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);		
	}
	
	
	 
}
