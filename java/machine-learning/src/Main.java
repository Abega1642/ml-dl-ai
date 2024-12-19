import model.Data;
import weka.core.DenseInstance;
import weka.core.Instance;


public class Main {
    public static void main(String[] args) {

        String filePath = "src/resources/classification/zoo.arff";
        Data DATA = new Data(filePath);
        DATA.setData();
        DATA.makeDecisionTree();

        Instance unicorn = new DenseInstance(DATA.getData().numAttributes());

        unicorn.setValue(0, 1.0);   //  with hair
        unicorn.setValue(1, 0.0);   //  no feather
        unicorn.setValue(2, 0.0);   //  no eggs
        unicorn.setValue(3, 1.0);   //  has milk
        unicorn.setValue(4, 0.0);   //  not airborne
        unicorn.setValue(5, 0.0);   //  not aquatic
        unicorn.setValue(6, 0.0);   //  not predator
        unicorn.setValue(7, 1.0);   //  with teeth
        unicorn.setValue(8, 1.0);   //  with backbone
        unicorn.setValue(9, 1.0);   //  with breathes
        unicorn.setValue(10, 0.0);  //  venomous
        unicorn.setValue(11, 0.0);  //  no fins
        unicorn.setValue(12, 4.0);  //  4 legs
        unicorn.setValue(13, 1.0);  //  with tail
        unicorn.setValue(14, 1.0);  //  domestic
        unicorn.setValue(15, 0.0);  //  no catsize

        unicorn.setDataset(DATA.getData());

        try {

            double indexResult = DATA.getDecisionTree().classifyInstance(unicorn);

            String result = DATA.getData().classAttribute().value((int) indexResult);

            System.out.printf("The unicorn is a %s", result);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}