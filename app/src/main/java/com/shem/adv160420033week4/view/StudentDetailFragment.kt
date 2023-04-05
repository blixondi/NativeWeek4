package com.shem.adv160420033week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.shem.adv160420033week4.R
import com.shem.adv160420033week4.util.loadImage
import com.shem.adv160420033week4.viewmodel.DetailViewModel


class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var student_id = ""

        arguments?.let{
            student_id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(student_id)

        val studentPhoto = view.findViewById<ImageView>(R.id.imageView2)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar2)
        val txtID = view.findViewById<TextView>(R.id.txtID)
        val txtStudentName = view.findViewById<TextView>(R.id.txtStudentName)
        val txtDoB = view.findViewById<TextView>(R.id.txtBod)
        val txtPhone = view.findViewById<TextView>(R.id.txtPhone)

        viewModel.studentLD.observe(viewLifecycleOwner) { student ->
            txtID.text = student.id
            txtStudentName.text = student.name
            txtDoB.text = student.dob
            txtPhone.text = student.phone
            studentPhoto.loadImage(student.photoUrl, progressBar)

        }
    }
}