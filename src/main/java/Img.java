

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Img {
    private String m_url;
    private double m_xPos;
    private double m_yPos;
    private double m_width;
    private double m_height;
    private ImageView m_imageView;
    public Img(String name, double xPos, double yPos, double width, double height, boolean isButton) {
        m_url = getClass().getResource("/" + name + ".png").toString();
        m_xPos = xPos;
        m_yPos =  yPos;
        m_width = width;
        m_height = height;
        Image image = new Image(m_url);
        m_imageView = new ImageView(image);
        m_imageView.setFitWidth(m_width);
        m_imageView.setFitHeight(m_height);
        m_imageView.setTranslateX(m_xPos);
        m_imageView.setTranslateY(m_yPos);
        if (isButton) {
        m_imageView.setOnMouseEntered(e -> {
            m_imageView.setFitWidth(m_width * 1.05);
            m_imageView.setFitHeight(m_height * 1.05);
            m_imageView.setCursor(Cursor.HAND);
        });
        m_imageView.setOnMouseExited(e -> {
            m_imageView.setFitWidth(m_width / 1.05);
            m_imageView.setFitHeight(m_height / 1.05);
            m_imageView.setCursor(Cursor.DEFAULT);
        });
        }
    }
    public ImageView getObject() {
        return m_imageView;
    }
    public void setPosition(double newX, double newY) {
        m_imageView.setTranslateX(newX);
        m_imageView.setTranslateY(newY);
        m_xPos = newX;
        m_yPos = newY;
    }
    public void setScale(double newWidth, double newHeight) {
        m_imageView.setFitWidth(newWidth);
        m_imageView.setFitHeight(newHeight);
        m_width = newWidth;
        m_height = newHeight;
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
    public String getUrl() {
        return m_url;
    }
}
