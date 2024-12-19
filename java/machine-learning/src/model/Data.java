package model;

import lombok.Getter;
import weka.classifiers.trees.J48;
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
    private String importantFeatures;
    private J48 decisionTree;

    public Data(String filename) {
        decisionTree = null;
        try {
            DataSource dataSrc = new DataSource(filename);
            data = dataSrc.getDataSet();

        } catch (Exception e) {
            throw new RuntimeException("Error loading data: " + e.getMessage());
        }
    }

    /**
     *  This filters and selects the most important features and simplify the data set
     */
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
            this.importantFeatures = Utils.arrayToString(indices);

            this.data = res;

        } catch (Exception e) {
            throw new RuntimeException("Error processing data: " + e.getMessage());
        }
    }


    /**
     *  This creates the decision tree (un-pruned tree)
     */
    public void makeDecisionTree() {
        J48 decisionTree = new J48();

        try {

            decisionTree.setOptions(new String[] {"-U"});
            decisionTree.buildClassifier(this.data);

        } catch (Exception e) {
            throw new RuntimeException("Error processing decision tree: " + e.getMessage());
        }

        this.decisionTree = decisionTree;
    }
}
