package cs3500.animator.controller;

import cs3500.animator.model.IModel;
import cs3500.animator.view.IView;
import cs3500.animator.view.Loop;
import javax.swing.Timer;

/**
 * Animator Controller which implements {@Code IController} using to setting the model and view.
 * Taking the implementation in model and show it in view.
 */
public class AnimatorController implements IController {

  private IView view;
  private IModel model;
  private Timer timer;
  private Loop loop;
  int speed = 1;

  /**
   * Constructor for Animator Controller which taking model, view and loop {@Code IModel} {@Code
   * IView} {@Code Loop} for mananaging the whole showing of animation.
   *
   * @param model inputted model
   * @param view  inputted view
   * @param loop  inputted loop
   */
  public AnimatorController(IModel model, IView view, Loop loop) {
    this.model = model;
    this.view = view;
    this.loop = loop;
    this.timer = new Timer(1000 / speed, loop);

    this.view.setPreferredSize(model.getBoundX(), model.getBoundY(),
        model.getWidth(), model.getHeight());
    this.view.setShapes(model.getHashShapes());
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public void setOutput(Appendable output) {
    view.setOutput(output);
  }

  @Override
  public void setTextInfo() {
    view.gettextInfo(model.textInfo());
  }

  @Override
  public void setSVG() {
    view.getSvgText(model.textSVG(speed));
  }

  @Override
  public void start() {
    this.timer = new Timer(1000 / speed, loop);
    this.view.makeVisible();
    this.timer.start();
  }
}
