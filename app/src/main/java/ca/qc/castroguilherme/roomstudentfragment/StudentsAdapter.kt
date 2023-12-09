package ca.qc.castroguilherme.roomstudentfragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentsAdapter (context: Context):
    RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var students = emptyList<Student>()

    var onEditClickListener: ((Student) -> Unit)? = null

    var onDeleteClickListener:((Student) -> Unit)? = null

    inner class StudentViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val fristName: TextView = itemView.findViewById(R.id.ItemPrenom)
        val lastName: TextView = itemView.findViewById(R.id.textView6)
        val tel: TextView = itemView.findViewById(R.id.textView7)
        val courriel: TextView = itemView.findViewById(R.id.textView8)
        val editBtn: FloatingActionButton = itemView.findViewById(R.id.edit)
        val deleteBtn: FloatingActionButton = itemView.findViewById(R.id.delete)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder {
        val itemView = inflater.inflate(R.layout.recycler_item, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentsAdapter.StudentViewHolder, position: Int) {
        val current = students[position]
        holder.fristName.text = current.firstName
        holder.lastName.text = current.lastName
        holder.tel.text = current.phoneNumber
        holder.courriel.text = current.email

        holder.editBtn.setOnClickListener { onEditClickListener?.invoke(current) }
        holder.deleteBtn.setOnClickListener { onDeleteClickListener?.invoke(current) }

    }

    fun setStudents(students: List<Student>){
        this.students = students
        notifyDataSetChanged()
    }
    override fun getItemCount() = students.size
}