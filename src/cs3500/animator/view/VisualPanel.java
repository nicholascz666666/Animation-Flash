package cs3500.animator.view;

import cs3500.animator.model.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Visual Panel which used to work for Visual View and EditView taking the the job as painting.
 * Panel extends JPanel {@Code JPanel} using method {@Code paintComponent} to paint.
 */
public class VisualPanel extends JPanel {

  //Local variable
  private ArrayList<Shape> shapes;
  private int x;
  private int y;

  /**
   * Contructor of visual panel.
   */
  public VisualPanel() {
    super();
    this.shapes = new ArrayList<Shape>();
    this.setBackground(Color.white);
  }

  /**
   * setShapes that could change the array of shape for using.
   *
   * @param shapes inputted array list of shapes
   */
  public void setShapes(ArrayList<Shape> shapes) {
    this.shapes = shapes;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    AffineTransform originalTransform = g2d.getTransform();

    g2d.translate(1, this.getPreferredSize().getHeight());

    g2d.scale(0.5, 0.5);

    for (Shape shape : shapes) {
      g2d.setColor(new Color(shape.getFrame().getR(), shape.getFrame().getG(),
          shape.getFrame().getB()));

      if (shape.getType().equals("rectangle")) {
        g2d.fillRect(shape.getFrame().getX() - x, shape.getFrame().getY() - y,
            shape.getFrame().getW(), shape.getFrame().getH());
      } else {
        g2d.fillOval(shape.getFrame().getX() - x, shape.getFrame().getY() - y,
            shape.getFrame().getW(), shape.getFrame().getH());
      }


    }

    g2d.setTransform(originalTransform);
  }

  /**
   * Passing the value of left x and top Y.
   *
   * @param x left x
   * @param y top Y
   */
  public void setBound(int x, int y) {
    this.x = x;
    this.y = y;
  }

}
