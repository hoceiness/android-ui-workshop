package com.example.tpdistance1
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    // Déclaration des vues
    private lateinit var tvResult: TextView
    private lateinit var etNumber1: EditText
    private lateinit var etNumber2: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnSubtract: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Binding des vues (liaison avec le XML)
        tvResult = findViewById(R.id.tvResult)
        etNumber1 = findViewById(R.id.etNumber1)
        etNumber2 = findViewById(R.id.etNumber2)
        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        // Événement du bouton Addition
        btnAdd.setOnClickListener {
            calculer(operation = "addition")
        }
        // Événement du bouton Soustraction
        btnSubtract.setOnClickListener {
            calculer(operation = "soustraction")
        }
    }
    // Fonction pour effectuer les calculs
    private fun calculer(operation: String) {
        // Récupérer le texte des EditText
        val nombre1Text = etNumber1.text.toString()
        val nombre2Text = etNumber2.text.toString()
        // Vérifier si les champs sont vides
        if (nombre1Text.isEmpty() || nombre2Text.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir les deux nombres",
                Toast.LENGTH_SHORT).show()
            return
        }
        // Convertir en nombres
        val nombre1 = nombre1Text.toDouble()
        val nombre2 = nombre2Text.toDouble()
        // Calculer selon l'opération
        val resultat = when (operation) {
            "addition" -> nombre1 + nombre2
            "soustraction" -> nombre1 - nombre2
            else -> 0.0
        }
        // Afficher le résultat
        tvResult.text = "Résultat : $resultat"
    }
}
