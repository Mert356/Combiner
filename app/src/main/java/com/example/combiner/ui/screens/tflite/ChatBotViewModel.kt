package com.example.combiner.ui.screens.tflite

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tensorflow.lite.task.text.nlclassifier.BertNLClassifier
import java.io.IOException

class ChatBotViewModel(private val context: Context) : ViewModel() {

    private var classifier: BertNLClassifier? = null

    init {
        loadModel()
    }

    private fun loadModel() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                classifier = BertNLClassifier.createFromFile(context, "mobilebert.tflite")
                Log.d("TFLite", "Model başarıyla yüklendi!")
            } catch (e: IOException) {
                Log.e("TFLite", "Model yüklenirken hata oluştu: ${e.message}")
            }
        }
    }

    fun getResponse(inputText: String): String {
        classifier?.let { model ->
            val result = model.classify(inputText)

            Log.d("TFLite", "Model tahminleri: $result") // Logcat'e tahminleri yaz

            if (result.isNotEmpty()) {
                val bestPrediction = result.maxByOrNull { it.score }
                val label = bestPrediction?.label ?: "Bilinmeyen"
                val score = bestPrediction?.score ?: 0.0f

                Log.d("TFLite", "En iyi tahmin: $label, Güven: $score")

                return "Bot: $label (Güven: ${"%.2f".format(score)})"
            }
        }
        return "Bot: Anlamadım, tekrar sorabilir misin?"
    }


    override fun onCleared() {
        classifier?.close()
        super.onCleared()
    }
}
