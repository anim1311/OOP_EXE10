import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App extends Application {

    private Canvas canvas;

    private final int INIT_HEIGHT = 600;
    private final int INIT_WIDTH = 1100;

    private final int PADDING_TEXT = 5;
    private final int TEXT_FIELD_WIDTH = 50;

    private RegularPolygon polygon;

    @Override
    public void start(Stage primaryStage) {
        // Main layout is BorderPane
        BorderPane root = new BorderPane();
        
        // Canvas for drawing shapes and axes
        canvas = new Canvas(600, 400);
        drawAxes();
        Pane canvasPane = new Pane(canvas);

        
        // Bind canvas size to the root pane size
        canvas.widthProperty().bind(canvasPane.widthProperty());
        canvas.heightProperty().bind(canvasPane.heightProperty());
        
        // Redraw axes when the window is resized
        canvas.widthProperty().addListener(evt -> drawAxes());
        canvas.heightProperty().addListener(evt -> drawAxes());
        canvasPane.widthProperty().addListener(evt -> drawPolygon(polygon));
        canvasPane.heightProperty().addListener(evt -> drawPolygon(polygon));


        // Controls
        TextField tfX = new TextField("0");
        TextField tfY = new TextField("0");
        TextField tfN = new TextField("3");
        TextField tfSize = new TextField("100");
        TextField tfTheta = new TextField("0");
        ColorPicker colorPicker = new ColorPicker(Color.RED);

        Button drawButton = new Button("Draw");
        drawButton.setOnAction(e -> {
            // Placeholder for drawing logic
            // Get values from text fields and color picker, then draw the polygon
            polygon = new RegularPolygon(
                    colorPicker.getValue().toString(),
                    true,
                    Integer.parseInt(tfN.getText()),
                    Double.parseDouble(tfSize.getText()),
                    Double.parseDouble(tfX.getText()),
                    Double.parseDouble(tfY.getText()),
                    Double.parseDouble(tfTheta.getText())
            );

            drawPolygon(polygon);
        });

        canvasPane.setOnKeyPressed(e ->{

            switch (e.getCode()) {
                case ENTER:
                    drawButton.fire();
                    break;
            
                default:
                    break;
            }
        });

        // HBox for controls
        HBox controls = new HBox(10);
        controls.setPadding(new Insets(10));
        controls.getChildren().addAll(
                new Label("X:"), tfX,
                new Label("Y:"), tfY,
                new Label("N:"), tfN,
                new Label("Size:"), tfSize,
                new Label("Theta (°):"), tfTheta,
                new Label("Color:"), colorPicker,
                drawButton
        );

        for (TextField tf : new TextField[]{tfX, tfY, tfN, tfSize, tfTheta}) {
            // Allow only numbers in text fields
            tf.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    tf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }

        controls.getChildren().forEach((node) -> {
            HBox.setMargin(node, new javafx.geometry.Insets(0, PADDING_TEXT, 0, PADDING_TEXT));

            if(node instanceof TextField){
                ((TextField)node).setPrefWidth(TEXT_FIELD_WIDTH);
            }

        });

    
        // Add the canvas and controls to the BorderPane
        root.setCenter(canvasPane);
        root.setBottom(controls);

        Scene scene = new Scene(root, this.INIT_WIDTH, this.INIT_HEIGHT);

        primaryStage.setTitle("Geometric Drawing Tool");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawAxes() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        
        // Clear the canvas
        gc.clearRect(0, 0, w, h);
        
        // Draw horizontal line
        gc.strokeLine(0, h / 2, w, h / 2);
        // Draw vertical line
        gc.strokeLine(w / 2, 0, w / 2, h);

    }

    private void drawPolygon(RegularPolygon polygon){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double w = canvas.getWidth();
        double h = canvas.getHeight();


        drawAxes();

        ArrayList<Double> xPoints = new ArrayList<Double>();
        ArrayList<Double> yPoints = new ArrayList<Double>();

        String points[] = this.polygon.toString().replace("(", "").replace(")", "").split(",");

        for(int i = 0; i < points.length; i++){
            if(i % 2 == 0){
                xPoints.add(Double.parseDouble(points[i]) + w/2);
            }else{
                yPoints.add(h/2 - Double.parseDouble(points[i]) );
            }
        }

        gc.setFill(Color.web(polygon.getColor()));
        gc.fillPolygon(
                xPoints.stream().mapToDouble(Double::doubleValue).toArray(),
                yPoints.stream().mapToDouble(Double::doubleValue).toArray(),
                polygon.getN()
        );

    }

    public static void main(String[] args) {
        launch(args);
    }
}
