package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private TextField stickOneAdjacent;

    @FXML
    private TextField distance;

    @FXML
    private Text information;

    @FXML
    private Text errorText;

    @FXML
    private PieChart pie;

    @FXML
    private TextField stickTwoAdjacent;

    @FXML
    private TextField oppositeSide;

    private void closeProgram() {
        boolean answer = ConfirmBox.display("Close Application", "Are you sure you want to quit? :(");

        if (answer) {
            System.exit(0);
        }
    }

    @FXML
    void terminateProgram() {
        closeProgram();
    }

    @FXML
    void showHelpText() {
        String help = "" +
                "The program can convert the following (not case-sensitive):\n" +
                "Miles can be written as -> Miles or Mile\n" +
                "Meters can be written as -> Meters, meter or m\n" +
                "Millimeters can be written as -> Millimeter or mm\n" +
                "Inches can be written as -> Inches or in\n" +
                "Feet can be written as -> Foot, feet or ft\n" +
                "If you have the measurements as km (for the distance) and cm (for the rest)\n" +
                "It can be entered without the variables";
        DialogBox.display("Help", help);
    }


    @FXML
    void cute() {
        reset();
        pie.setVisible(true);
        information.setVisible(true);
        errorText.setVisible(false);

        try {
            double d = Double.parseDouble(rSAC(distance.getText(), "KM"));
            double stickOneOpposite = Double.parseDouble(rSAC(oppositeSide.getText(), "CM"));
            double stickOneAdjacent_ = Double.parseDouble(rSAC(stickOneAdjacent.getText(), "CM"));
            double stickTwoAdjacent_ = Double.parseDouble(rSAC(stickTwoAdjacent.getText(), "CM"));

            if (stickOneAdjacent_ != stickTwoAdjacent_) {
                int EARTH = 40075;
                double angleOne = tenth(TanInverse(stickOneOpposite, stickOneAdjacent_));
                double angleTwo = tenth(TanInverse(stickOneOpposite, stickTwoAdjacent_));
                double angleInBetween = Math.round((Math.abs(angleOne - angleTwo)) * 10) / 10.0;
                double remainderOfCircle = (360 / angleInBetween);
                double calculatedCircumferenceWithGivenValues = Math.round(d * remainderOfCircle);
                double change = Change(calculatedCircumferenceWithGivenValues, EARTH);

                String right = ""
                        + "The angle for the First stick: " + angleOne + "°\n"
                        + "The angle for the Second stick: " + angleTwo + "°\n"
                        + "The angle at which both meet: " + angleInBetween + "°\n"
                        + "Your calculated circumference: " + calculatedCircumferenceWithGivenValues + "Km\n\n"
                        + "The circumference of planet EARTH is " + EARTH + " Km. "
                        + "\nYou calculated " + calculatedCircumferenceWithGivenValues + " Km. "
                        + "\nYou were off by " + change + "%.\n";

                setupPie(angleInBetween);

                if (change > 79.99) {
                    information.setText(right + "\nBIG OOF");
                } else if (change > 49.99) {
                    information.setText(right + "\nOOF");
                } else {
                    information.setText(right);
                }

            } else {
                reset();
                pie.setVisible(false);
                information.setVisible(false);
                errorText.setVisible(true);
                errorText.setText("No curvature... FLAT EARTH! D:");
            }
        } catch (Exception ignored) {
            reset();
            pie.setVisible(false);
            information.setVisible(false);
            errorText.setVisible(true);
            errorText.setText("Verify input information.");
        }
    }

    private void setupPie(double angle) {
        pie.getData().clear();
        PieChart.Data slice1 = new PieChart.Data("Distance measured " + angle + "°", angle);
        PieChart.Data slice2 = new PieChart.Data("The rest " + (360 - angle) + "°", (360 - angle));
        pie.getData().add(slice1);
        pie.getData().add(slice2);
    }

    private void reset() {
        pie.getData().clear();
        information.setText("");
        errorText.setText("");
    }

    private static double Change(double newNumber, double oldNumber) {
        return Math.abs(Math.round((((newNumber - oldNumber) / oldNumber)*100) * 100) / 100.0);
    }

    private static double TanInverse(double opposite, double adjacent) {
        return ToDegrees(Math.atan(opposite/adjacent));
    }

    private static double ToDegrees(double value) {
        return value*(180/Math.PI);
    }

    private static double tenth(double value) {
        return Math.round(value * 10) / 10.0;
    }

    private static String rSAC(String string, String type) {
        char[] list = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '@', '#', '!', '$', '%',
                '&', '-', '_', '=', '`', '~', '"', ',', '/', '|'
        };

        string = string.toUpperCase();
        string = string.replaceAll("\\s+","");

        String[] types = {
                "CM", "MILLIMETER", "METER", "METERS", "KM", "MILES", "MILE", "INCHES",
                "IN", "FEET", "FOOT", "FT", "MM", "M"
        };

        String what = "";

        for (String var : types) {
            if (string.contains(var)) {
                what = var;
                break;
            }
        }

        if (what.equals("")) {
            what = type;
        }

        if (string.contains(".")) {
            for (char character : list) {
                string = string.replaceAll(String.valueOf(character), "");
            }

            string = convert(string, what, type);

            return string;
        } else {
            string = string.replaceAll("\\D", "");

            string = convert(string, what, type);

            return string;
        }
    }

    private static String convert(String value, String from, String to) {
        double val = Double.parseDouble(value);

        if (from.equals(to)) {
            return value;
        }

        switch (from) {
            case "MILES":
            case "MILE":
                if (to.equals("KM")) {
                    val *= 1.609;
                } else if (to.equals("CM")) {
                    val *= 160934.4;
                }
                break;
            case "METER":
            case "METERS":
            case "M":
                if (to.equals("KM")) {
                    val /= 1000;
                } else if (to.equals("CM")) {
                    val *= 100;
                }
                break;
            case "MM":
            case "MILLIMETER":
                if (to.equals("KM")) {
                    val /= 1e+6;
                } else if (to.equals("CM")) {
                    val /= 10;
                }
                break;
            case "INCHES":
            case "IN":
                if (to.equals("KM")) {
                    val /= 39370.079;
                } else if (to.equals("CM")) {
                    val *= 2.54;
                }
                break;
            case "FEET":
            case "FOOT":
            case "FT":
                if (to.equals("KM")) {
                    val /= 3280.84;
                } else if (to.equals("CM")) {
                    val *= 30.48;
                }
                break;
        }
        return String.valueOf(val);
    }
}
