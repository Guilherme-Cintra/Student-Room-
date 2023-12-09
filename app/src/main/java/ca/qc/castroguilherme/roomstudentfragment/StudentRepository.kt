package ca.qc.castroguilherme.roomstudentfragment

import androidx.lifecycle.LiveData

class StudentRepository(private val studentDAO: StudentDAO) {
    // Room execute les requêtes dans un thread séparé
    //LivaData observé va notifier le observer à chaque modification

    val allStudents: LiveData<List<Student>> = studentDAO.getStudents()

     fun getStudentById(id: Int): LiveData<Student?>{
        return studentDAO.getStudent(id)
    }

    //suspend indique que la fonciton doit être appelé par une coroutine ou une autre fonction de suspension
    suspend fun insert(student: Student){
        studentDAO.insert(student)
    }

    suspend fun update(student: Student){
        studentDAO.updateStudent(student)
    }

    suspend fun delete(student: Student){
        studentDAO.deleteStudent(student)
    }
}