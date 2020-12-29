package cs3500.animator.view;

import cs3500.animator.model.KeyFrame;
import cs3500.animator.model.Shape;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class represents the SVGView. It animate a model by outputting the name, type of the objects
 * implemented in the Model {@Code AnimationModel},including horizontal & vertical position, width &
 * height, color, and starting and ending time. The View takes a model {@Code AnimationModel} and an
 * appendable {@Code Appendable}.
 */

public class SVGView implements IView {

  private Appendable output;
  private String svgText;

  /**
   * Consturctor the SVGView annd initilialize the variables. It initialize some values for the
   * later use.
   */
  public SVGView() {
    this.output = null;
    this.svgText = "";
  }

  @Override
  public void makeVisible() {
    try {
      if (output != null) {
        output.append(this.svgText);
      } else {
        System.out.print(this.svgText);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Failure in appendable");
    }
  }


  @Override
  public void setShapes(Map<Shape, ArrayList<KeyFrame>> hashShapes) {
    //not implement in this class
  }

  @Override
  public void setUpdatedShapes(ArrayList<Shape> shapes) {
    //not Implement in this class
  }

  @Override
  public void showErrorMessage(String error) {
    //not Implement in this class
  }

  @Override
  public void setPreferredSize(int leftX, int topY, int width, int height) {
    //Not implement in this class
  }

  @Override
  public void setOutput(Appendable output) {
    this.output = output;
  }

  @Override
  public void gettextInfo(String textinfo) {
    //Not implements in this class
  }

  @Override
  public void getSvgText(String svgText) {
    this.svgText = svgText;
  }

  @Override
  public void refresh() {
    //not Implement in this class
  }
}
