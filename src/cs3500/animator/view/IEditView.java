package cs3500.animator.view;

import cs3500.animator.controller.Features;
import java.util.ArrayList;

/**
 * Interface for EditView which allowing user to edit and implement the keyframe, adding or
 * implement the animation. It extends the IView {@Code IView}.
 */
public interface IEditView extends IView {

  /**
   * Add the feature to view.
   *
   * @param features features, the event of action listener.
   */
  void addFeatures(Features features);

  /**
   * Get the current Combine box.
   *
   * @return string of combine box
   */
  String getCurrentCMD();

  /**
   * Get the input shape name.
   *
   * @return String input name
   */
  String getInputName();

  /**
   * Get the selected shape.
   *
   * @return String name of the selected shape
   */
  String getDeleteShape();

  /**
   * Get the frame values into output of ArrayList of integer.
   *
   * @return list of integer
   */
  ArrayList<Integer> getNewFrameValues();

}
