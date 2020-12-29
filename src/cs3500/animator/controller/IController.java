package cs3500.animator.controller;

/**
 * Interface IController providing the functionanlity for the AnimationController {@Code
 * AnimationController}. It takes the job as implemenating the model and view, passing the data with
 * each other.
 */
public interface IController {

  /**
   * Set the speed to model and the view.
   *
   * @param speed int value of speed
   */
  void setSpeed(int speed);

  /**
   * Set the appendable output to the view.
   *
   * @param output Appendable output
   */
  void setOutput(Appendable output);

  /**
   * Set the text description of the motion and the shape.
   */
  void setTextInfo();

  /**
   * Set the text svg of the motion and the shape.
   */
  void setSVG();

  /**
   * Go commend to start and execute the view and model.
   */
  void start();

}
