package com.example.myloginapp.Dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.datastorage.R
import kotlin.properties.Delegates


class ExampleDialog(position : Int,msg:String) : AppCompatDialogFragment() {
    private var pos: Int = position
    private var str : String =msg
    private var editTextUsername: EditText? = null
    private var listener: ExampleDialogListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(
            requireActivity()
        )
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.add_msg_layout, null)
        var textdata = view.findViewById<TextView>(R.id.edit_username)
        textdata.text = str
        builder.setView(view)
            .setCancelable(false)
            .setTitle("Edit your message")
            .setNegativeButton("cancel") { dialogInterface, i -> }
            .setPositiveButton("ok") { dialogInterface, i ->
                val username = editTextUsername!!.text.toString()
                listener!!.applyTexts(username,pos)
            }
        editTextUsername = view.findViewById(R.id.edit_username)
        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = try {
            context as ExampleDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString() +
                        "must implement ExampleDialogListener"
            )
        }
    }

    interface ExampleDialogListener {
        fun applyTexts(username: String?,position: Int)
    }
}