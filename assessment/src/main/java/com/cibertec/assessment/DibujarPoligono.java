package com.cibertec.assessment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DibujarPoligono extends JPanel {
	// 1st Polygon Coordenadas de los vértices del polígono 1 (el más amplio - color negro)
    Double xpoint1[] = {10.0, 205.0, 305.0, 405.0, 500.0};
    Double ypoint1[] = {10.0, 501.0, 506.0, 107.0, 30.0};

    //SQUARE 1 (intercepta poligonos 1,2,4,5)
    Double xpoint2[] = {100.0, 200.0, 200.0, 100.0};
    Double ypoint2[] = {100.0, 100.0, 200.0, 200.0};
    
    //2nd polygon forma de z, (pink)
    Double xpoint5[] = {100.0, 605.0, 305.0, 405.0, 500.0};
    Double ypoint5[] = {100.0, 601.0, 506.0, 337.0, 300.0};
    
    //3th polygon (red)
    Double xpoint3[] = {250.0, 350.0, 450.0, 400.0, 300.0};
    Double ypoint3[] = {50.0, 50.0, 150.0, 200.0, 150.0};
    
    //4th polygon (magenta 1)
    Double xpoint4[] = {70.0, 200.0, 300.0, 200.0, 100.0};
    Double ypoint4[] = {150.0, 50.0, 150.0, 250.0, 200.0};
    
    //5th polygon (blue)
    Double xpoint6[] = {90.0, 210.0, 210.0, 90.0, 50.0};
    Double ypoint6[] = {40.0, 90.0, 210.0, 210.0, 50.0};
    
    //6th polygon  (magenta 2) 
    Double xpoint7[] = {300.0, 400.0, 400.0, 300.0, 350.0};
    Double ypoint7[] = {300.0, 300.0, 400.0, 400.0, 350.0};

    //SQUARE 2 (intercepta poligonos 1,3)
    Double xpoint8[] = {350.0, 450.0, 450.0, 350.0};
    Double ypoint8[] = {50.0, 50.0, 150.0, 150.0};
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Dibujar el polígono 1
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawPolygon(toIntArray(xpoint1), toIntArray(ypoint1), xpoint1.length);
    
        // Dibujar el polígono 2 (forma de z)
        g2d.setColor(Color.pink);
        g2d.drawPolygon(toIntArray(xpoint5), toIntArray(ypoint5), xpoint5.length);
        
        // Dibujar el polígono 3
        g2d.setColor(Color.red);
        g2d.drawPolygon(toIntArray(xpoint3), toIntArray(ypoint3), xpoint3.length);
        
        // Dibujar el polígono 4
        g2d.setColor(Color.magenta);
        g2d.drawPolygon(toIntArray(xpoint4), toIntArray(ypoint4), xpoint4.length);
        
        // Dibujar el cuadrado 1
        g2d.setColor(Color.ORANGE);
        //g2d.drawPolygon(cuadradoXPoints, cuadradoYPoints, cuadradoXPoints.length);
        g2d.drawPolygon(toIntArray(xpoint2), toIntArray(ypoint2), xpoint2.length);
    
        // Dibujar el polígono 5
        g2d.setColor(Color.blue);
        g2d.drawPolygon(toIntArray(xpoint6), toIntArray(ypoint6), xpoint6.length);
    
        // Dibujar el polígono 6
        g2d.setColor(Color.magenta);
        g2d.drawPolygon(toIntArray(xpoint7), toIntArray(ypoint7), xpoint7.length);
        
        // Dibujar el cuadrado 2
        g2d.setColor(Color.orange);
        g2d.drawPolygon(toIntArray(xpoint8), toIntArray(ypoint8), xpoint8.length);
    
    }
    // Método para convertir Double[] a int[]
    private int[] toIntArray(Double[] array) {
        int[] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            intArray[i] = array[i].intValue();
        }
        return intArray;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dibujar Polígono");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new DibujarPoligono());
            frame.setSize(700, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
