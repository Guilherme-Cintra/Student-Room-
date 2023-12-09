package ca.qc.castroguilherme.roomstudentfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ca.qc.castroguilherme.roomstudentfragment.databinding.FragmentAddStudentBinding
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddStudentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddStudentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    private lateinit var binding: FragmentAddStudentBinding

    val studentViewModel: StudentViewModel by lazy {
        ViewModelProvider(this, StudentViewModelFactory(requireActivity().application)).get(
            StudentViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddStudentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var nom = binding.idNom.text.toString()
        var prenom = binding.prenomId.text.toString()
        var courriel = binding.idCourriel.text.toString()
        var telephone = binding.tel.text.toString()



        binding.button.setOnClickListener {
            nom = binding.idNom.text.toString()
             prenom = binding.prenomId.text.toString()
         courriel = binding.idCourriel.text.toString()
            telephone = binding.tel.text.toString()

            if (nom != "" && prenom != "" && courriel != "" && telephone != "") {

                var student = Student(
                    0, prenom, nom, courriel, telephone
                )
                studentViewModel.insert(student)
                Snackbar.make(view, "Étudiant ajouté", Snackbar.LENGTH_LONG).show()
                findNavController().navigate(R.id.allStudentsFragment)
            }

            else {
                Snackbar.make(view, "Veuillez remplir tous les chaps pour pouvoir ajouter un nouvel étudiant", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddStudentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddStudentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}