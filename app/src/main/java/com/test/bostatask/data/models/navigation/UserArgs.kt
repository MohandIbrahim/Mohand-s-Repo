package com.test.bostatask.data.models.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class UserArgs (
    val address: String,
    val userId: Long,
    val name: String,
): Parcelable