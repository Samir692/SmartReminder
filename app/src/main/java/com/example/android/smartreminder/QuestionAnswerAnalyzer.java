package com.example.android.smartreminder;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.*;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;

public class QuestionAnswerAnalyzer {
    Classifier c = new J48();
    trainModel(c, "J48");
}
