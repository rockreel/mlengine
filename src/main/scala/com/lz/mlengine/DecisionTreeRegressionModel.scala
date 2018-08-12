package com.lz.mlengine

import breeze.linalg.{DenseVector, Vector}
import com.lz.mlengine.tree.Node

class DecisionTreeRegressionModel(val rootNode: Node, val featureToIndexMap: Map[String, Int]
                                 ) extends MLModel(featureToIndexMap, None) {

  override private[mlengine] def predictImpl(vector: Vector[Double]): Vector[Double] = {
    DenseVector(rootNode.predictImpl(vector).prediction)
  }

}

object DecisionTreeRegressionModel extends MLModelLoader[DecisionTreeRegressionModel]
