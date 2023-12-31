package tn.esprit.pdm.uikotlin.experience
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.pdm.R
import tn.esprit.pdm.models.Experience
import tn.esprit.pdm.uikotlin.SessionManager
import tn.esprit.pdm.utils.ExperienceServices
import tn.esprit.pdm.utils.RetrofitInstance

class AddExperienceFragment(
    private val communityId: String,
    private val onExperienceAdded: (Experience) -> Unit,
) : DialogFragment() {

    private val experienceService: ExperienceServices = RetrofitInstance.createExperienceService()
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(requireContext())
    }
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_experience, container, false)

        val titleEditText: EditText = view.findViewById(R.id.newEditTextTitle)
        val textEditText: EditText = view.findViewById(R.id.newEditTextText)
        val createButton: Button = view.findViewById(R.id.newButtonCreateExperience)

        createButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val text = textEditText.text.toString()
            val token = sessionManager.getUserName().toString()
            val decodedToken = sessionManager.decodeToken(token)
            val username = decodedToken.username
            val newExperience = Experience(username.toString(), title, communityId, text)

            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val response = experienceService.createExperience(newExperience)
                        if (response.isSuccessful) {
                            // Experience created successfully
                            dismiss()
                            // Notify the caller that an experience has been added
                            onExperienceAdded(newExperience)
                        } else {
                            // Handle error
                            // You can show an error message or take appropriate action
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        // Handle network error
                    }
                }
            }
        }

        return view
    }
}