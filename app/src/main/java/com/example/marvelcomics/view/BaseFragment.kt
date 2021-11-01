package com.example.marvelcomics.view

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.marvelcomics.R

open class BaseFragment : Fragment() {

    fun showCommunicationError() = AlertDialog.Builder(requireContext()).apply {
        setTitle(getString(R.string.title_error))
        setMessage(getString(R.string.description_error))
        setNegativeButton(getString(R.string.negative_button_error)) { dialog, _ ->
            dialog.dismiss()
        }
        create()
        show()
    }
}