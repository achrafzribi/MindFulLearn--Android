package tn.esprit.pdm.uikotlin.hospital
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.pdm.R
import com.google.android.material.snackbar.Snackbar
class ReservationHospital : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_hospital)
        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener { finish()}
        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            Snackbar.make(it, "Réservation effectuée avec succès!", Snackbar.LENGTH_LONG).show()
        }
    }
}