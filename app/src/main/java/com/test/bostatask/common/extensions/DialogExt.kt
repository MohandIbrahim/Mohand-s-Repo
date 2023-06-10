package com.test.bostatask.common.extensions

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.task.databinding.PhotoDialogBinding

fun Fragment.openPhotoDialog(photoTitle:String, photoUrl:String)  = activity?.openPhotoDialog(photoTitle,photoUrl)
fun Activity.openPhotoDialog(photoTitle:String, photoUrl:String) = photoDialog(photoTitle,photoUrl)
fun Context.photoDialog(photoTitle:String, photoUrl:String): Dialog {
    val photoDialogBinding = PhotoDialogBinding.inflate(LayoutInflater.from(this))
    val dialog = Dialog(this)
    dialog.setContentView(photoDialogBinding.root)
    dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    photoDialogBinding.dialogTitle.text = photoTitle
    photoDialogBinding.photoZoomingHolder.loadImageFromUrl(photoUrl)
    dialog.show()
    return dialog
}