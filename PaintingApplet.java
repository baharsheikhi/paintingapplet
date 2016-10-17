/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintingapplet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author baharsheikhi
 */
public class PaintingApplet extends JPanel implements ActionListener, MouseListener, MouseMotionListener, ItemListener {
    JMenuBar menuBar;

    JMenu shapeSelection;
    JRadioButtonMenuItem line;
    JRadioButtonMenuItem rectangle;
    JRadioButtonMenuItem oval;
    String shape;
    ButtonGroup shapes;
    
    JMenu colorSelection;
    JRadioButtonMenuItem red;
    JRadioButtonMenuItem blue;
    JRadioButtonMenuItem green;
    Color color;
    ButtonGroup colors;
    
    JMenu thicknessSelection;
    JRadioButtonMenuItem thin;
    JRadioButtonMenuItem medium;
    JRadioButtonMenuItem thick;
    int thickness;
    ButtonGroup thicknesses;

    JButton clear;
   


    
    public PaintingApplet() {
    
        this.setPreferredSize(new Dimension(500, 500));
        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
        this.setLayout(layout);
        clear = new JButton("Clear");
        layout.setVgap(440);
        clear.setPreferredSize(new Dimension(50, 35));
        add(clear);
        clear.addActionListener(this);
        menuBar = new JMenuBar();
        
        shapeSelection = new JMenu("Shape selection");
        shapes = new ButtonGroup();
        line = new JRadioButtonMenuItem("Line");
        shapeSelection.add(line);
        rectangle = new JRadioButtonMenuItem("Rectangle");
        shapeSelection.add(rectangle);
        oval = new JRadioButtonMenuItem("Oval");
        shapeSelection.add(oval);
        
        line.addItemListener(this);
        rectangle.addItemListener(this);
        oval.addItemListener(this);
        
        menuBar.add(shapeSelection);
        shapes.add(line);
        shapes.add(rectangle);
        shapes.add(oval);
        
        colorSelection = new JMenu("Color Selection");
        colors = new ButtonGroup();
        red = new JRadioButtonMenuItem("Red");
        colorSelection.add(red);
        blue = new JRadioButtonMenuItem("Blue");
        colorSelection.add(blue);
        green = new JRadioButtonMenuItem("Green");
        colorSelection.add(green);
        
        red.addItemListener(this);
        blue.addItemListener(this);
        green.addItemListener(this);
        
        menuBar.add(colorSelection);
        colors.add(red);
        colors.add(blue);
        colors.add(green);
        
        thicknessSelection = new JMenu("Thickness");
        thicknesses = new ButtonGroup();
        thin = new JRadioButtonMenuItem("Thin");
        thicknessSelection.add(thin);
        medium = new JRadioButtonMenuItem("Medium");
        thicknessSelection.add(medium);
        thick = new JRadioButtonMenuItem("Thick");
        thicknessSelection.add(thick);
        
        thin.addItemListener(this);
        medium.addItemListener(this);
        thick.addItemListener(this);
        
        menuBar.add(thicknessSelection);
        thicknesses.add(thin);
        thicknesses.add(medium);
        thicknesses.add(thick);               

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PaintingApplet p = new PaintingApplet();
        p.createAndShowGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    public void createAndShowGUI() {
    //Create and set up the window.
    JFrame frame = new JFrame("Painting Applet");
    //frame.addMouseListener(this);
    frame.addMouseMotionListener(this);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Create and set up the content pane.
    this.setOpaque(true); //content panes must be opaque
    frame.setContentPane(this);
    //Display the window.
    frame.pack();
    frame.setVisible(true);
    frame.setJMenuBar(menuBar);
    }

    @Override
    public void mouseClicked(MouseEvent e) { 
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("mouse entered!" + e.getX() + "" + e.getY()); 
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("mouse exited!" + e.getX() + "" + e.getY()); 
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics graphics = getGraphics();
        if (shape == "Oval") {
            graphics.setColor(color);
            graphics.drawOval(e.getX(),e.getY(),thickness,thickness); 
            graphics.fillOval(e.getX(),e.getY(),thickness,thickness);
            
        }
        if (shape == "Rectangle") {
            graphics.setColor(color);
            graphics.drawRect(e.getX(),e.getY(),thickness,thickness); 
            graphics.fillRect(e.getX(),e.getY(),thickness,thickness);    
            
        }
        if (shape == "Line") {
            graphics.setColor(color);
            graphics.drawOval(e.getX(),e.getY(),thickness,thickness); 
            graphics.fillOval(e.getX(),e.getY(),thickness,thickness);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String selected = e.paramString();
        String toParse[] = selected.split("text");
        if (toParse[1].contains("Line") && e.getStateChange() == ItemEvent.SELECTED) {
            shape = "Line";
        }
        if (toParse[1].contains("Rectangle") && e.getStateChange() == ItemEvent.SELECTED) {
            shape = "Rectangle";
        }
        if (toParse[1].contains("Oval") && e.getStateChange() == ItemEvent.SELECTED) {
            shape = "Oval";
        }
        
        if (toParse[1].contains("Red") && e.getStateChange() == ItemEvent.SELECTED) {
            color = Color.red;
        }
        
        if (toParse[1].contains("Blue") && e.getStateChange() == ItemEvent.SELECTED) {
            color = Color.blue;
        }
        
        if (toParse[1].contains("Green") && e.getStateChange() == ItemEvent.SELECTED) {
            color = Color.green;
        }
        
        if (toParse[1].contains("Thin") && e.getStateChange() == ItemEvent.SELECTED) {
            thickness = 3;
        }
        
        if (toParse[1].contains("Medium") && e.getStateChange() == ItemEvent.SELECTED) {
            thickness = 7;
        }
        
        if (toParse[1].contains("Thick") && e.getStateChange() == ItemEvent.SELECTED) {
            thickness = 25;
        }
        
    }

}
