package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_PREDICTION = "extra_prediction"
        const val EXTRA_CONFIDENCE = "extra_confidence"
    }

    private lateinit var binding: ActivityResultBinding

    private lateinit var currentImageUri: Uri
    private lateinit var prediction: String
    private var confidenceScore: Float = 0f


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResultBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.
        currentImageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        prediction = intent.getStringExtra(EXTRA_PREDICTION) ?: ""
        confidenceScore = intent.getFloatExtra(EXTRA_CONFIDENCE, 0f)
        val resultText = getString(R.string.result_text, prediction, confidenceScore)

        binding.resultImage.setImageURI(currentImageUri)
        binding.resultText.text = resultText

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}