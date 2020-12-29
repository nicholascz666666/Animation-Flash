package cs3500.animator.view;

import cs3500.animator.controller.Features;
import cs3500.animator.model.KeyFrame;
import cs3500.animator.model.Shape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Edit View taking the job as editable view and provide user all kinds of visualize pattern to
 * actually interact with the animation. It extends JFrame {@Code JFrame} and {@Code IEditView}.
 */
public class EditView extends JFrame implements IEditView {

  private Map<Shape, ArrayList<KeyFrame>> hashShapes;

  private VisualPanel panel;

  private JLabel display;
  private JButton startButton;
  private JButton restartButton;
  private JButton pauseButton;
  private JButton loopButton;
  private JButton fastButton;
  private JButton slowButton;
  private JButton deleteButton;
  private JButton insertButton;
  private JButton editFrameButton;
  private JButton deleteFrameButton;


  private JTextField inputName;
  private JTextField inputT;
  private JTextField inputX;
  private JTextField inputY;
  private JTextField inputW;
  private JTextField inputH;
  private JTextField inputR;
  private JTextField inputG;
  private JTextField inputB;

  private JComboBox cmb = new JComboBox();
  private JComboBox keyCollection = new JComboBox();

  private final JList<String> list = new JList<String>();
  private final JList<String> frameList = new JList<String>();


  /**
   * Edit View Constructor taking string as its caption and would be output to the public as GUI.
   *
   * @param caption String caption
   */
  public EditView(String caption) {

    super(caption);

    JPanel buttonPanel;
    JPanel insertKeyPanel;
    JPanel listPanel;
    JPanel shapePanel;

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Visual Panel for drawing
    panel = new VisualPanel();
    JScrollPane scrollPane = new JScrollPane(panel);
    this.add(scrollPane, BorderLayout.CENTER);

    //butotn panel for buttons
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 20));
    this.add(buttonPanel, BorderLayout.NORTH);

    //insertKeyPanle for KeyFrame list
    insertKeyPanel = new JPanel();
    insertKeyPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 20));
    this.add(insertKeyPanel, BorderLayout.SOUTH);

    //JList with JScrollPane to store the list of keyframe contains in  one shape
    listPanel = new JPanel();
    listPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 20));
    listPanel.setBorder(BorderFactory.createTitledBorder("KeyFrames"));
    JScrollPane scrollListPane = new JScrollPane(listPanel);
    this.add(scrollListPane, BorderLayout.WEST);

    //ShapePaenel to store shape list
    shapePanel = new JPanel();
    shapePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 20));
    shapePanel.setBorder(BorderFactory.createTitledBorder("Shapes"));
    JScrollPane scrollshapePanel = new JScrollPane(shapePanel);
    this.add(scrollshapePanel, BorderLayout.EAST);

    //startButton
    startButton = new JButton("Start");
    buttonPanel.add(startButton);

    //restartButton
    restartButton = new JButton("Restart");
    buttonPanel.add(restartButton);

    //pause Button
    pauseButton = new JButton("Pause");
    buttonPanel.add(pauseButton);

    //loop Button
    loopButton = new JButton("Disable Loop");
    buttonPanel.add(loopButton);

    //fast Button
    fastButton = new JButton("Speed X2");
    buttonPanel.add(fastButton);

    //fast Button
    slowButton = new JButton("Speed /2");
    buttonPanel.add(slowButton);

    buttonPanel.add(new JLabel("Create Shape: "));
    cmb.addItem("--- Choosing ---");
    cmb.addItem("New Rectangle");
    cmb.addItem("New Oval");
    buttonPanel.add(cmb);

    inputName = new JTextField(10);
    buttonPanel.add(new JLabel("Name"));
    buttonPanel.add(inputName);

    insertButton = new JButton("Create Shape");
    insertButton.setLayout(new FlowLayout());
    buttonPanel.add(insertButton);

    //In List Panel
    shapePanel.add(list);

    //Delete and Add KeyFrame Button
    deleteButton = new JButton("Delete Shape");
    deleteButton.setLayout(new FlowLayout());
    buttonPanel.add(deleteButton);

    frameList.setBounds(20, 20, 20, 20);
    listPanel.add(frameList);

    //tick testField
    inputT = new JTextField(6);
    insertKeyPanel.add(new JLabel("Tick"));
    insertKeyPanel.add(inputT);

    inputX = new JTextField(4);
    insertKeyPanel.add(new JLabel("PosnX"));
    insertKeyPanel.add(inputX);

    inputY = new JTextField(4);
    insertKeyPanel.add(new JLabel("PosnY"));
    insertKeyPanel.add(inputY);

    inputW = new JTextField(4);
    insertKeyPanel.add(new JLabel("Width"));
    insertKeyPanel.add(inputW);

    inputH = new JTextField(4);
    insertKeyPanel.add(new JLabel("Height"));
    insertKeyPanel.add(inputH);

    inputR = new JTextField(3);
    insertKeyPanel.add(new JLabel("Red"));
    insertKeyPanel.add(inputR);

    inputG = new JTextField(3);
    insertKeyPanel.add(new JLabel("Green"));
    insertKeyPanel.add(inputG);

    inputB = new JTextField(3);
    insertKeyPanel.add(new JLabel("Blue"));
    insertKeyPanel.add(inputB);

    editFrameButton = new JButton("Edit/Add Frame");
    insertKeyPanel.add(editFrameButton);

    deleteFrameButton = new JButton("Remove Frame");
    insertKeyPanel.add(deleteFrameButton);


  }


  @Override
  public void addFeatures(Features features) {
    startButton.addActionListener(evt -> {
      features.startAnimation();
      startButton.setEnabled(false);
    });
    restartButton.addActionListener(evt -> features.restartAnimation());
    pauseButton.addActionListener(evt -> {
      features.pauseAnimation();
      flipPause();
    });
    loopButton.addActionListener(evt -> {
      features.loopAnimation();
      flipLoop();
    });
    fastButton.addActionListener(evt -> features.fastAnimation());
    slowButton.addActionListener(evt -> features.slowAnimation());
    insertButton.addActionListener(evt -> features.addAnimation());
    deleteButton.addActionListener(evt -> features.deleteShape());
    cmb.addActionListener(evt -> this.setCMBOption());

    list.addListSelectionListener(e ->
        this.setFrameList(list.getSelectedValue(), hashShapes));

    frameList.addListSelectionListener(e ->
        this.setFrameTextField(frameList.getSelectedValue(), hashShapes));

    editFrameButton.addActionListener(e -> features.editKeyFrame());
    deleteFrameButton.addActionListener(e -> features.deleteKeyFrame());
  }

  /**
   * Flip the pause. If the pause was not resume, it would replace the current pattern to pause.
   */
  private void flipPause() {
    if (pauseButton.getText().equals("Pause")) {
      pauseButton.setText("Resume");
    } else {
      pauseButton.setText("Pause");
    }
  }

  /**
   * Flip the loop.
   */
  private void flipLoop() {
    if (loopButton.getText().equals("Enable Loop")) {
      loopButton.setText("Disable Loop");
    } else {
      loopButton.setText("Enable Loop");
    }
  }

  /**
   * Choose type of combine box based on the input.
   */
  private void setCMBOption() {
    String currentCMB = cmb.getSelectedItem().toString();

    switch (currentCMB) {
      case "New Rectangle":
      case "New Oval":
        insertButton.setForeground(Color.ORANGE);
        break;
      default:
        insertButton.setForeground(Color.BLACK);
        break;
    }
  }

  /**
   * Set the current FrameList based on th4e hashmap inputted.
   *
   * @param name       name of shape
   * @param hashShapes inputted hashshapes contianing shape and list of keyframes
   */
  private void setFrameList(String name, Map<Shape, ArrayList<KeyFrame>> hashShapes) {
    ArrayList<KeyFrame> frames = new ArrayList<>();
    String[] strList;
    for (Shape shape : hashShapes.keySet()) {
      if (shape.getName().equals(name)) {
        frames = hashShapes.get(shape);
      }
    }

    if (frames.size() != 0) {
      strList = new String[frames.size() + 1];
      strList[0] = name + " KeyFrames";
      for (int i = 0; i < frames.size(); i++) {
        strList[i + 1] = "Tick: " + frames.get(i).getT();
      }
    } else {
      strList = new String[]{"No KeyFrames"};
    }

    frameList.setListData(strList);
    frameList.setSelectedIndex(0);

    setFrameTextField(frameList.getSelectedValue(), hashShapes);
  }

  /**
   * Set the input text field according to the current tick and the shape.
   *
   * @param arg        given tick
   * @param hashShapes the inputted hashmap
   */
  private void setFrameTextField(String arg, Map<Shape,
      ArrayList<KeyFrame>> hashShapes) {

    KeyFrame keyFrame = null;

    if (arg == null) {
      arg = frameList.getModel().getElementAt(0);
    }

    String[] strlist = arg.split(" ");

    if (!strlist[1].equals("KeyFrames")) {

      String[] copy = frameList.getModel().getElementAt(0).split(" ");
      String name = copy[0];
      int t = Integer.parseInt(strlist[1]);

      for (Shape shape : hashShapes.keySet()) {
        if (shape.getName().equals(name)) {
          for (KeyFrame frame : hashShapes.get(shape)) {
            if (t == frame.getT()) {
              keyFrame = frame;
            }
          }
        }
      }

      if (keyFrame != null) {
        inputT.setText(String.valueOf(keyFrame.getT()));
        inputX.setText(String.valueOf(keyFrame.getX()));
        inputY.setText(String.valueOf(keyFrame.getY()));
        inputW.setText(String.valueOf(keyFrame.getW()));
        inputH.setText(String.valueOf(keyFrame.getH()));
        inputR.setText(String.valueOf(keyFrame.getR()));
        inputG.setText(String.valueOf(keyFrame.getG()));
        inputB.setText(String.valueOf(keyFrame.getB()));
      }

    }


  }

  @Override
  public ArrayList<Integer> getNewFrameValues() {
    ArrayList<Integer> frameValues = new ArrayList<>();
    int t = Integer.parseInt(this.inputT.getText());
    int x = Integer.parseInt(this.inputX.getText());
    int y = Integer.parseInt(this.inputY.getText());
    int w = Integer.parseInt(this.inputW.getText());
    int h = Integer.parseInt(this.inputH.getText());
    int r = Integer.parseInt(this.inputR.getText());
    int g = Integer.parseInt(this.inputG.getText());
    int b = Integer.parseInt(this.inputB.getText());

    frameValues.add(t);
    frameValues.add(x);
    frameValues.add(y);
    frameValues.add(w);
    frameValues.add(h);
    frameValues.add(r);
    frameValues.add(g);
    frameValues.add(b);

    return frameValues;
  }

  @Override
  public String getCurrentCMD() {

    return this.cmb.getSelectedItem().toString();
  }

  @Override
  public String getInputName() {
    return inputName.getText();
  }

  @Override
  public String getDeleteShape() {
    return list.getSelectedValue();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);

  }

  @Override
  public void setShapes(Map<Shape, ArrayList<KeyFrame>> hashShapes) {
    String[] strList = new String[hashShapes.size()];

    this.hashShapes = hashShapes;
    int i = 0;
    for (Shape shape : hashShapes.keySet()) {
      strList[i] = shape.getName();
      i++;
    }

    list.setListData(strList);
    list.setSelectedIndex(0);

    this.setFrameList(list.getSelectedValue(), hashShapes);
  }

  @Override
  public void setUpdatedShapes(ArrayList<Shape> shapes) {
    panel.setShapes(shapes);
  }


  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error,
        "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setPreferredSize(int leftX, int topY, int width, int height) {
    panel.setBound(leftX, topY);
    this.setSize(width, height);
    this.setPreferredSize(new Dimension(width, height));
    this.pack();
  }

  @Override
  public void setOutput(Appendable output) {
    //Not implement in this class
  }

  @Override
  public void gettextInfo(String textinfo) {
    //Not implement in this class
  }

  @Override
  public void getSvgText(String svgText) {
    //Not implement in this class
  }

  @Override
  public void refresh() {
    this.repaint();
  }

}
