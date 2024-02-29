package com.cibertec.assessment.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.repo.SquareRepo;
import com.cibertec.assessment.service.PolygonService;
import com.cibertec.assessment.service.SquareService;

import jakarta.transaction.Transactional;

import com.cibertec.assessment.beans.PolygonBean;


@Service
public class SquareServiceImpl implements SquareService{

	@Autowired 
	SquareRepo squareRepo;
	
	@Autowired
	PolygonService polygonService;
	
	@Override
	public Square create(Square s) {
		return squareRepo.save(s); 
	}

	@Override
	public List<Square> list() {
		return squareRepo.findAll(); 
	}
	
	@Override
	public Square update(Square s) {
		// TODO Auto-generated method stub
		return squareRepo.save(s);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		squareRepo.deleteById(id);
	}
	
	// método para encontrar intersecciones entre cuadrados y polígonos	(con id=1)
	@Transactional
	public List<Integer> findIntersections() {
	    List<Integer> intersectionIds = new ArrayList<>();
	    List<PolygonBean> polygons = polygonService.list();
	    // Obtener el cuadrado con ID 1
	    Optional<Square> squareOpt = squareRepo.findById(1);

	    if (!squareOpt.isPresent()) {
	        // Manejo de cuadrado no encontrado
	        return Arrays.asList(-1);
	    }
	    Square square = squareOpt.get();
	    // Recorrer los polígonos
	    for (PolygonBean polygon : polygons) {
	        // Verificar si el cuadrado está dentro del polígono
	        if (isInside(polygon.getXPoints(), polygon.getYPoints(), square)) {
	            // Agregar el ID del polígono a la lista de intersecciones
	            intersectionIds.add(polygon.getId());
	        }
	    }//fin for....
	    // Actualizar el campo 'polygons' del cuadrado con los IDs encontrados
	    String polygonsIdsStr = intersectionIds.isEmpty() ? "No se halló intersecciones" : intersectionIds.stream()
	            .map(String::valueOf)
	            .collect(Collectors.joining(","));
	    square.setPolygons(polygonsIdsStr);
	    // Guardar los cambios en el cuadrado
	    squareRepo.save(square);
	    squareRepo.flush();
	    
	    // Devolver la lista de IDs de intersecciones, o un indicador de que no se encontraron
	    return intersectionIds.isEmpty() ? Arrays.asList(-1) : intersectionIds;
	    
	} // fin de método (findIntersections)...
	
	// Método mejorado para encontrar intersecciones entre cuadrados y polígonos (segundo intento, para indicar un ID)	
	@Transactional
	public Square findIntersectionsAndUpdateSquare(Integer squareId) {
	    List<Integer> intersectionIds = new ArrayList<>();
	    List<PolygonBean> polygons = polygonService.list();
	    // Obtener el cuadrado específico de la base de datos usando el squareId proporcionado
	    Optional<Square> squareOpt = squareRepo.findById(squareId);

	    if (!squareOpt.isPresent()) {
	        // Manejo de cuadrado no encontrado
	        throw new IllegalArgumentException("Cuadrado no encontrado con ID: " + squareId);
	    }
	    Square square = squareOpt.get();

	    // Recorrer todos los polígonos
	    for (PolygonBean polygon : polygons) {
	        // Verificar si el cuadrado está dentro del polígono
	        if (isInside(polygon.getXPoints(), polygon.getYPoints(), square)) {
	            // Agregar el ID del polígono a la lista de intersecciones
	            intersectionIds.add(polygon.getId());
	        }
	    }
	 // Actualiza el campo 'polygons' del cuadrado con los IDs encontrados, incluyendo corchetes
	    String polygonsIdsStr = intersectionIds.isEmpty() ? "No se halló intersecciones" : 
	                             "[" + intersectionIds.stream()
	                             .map(String::valueOf)
	                             .collect(Collectors.joining(",")) + "]";
	    square.setPolygons(polygonsIdsStr);
	    // Guardar los cambios en el cuadrado
	    Square updatedSquare = squareRepo.save(square);
	    squareRepo.flush();
	    // Devolver cuadrado actualizado
	    return updatedSquare;
	}

	// método para convertir una cadena de coordenadas en un array de enteros
    private Integer[] convertStringToIntegerArray(String coordinates) {
        // Eliminar los corchetes al inicio y al final de la cadena si están presentes
        String cleanedCoordinates = coordinates.replaceAll("\\[|\\]", "").trim();
        // Dividir la cadena en base a las comas para obtener los valores individuales
        String[] parts = cleanedCoordinates.split(",\\s*");
        // Convertir cada parte a Integer y almacenar en el array
        Integer[] intArray = new Integer[parts.length];
        for (int i = 0; i < parts.length; i++) {
            intArray[i] = Integer.parseInt(parts[i]);
        }
        return intArray;
    } // fin método convertStringToIntegerArray ....
    
    // método isInside para verificar si el cuadrado está dentro del polygono
    private boolean isInside(Integer[] xPoints, Integer[] yPoints, Square square) {

        // Convertir coordenadas del cuadrado a enteros
        Integer[] squareXPoints = convertStringToIntegerArray(square.getXPoints());
        Integer[] squareYPoints = convertStringToIntegerArray(square.getYPoints());

        // Encuentra los extremos del cuadrado
        int minXSquare = Collections.min(Arrays.asList(squareXPoints));
        int maxXSquare = Collections.max(Arrays.asList(squareXPoints));
        int minYSquare = Collections.min(Arrays.asList(squareYPoints));
        int maxYSquare = Collections.max(Arrays.asList(squareYPoints));

        // Encuentra los extremos del polígono
        int minXPolygon = Collections.min(Arrays.asList(xPoints));
        int maxXPolygon = Collections.max(Arrays.asList(xPoints));
        int minYPolygon = Collections.min(Arrays.asList(yPoints));
        int maxYPolygon = Collections.max(Arrays.asList(yPoints));

        // Verifica si el cuadrado está dentro del polígono
        return minXSquare >= minXPolygon && maxXSquare <= maxXPolygon && 
               minYSquare >= minYPolygon && maxYSquare <= maxYPolygon;
    } //fin método isInside ....

} //fin de método principal (SquareServiceImpl)...
