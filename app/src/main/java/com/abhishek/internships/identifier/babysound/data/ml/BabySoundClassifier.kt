package com.abhishek.internships.identifier.babysound.data.ml

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.nio.ByteBuffer
import java.nio.ByteOrder

class BabySoundClassifier(
    context: Context
) {

    private val interpreter: Interpreter

    init {
        val modelBuffer = ModelLoader.loadModel(
            context,
            "baby_sound.tflite"
        )
        interpreter = Interpreter(modelBuffer)
    }

    fun classify(input: FloatArray): Pair<Int, Float> {
        val output = Array(1) { FloatArray(5) }
        interpreter.run(input, output)

        val index = output[0].indices.maxBy { output[0][it] }
        return index to output[0][index]
    }
}
