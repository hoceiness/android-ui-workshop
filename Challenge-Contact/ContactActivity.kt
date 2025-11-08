package com.example.contactapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// The incorrect import has been removed from here.

class ContactActivity : AppCompatActivity() {

    // 1. DÉCLARER toutes les vues
    private lateinit var etFullName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAddress1: EditText
    private lateinit var etAddress2: EditText
    private lateinit var rgCategory: RadioGroup
    private lateinit var btnAdd: Button
    private lateinit var btnReset: Button
    private lateinit var tvContactDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        // 2. FAIRE LE BINDING de toutes les vues
        etFullName = findViewById(R.id.etFullName)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        etAddress1 = findViewById(R.id.etAddress1)
        etAddress2 = findViewById(R.id.etAddress2)
        rgCategory = findViewById(R.id.rgCategory)
        btnAdd = findViewById(R.id.btnAdd)
        btnReset = findViewById(R.id.btnReset)
        tvContactDisplay = findViewById(R.id.tvContactDisplay)

        // 3. DÉFINIR les événements des boutons
        btnAdd.setOnClickListener {
            ajouterContact()
        }
        btnReset.setOnClickListener {
            reinitialiser()
        }
    }

    // 4. CRÉER la fonction ajouterContact()
    private fun ajouterContact() {
        // - Récupérer toutes les valeurs
        val nom = etFullName.text.toString().trim()
        val telephone = etPhone.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val adresse1 = etAddress1.text.toString().trim()
        val adresse2 = etAddress2.text.toString().trim()

        // - Vérifier qu'aucun champ obligatoire n'est vide
        if (nom.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer le nom complet", Toast.LENGTH_SHORT).show()
            return
        }
        if (telephone.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer le numéro de téléphone", Toast.LENGTH_SHORT).show()
            return
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer l'email", Toast.LENGTH_SHORT).show()
            return
        }
        if (adresse1.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer la ligne d'adresse 1", Toast.LENGTH_SHORT).show()
            return
        }

        // - Récupérer la catégorie sélectionnée
        val selectedId = rgCategory.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Veuillez sélectionner une catégorie", Toast.LENGTH_SHORT).show()
            return
        }
        val radioButton = findViewById<RadioButton>(selectedId)
        val category = radioButton.text.toString()

        // Combiner l'adresse
        val fullAddress = if (adresse2.isNotEmpty()) "$adresse1, $adresse2" else adresse1

        // - Créer un texte formaté avec toutes les infos
        val contactInfo = """
            📇 Contact ajouté:
            
            Nom: $nom
            📞 Tél: $telephone
            📧 Email: $email
            🏠 Adresse: $fullAddress
            🏷 Catégorie: $category
        """.trimIndent()

        // - Afficher dans tvContactDisplay
        tvContactDisplay.text = contactInfo

        // - Afficher un Toast de confirmation
        Toast.makeText(this, "Contact '$nom' ajouté avec succès!", Toast.LENGTH_LONG).show()
    }

    // 5. CRÉER la fonction reinitialiser()
    private fun reinitialiser() {
        // - Vider tous les EditText
        etFullName.setText("")
        etPhone.setText("")
        etEmail.setText("")
        etAddress1.setText("")
        etAddress2.setText("")

        // - Décocher les RadioButtons
        rgCategory.clearCheck()

        // - Réinitialiser tvContactDisplay
        tvContactDisplay.text = "Aucun contact ajouté"

        // - Afficher un Toast
        Toast.makeText(this, "Formulaire réinitialisé", Toast.LENGTH_SHORT).show()
    }
}
