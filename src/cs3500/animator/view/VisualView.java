package cs3500.animator.view;

import cs3500.animator.model.KeyFrame;
import cs3500.animator.model.Shape;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 * This class represents the VisualGView. It animate a model by the actual view of animation
 * implemented in the Model {@Code AnimationModel}, based on the AnimmationPanel {@Code AnimePanel},
 * {@Code JFrame}. The View takes a model {@Code AnimationModel} and the value of speed. It applying
 * GUI to let people see the actual picture.
 */
public class VisualView extends JFrame implements IView {

  private VisualPanel panel;
  private int speed = 1;

  /**
   * Construct the visual view.
   */
  public VisualView() {
    super();
    this.setTitle("Visual!!");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    panel = new VisualPanel();
    JScrollPane scrollPane = new JScrollPane(panel);
    this.add(scrollPane, BorderLayout.CENTER);

    this.pack();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);

  }

  @Override
  public void setShapes(Map<Shape, ArrayList<KeyFrame>> hashShapes) {
    // not implement in this class
  }

  @Override
  public void setUpdatedShapes(ArrayList<Shape> shapes) {
    panel.setShapes(shapes);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error,
        "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setPreferredSize(int leftX, int topY, int width, int height) {
    panel.setBound(leftX, topY);
    panel.setPreferredSize(new Dimension(leftX, topY));
    panel.setSize(leftX, topY);
    this.setSize(width, height);
    this.setPreferredSize(new Dimension(width, height));
    this.pack();
  }

  @Override
  public void setOutput(Appendable output) {
    //not implement in this class
  }

  @Override
  public void gettextInfo(String textinfo) {
    //not implement in this class
  }

  @Override
  public void getSvgText(String svgText) {
    //Not implements in this class
  }

  @Override
  public void refresh() {
    this.repaint();
  }
}
