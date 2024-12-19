package model;

import lombok.Getter;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.attributeSelection.AttributeSelection;

@Getter
public class Data {

    private Instances data;

    public Data(String filename) {
        try {
            DataSource dataSrc = new DataSource(filename);
            data = dataSrc.getDataSet();

        } catch (Exception e) {
            throw new RuntimeException("Error loading data: " + e.getMessage());
        }
    }

    public void setData() {
        Instances res = new Instances(this.data);
        Remove rm = new Remove();
        String[] options = new String[]{"-R", "1"};

        try {
            rm.setOptions(options);
            rm.setInputFormat(res);
            res = Filter.useFilter(res, rm);


            InfoGainAttributeEval eval = new InfoGainAttributeEval();
            Ranker search = new Ranker();
            AttributeSelection attSelect = new AttributeSelection();

            attSelect.setEvaluator(eval);
            attSelect.setSearch(search);
            attSelect.SelectAttributes(res);

            int[] indices = attSelect.selectedAttributes();
            System.out.println("Selected Attributes: " + Utils.arrayToString(indices));

            this.data = res;

        } catch (Exception e) {
            throw new RuntimeException("Error processing data: " + e.getMessage());
        }
    }


}
