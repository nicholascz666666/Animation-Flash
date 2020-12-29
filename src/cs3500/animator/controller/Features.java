package cs3500.animator.controller;

/**
 * Interface feature for the use by {@Code EditController} it used as event which should be
 * implement by controller and then change the data of model.
 */
public interface Features {

  /**
   * Get the start animaiton from EditView and implement the animation.
   */
  void startAnimation();

  /**
   * Restart the animaiton.
   */
  void restartAnimation();

  /**
   * Puase the animation.
   */
  void pauseAnimation();

  /**
   * Flip the ablitity of loop.
   */
  void loopAnimation();

  /**
   * Double the current speed.
   */
  void fastAnimation();

  /**
   * Slow down the current speed.
   */
  void slowAnimation();

  /**
   * add the animation.
   */
  void addAnimation();

  /**
   * Delete the unwanted shape.
   */
  void deleteShape();

  /**
   * modtify the keyframe.
   */
  void editKeyFrame();

  /**
   * Stop the anime chause and make the aim to teh rurable energy.
   */
  void deleteKeyFrame();

  /**
   * Set the speed.
   *
   * @param speed integer value of speed.
   */
  void setSpeed(int speed);

  /**
   * get the loop state.
   *
   * @return boolean loop
   */
  boolean getLoop();

  /**
   * get the pause state.
   *
   * @return boolean loop
   */
  boolean getPause();

  /**
   * Get the speed.
   * @return int value of speed
   */
  int getSpeed();
}
