
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class Prompt {
    private double m_xPos;
    private double m_yPos;
    private double m_width;
    private double m_height;
    private String m_text;
    private TextField m_label;
    public Prompt(String text, double xPos, double yPos, double width, double height, int fontSize) {
        m_text = text;
        m_xPos = xPos;
        m_yPos = yPos;
        m_width = width;
        m_height = height;
        m_label = new TextField(text);
        m_label.setTranslateX(xPos);
        m_label.setTranslateY(yPos);
        m_label.setPrefWidth(width);
        m_label.setMinWidth(width);
        m_label.setMaxWidth(width);
        m_label.setPrefHeight(height);
        m_label.setMinHeight(height);
        m_label.setMaxHeight(height);
        m_label.setFont(Font.font(MainApp.getFont(), fontSize));
        
    }
    public TextField getObject() {
        return m_label;
    }
    public void setPosition(double newX, double newY) {
        m_label.setTranslateX(newX);
        m_label.setTranslateY(newY);
        m_xPos = newX;
        m_yPos = newY;
    }
    public void setScale(double newWidth, double newHeight) {
        m_label.setPrefWidth(newWidth);
        m_label.setMinWidth(newWidth);
        m_label.setMaxWidth(newWidth);
        m_label.setPrefHeight(newHeight);
        m_label.setMinHeight(newHeight);
        m_label.setMaxHeight(newHeight);
        m_width = newWidth;
        m_height = newHeight;
    }
    public String getText() {
        return m_label.getText();
    }
    public double getXPos() {
        return m_xPos;
    }
    public double getYPos() {
        return m_yPos;
    }
    public double getWidth() {
        return m_width;
    }
    public double getHeight() {
        return m_height;
    }
    public String getDefaultText() {
        return m_text;
    }
}
