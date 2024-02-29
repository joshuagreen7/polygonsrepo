package com.cibertec.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.assessment.model.Polygon;
import com.cibertec.assessment.service.imp.PolygonServiceImpl;

@RestController
@RequestMapping("/polygon")
public class PolygonController {
	
	@Autowired
	private PolygonServiceImpl polygonService;
	
	@GetMapping
	public ResponseEntity<List<Polygon>> listP(){
	return new ResponseEntity<>(polygonService.listP(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Polygon> add(@RequestBody Polygon p) {
		return new ResponseEntity<>(polygonService.add(p), HttpStatus.CREATED);
	}
	
}
