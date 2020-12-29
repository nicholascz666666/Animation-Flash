package cs3500.animator.view;

import cs3500.animator.model.KeyFrame;
import cs3500.animator.model.Shape;
import java.util.ArrayList;
import java.util.Map;

/**
 * Interface of the View. Provding the view for the model given {@Code AnimationModel}, and
 * currently it contains three different view: TextView, SVGView and VisualView. TextView outputs
 * text including the name, type, starting and ending position, size, and color in the period of
 * time {@Code TextView}. The SVGView was similar to TextView but output the view in SVG format
 * {@Code SVGView}. And VisualView was implemented {@Code JPanel} {@Code JFrame} to showing the
 * graph actually in GUI.
 */
public interface IView {

  /**
   * Method was implemented in VisualView to do the job of setVisible {@Code setVisible}.
   */
  void makeVisible();

  /**
   * Set the hashshapes.
   *
   * @param hashShapes inputted hashShapes
   */
  void setShapes(Map<Shape, ArrayList<KeyFrame>> hashShapes);

  /**
   * Set the updatted arrayList of shapes.
   *
   * @param shapes list of shapes for transferring
   */
  void setUpdatedShapes(ArrayList<Shape> shapes);

  /**
   * Showing the error message.
   *
   * @param error error needed to be output
   */
  void showErrorMessage(String error);

  /**
   * Set the preferredSize.
   *
   * @param leftX  leftmost x
   * @param topY   toppest y
   * @param width  width of bound
   * @param height height of bound
   */
  void setPreferredSize(int leftX, int topY, int width, int height);

  /**
   * Set the output appendable.
   *
   * @param output Appendable output
   */
  void setOutput(Appendable output);

  /**
   * Set the text view.
   *
   * @param textinfo text view from model
   */
  void gettextInfo(String textinfo);

  /**
   * Set the txt svg view.
   *
   * @param svgText svg view from model
   */
  void getSvgText(String svgText);

  /**
   * Refresh function in view, allowing panel to refresh.
   */
  void refresh();
}
