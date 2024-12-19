import model.Data;

public class Main {
    public static void main(String[] args) {

        String filePath = "src/resources/classification/zoo.arff";
        Data data = new Data(filePath);

        data.setData();

    }
}