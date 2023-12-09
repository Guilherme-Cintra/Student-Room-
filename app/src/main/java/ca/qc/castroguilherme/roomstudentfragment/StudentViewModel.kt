package ca.qc.castroguilherme.roomstudentfragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudentRepository

    val allStudents: LiveData<List<Student>>

    init {
        val studentsDao = StudentRoomDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentsDao)
        allStudents = repository.allStudents
    }

     fun getStudentById(id: Int) = repository.getStudentById(id)

    fun insert(student: Student) = viewModelScope.launch(Dispatchers.IO) { repository.insert(student)
    }

    fun update(student: Student) = viewModelScope.launch(Dispatchers.IO) { repository.update(student) }

    fun delete(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("StudentViewModel", "Deleting ${student.firstName} ${student.lastName}")
        repository.delete(student) }
}