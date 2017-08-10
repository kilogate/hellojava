package com.kilogate.hello.java.javase.jdkapi.annotation.listener;

import javax.swing.*;
import java.awt.*;

/**
 * 应用注解的类
 *
 * @author fengquanwei
 * @create 2017/8/7 19:48
 **/
public class ButtonFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private JPanel panel;
    private JButton yellowButton;
    private JButton blueButton;
    private JButton redButton;

    public ButtonFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JPanel panel = new JPanel();
        add(panel);

        yellowButton = new JButton("Yellow");
        blueButton = new JButton("Bule");
        redButton = new JButton("Red");

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        ActionListenerInstaller.processAnnotations(this);
    }

    @ActionListenerFor(source = "yellowButton")
    public void yellowBackground() {
        this.panel.setBackground(Color.YELLOW);
    }

    @ActionListenerFor(source = "blueButton")
    public void blueBackground() {
        this.panel.setBackground(Color.BLUE);
    }

    @ActionListenerFor(source = "redButton")
    public void redBackground() {
        this.panel.setBackground(Color.RED);
    }

    public static void main(String[] args) {
        ButtonFrame buttonFrame = new ButtonFrame();
        System.out.println(buttonFrame);
    }

}
