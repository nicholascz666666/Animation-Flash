package cs3500.animator.view;

import cs3500.animator.model.IModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * One single loop taking the job as action listener and could do the looping based letting the view
 * work.
 */
public class Loop implements ActionListener {

  private IView view;
  private IModel model;
  private int tick;

  /**
   * Construct the loop, with inputed view and model.
   *
   * @param view  inputted view
   * @param model inputted model
   */
  public Loop(IView view, IModel model) {
    this.view = view;
    this.model = model;
    this.tick = 1;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    model.updateTick(tick);
    view.setUpdatedShapes(model.getShapes());
    view.refresh();
    tick++;
  }
}
