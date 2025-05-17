


import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class Btn {
    double xPos;
    double yPos;
    double length;
    double height;
    Color bgColor;
    Color textColor;
    Rectangle rect;
    Text text;
    TextFlow flow;
    String content;
    double textSize;
    boolean isButton;
    StackPane group;
    public Btn(double xPos, double yPos, double length, double height, Color bgColor, Color textColor, String content, double textSize, boolean isButton) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.length = length;
        this.height = height;
        this.bgColor = bgColor;
        this.textColor = textColor;
        this.content = content;
        this.textSize = textSize;
        this.isButton = isButton;
        rect = new Rectangle(length, height, bgColor);
        rect.setArcWidth(length/10);
        rect.setArcHeight(length/10);
        text = new Text(content);
        text.setFill(textColor);
        text.setFont(Font.font(MainApp.getFont(), textSize));
        text.setWrappingWidth(length);
        group = new StackPane(rect, text);
        group.setMaxWidth(length);
        group.setMaxHeight(height);
        group.setTranslateX(xPos);
        group.setTranslateY(yPos);
        group.setEffect(new DropShadow(height/1.7,Color.DARKBLUE));
        
        if (isButton) {
        group.setOnMouseEntered(e -> {
            rect.setScaleX(1.05);
            rect.setScaleY(1.05);
            text.setFont(Font.font(MainApp.getFont(), textSize+3));
            text.setWrappingWidth(length*1);
            group.setCursor(Cursor.HAND);
        });
        group.setOnMouseExited(event -> {
            rect.setScaleX(1);
            rect.setScaleY(1);
            text.setFont(Font.font(MainApp.getFont(), textSize));
            text.setWrappingWidth(length);
            group.setCursor(Cursor.DEFAULT);
        });
        }
        text.setTextAlignment(TextAlignment.CENTER);
        System.out.println(group.getHeight());
    }
    public Rectangle getRect() {
        return rect;
    }
    public Text getText() {
        return text;
    }
    public StackPane getObject() {
        return group;
    }
}
