package ca.qc.castroguilherme.roomstudentfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.castroguilherme.roomstudentfragment.databinding.FragmentAllStudentsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AllStudentsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllStudentsFragment : Fragment() {

    private lateinit var binding: FragmentAllStudentsBinding

    //    ViewModel
    private val studentViewModel: StudentViewModel by lazy {
        ViewModelProvider(this, StudentViewModelFactory(requireActivity().application)).get(
            StudentViewModel::class.java
        )

    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        binding = FragmentAllStudentsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = StudentsAdapter(requireContext())
        binding.recyclerView.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.addStudentFragment)
        }

        adapter.onEditClickListener = {
            val action =
                AllStudentsFragmentDirections.actionAllStudentsFragmentToEditStudentFragment(it)
            findNavController().navigate(action)
        }

        adapter.onDeleteClickListener = {
            studentViewModel.delete(it)
        }

        studentViewModel.allStudents.observe(viewLifecycleOwner, Observer { students ->
            students?.let { adapter.setStudents(it) }

        })


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AllStudentsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllStudentsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}