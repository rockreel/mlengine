package com.lz.mlengine.classification

import com.lz.mlengine.{SparkConverter, SparkModelTest}
import org.apache.spark.ml.{classification => cl}
import org.junit.Test

class RandomForestClassificationModelTest extends SparkModelTest {

  @Test def testBinaryClassification() = {
    val sparkModel = getTrainer.fit(binaryClassificationData)
    val model = SparkConverter.convert(sparkModel)(Map[String, Int](), Map[Int, String]())

    val path = s"${temporaryFolder.getRoot.getPath}/random_forest_classification_binary"
    val modelLoaded = saveAndLoadModel(model, path, RandomForestClassificationModel.load)

    assertBinaryClassificationModelProbabilitySame[cl.RandomForestClassificationModel](
      binaryClassificationData, sparkModel, modelLoaded
    )
  }

  @Test def testMultiClassification() = {
    val sparkModel = getTrainer.fit(multiClassificationData)
    val model = SparkConverter.convert(sparkModel)(Map[String, Int](), Map[Int, String]())

    val path = s"${temporaryFolder.getRoot.getPath}/random_forest_classification_multiple"
    val modelLoaded = saveAndLoadModel(model, path, RandomForestClassificationModel.load)

    assertMultiClassificationModelProbabilitySame[cl.RandomForestClassificationModel](
      multiClassificationData, sparkModel, modelLoaded
    )
  }

  def getTrainer = {
    new cl.RandomForestClassifier()
      .setMaxDepth(4)
  }

}
