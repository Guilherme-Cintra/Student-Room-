package ca.qc.castroguilherme.roomstudentfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ca.qc.castroguilherme.roomstudentfragment.databinding.FragmentEditStudentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditStudentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditStudentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val args: EditStudentFragmentArgs by navArgs()



   private lateinit var student: Student

    private lateinit var binding: FragmentEditStudentBinding
    var prenom = ""
    var nom = ""
    var numero = ""
    var courriel = ""

    private val studentViewModel: StudentViewModel by lazy {
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
        binding = FragmentEditStudentBinding.inflate(layoutInflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        student  = args.student

        if (student == null) {
            requireActivity().finish()
            return
        }






                    binding.prenomId.setText(student.firstName)
                    binding.idNom.setText(student.lastName)
                    binding.idCourriel.setText(student.email)
                    binding.editTextText3.setText(student.phoneNumber)





        binding.buttonEdit.setOnClickListener {
            prenom = binding.prenomId.text.toString()
            nom = binding.idNom.text.toString()
            courriel = binding.idCourriel.text.toString()
            numero = binding.editTextText3.text.toString()

            val etuModifie =  Student(student.id, prenom, nom, numero, courriel)
            if (etuModifie != null) {
                studentViewModel.update(etuModifie)
            }

            findNavController().navigate(R.id.allStudentsFragment)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditStudentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditStudentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}