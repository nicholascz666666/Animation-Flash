package cs3500.animator.view;

import cs3500.animator.model.KeyFrame;
import cs3500.animator.model.Shape;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class represents the TextView. It animate a model by outputting the  name, type of the
 * objects implemented in the Model {@Code AnimationModel},including horizontal & vertical position,
 * width & height, color, and starting and ending time.
 */
public class TexualView implements IView {

  //local variables
  private String textInfo;
  private Appendable output;

  /**
   * Constructor for text view.
   */
  public TexualView() {
    this.output = null;
  }

  /**
   * Make the makevisible used to output the file of txt view.
   *
   * @throws IllegalArgumentException if fail in appenable
   */
  @Override
  public void makeVisible() {
    try {
      if (output != null) {
        output.append(textInfo);

      } else {
        System.out.print(textInfo);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Failure in appendable");
    }

  }

  @Override
  public void setShapes(Map<Shape, ArrayList<KeyFrame>> hashShapes) {
    //not in this class
  }

  @Override
  public void setUpdatedShapes(ArrayList<Shape> shapes) {
    //not implement in this class
  }

  @Override
  public void showErrorMessage(String error) {
    //not implement in this class
  }

  @Override
  public void setPreferredSize(int leftX, int topY, int width, int height) {
    //not in this class
  }

  @Override
  public void setOutput(Appendable output) {
    this.output = output;
  }

  @Override
  public void gettextInfo(String textinfo) {
    this.textInfo = textinfo;
  }

  @Override
  public void getSvgText(String svgText) {
    //Not implements in this class
  }

  @Override
  public void refresh() {
    //not implement in this class
  }


}
