package ca.qc.castroguilherme.roomstudentfragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StudentViewModelFactory(private val application: Application): ViewModelProvider.AndroidViewModelFactory(application){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(StudentViewModel::class.java)){
            StudentViewModel(application) as T
        } else{
            throw IllegalArgumentException("Unkown ViewModel Class")
        }
    }
}