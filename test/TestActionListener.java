import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.animator.controller.EditController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModel.AnimeBuilder;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IEditView;
import org.junit.Test;

/**
 * Test ActionListener.
 */
public class TestActionListener {

  AnimatorModel m = new AnimeBuilder().setBounds(100, 200, 400, 300)
      .declareShape("R", "rectangle")
      .declareShape("C", "ellipse")
      .addKeyframe("R", 50, 290, 280, 22, 32, 2, 42, 92)
      .addKeyframe("R", 1, 190, 180, 20, 30, 0, 49, 90)
      .addMotion("R", 1, 190, 180, 20, 30, 0, 49, 90, 25,
          190, 180, 20, 30, 0, 49, 90)
      .addKeyframe("R", 10, 290, 280, 22, 32, 2, 42, 92)
      .addKeyframe("C", 1, 167, 210, 65, 30, 6, 247, 41)
      .addMotion("C", 1, 167, 210, 65, 30, 6, 247, 41,
          57, 167, 210, 65, 30, 6, 247, 41)
      .build();

  IEditView editView = new EditView("Edit");
  EditController c = new EditController(m);

  @Test
  public void testloopAnimation() {
    c.setEditView(editView);

    assertTrue(c.getLoop());

    c.loopAnimation();

    assertTrue(!c.getLoop());
  }

  @Test
  public void testPauseAnimation() {
    c.setEditView(editView);

    assertTrue(!c.getPause());

    c.pauseAnimation();

    assertTrue(c.getPause());
  }


  @Test
  public void testsetSpeed() {
    c.setEditView(editView);

    c.setSpeed(20);

    assertEquals(c.getSpeed(), 20);
  }
}
