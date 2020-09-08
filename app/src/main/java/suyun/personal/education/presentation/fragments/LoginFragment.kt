package suyun.personal.education.presentation.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.room.Room
import androidx.room.RoomDatabase
import suyun.personal.education.EducationApplication
import suyun.personal.education.R
import suyun.personal.education.data.*
import suyun.personal.education.di.DaggerEducationComponentSuper
import suyun.personal.education.di.EducationModule
import suyun.personal.education.presentation.dialog.DialogLoginInformation
import suyun.personal.education.presentation.fragments.DetailFragment
import javax.inject.Inject

class LoginFragment : Fragment() {

    var buttonAction: Button? = null

    @Inject lateinit var student2: Student
    @Inject lateinit var student: Student
    @Inject lateinit var dog: Dog
    @Inject lateinit var backPack: Backpack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerEducationComponentSuper
            .builder()
            .educationModule(EducationModule())
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = LayoutInflater.from(context).inflate(R.layout.fragment_login, container, false)

        student.initiatePrintName()

        return view
    }

    fun initiateDisplayAlertDialog(){
        var builder = AlertDialog.Builder(context)
            .setTitle("Example")
            .setMessage("This is example message")
            .setPositiveButton("Positive", object: DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Log.d("Dialog", "Clicked")
                }
            })
            .setNegativeButton("Negative", object: DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Log.d("Dialog", "Clicked negative")
                }
            })
            .setNeutralButton("Neutral", object: DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Log.d("Dialog", "Clicked neutral")
                }
            })

        builder.create().show()
    }

    fun initiateDisplayLoginDialog(){
        var fragmentManager = activity?.supportFragmentManager
        var dialogLoginInformation = DialogLoginInformation()
        dialogLoginInformation.show(fragmentManager!!, DialogLoginInformation.TAG)
    }

    fun initializeDetailFragment(){
        var detailFragment =
            StudentsViewPagerFragment()

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.framelayout_activity_main_fragment_container, detailFragment)
            ?.commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var student = StudentEntity()
        student.name = "John"

        var db = Room.databaseBuilder(context!!, EducationDatabase::class.java, "education.db").allowMainThreadQueries().build()

//        db.getStudentDao().initiateInsertStudent(student)
//        db.getStudentDao().initiateInsertStudent(student)
//        db.getStudentDao().initiateInsertStudent(student)
//
//        var listStudents: List<StudentEntity>? = db.getStudentDao().initiateGetStudents()
//
//        Log.d("Students", listStudents?.size?.toString() ?: 0.toString())
//        Log.d("Students", listStudents?.get(0)?.name ?: " Empty")
        buttonAction = view?.findViewById(R.id.button_activity_main_fill_data)
        buttonAction?.setOnClickListener { initiateDisplayLoginDialog() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}