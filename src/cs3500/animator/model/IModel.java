package cs3500.animator.model;


import java.util.ArrayList;
import java.util.Map;

/**
 * The interface for AnimatorModel, which used to construct a stucture including the name and type
 * of shapes containing bunch of keyframes representing the future movement. The model would be
 * later used by AnimationView {@Code IView} for viewing and {@Code IController} for controlling.
 */
public interface IModel {

  /**
   * Get the leftX of stored in the model.
   *
   * @return int value of x boundary
   */
  int getBoundX();

  /**
   * Get the topY of stored in the model.
   *
   * @return int top of Y boundary
   */
  int getBoundY();


  /**
   * Get the width of stored in the model.
   *
   * @return int value of wide boundary
   */
  int getWidth();

  /**
   * Get the height of stored in the model.
   *
   * @return int value of height boundary
   */
  int getHeight();


  /**
   * Get the LinkedHashMappedlist stored all names and related all possible motions after the
   * ticking and updation.
   *
   * @param tick current tick
   */
  void updateTick(int tick);

  /**
   * Get the LinkedHashMappedlist stored all names and related all possible motions.
   *
   * @return integer value of all names and related all possible motions.
   */
  Map<Shape, ArrayList<KeyFrame>> getHashShapes();

  /**
   * get the text information of the current shape. It used in side the TextView.
   *
   * @return the text output of the shape
   */
  String textInfo();

  /**
   * get the svg format text to the svg view. It used to be in side the SVG View.
   * @return the text svg of the shape
   */
  String textSVG(int speed);

  /**
   * Get the the Arraylist of shape inside the Model.
   *
   * @return Arraylist of shapes
   */
  ArrayList<Shape> getShapes();


  /**
   * Get the animation length for controller use to implement the view.
   *
   * @return The total animation length
   */
  int getAnimationLength();
}
