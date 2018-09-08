package com.example.android.smartreminder;
//import org.junit.After;
//import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.feedzai.fos.api.FOSException;
import com.feedzai.fos.api.ModelConfig;

import weka.classifiers.trees.J48;
import weka.classifiers.meta.Vote;
import weka.classifiers.MultipleClassifiersCombiner;

public class QuestionAnswerAnalyzerTest {
    @Test
    public void testMultipleModels() throws FOSException {
        ModelConfig modelConfig = new ModelConfig();
        modelConfig.setProperty(WekaModelConfig.CLASSIFIER_IMPL, Vote.class.getName());
        modelConfig.setProperty(WekaModelConfig.CLASSIFIER_CONFIG, "-R MAX -B \""+J48.class.getName());

        MultipleClassifiersCombiner classifier = (MultipleClassifiersCombiner) QuestionAnswerAnalyzer.create(modelConfig);

        Assert.assertEquals(J48.class,classifier.getClassifiers()[0].getClass());

    }
}
