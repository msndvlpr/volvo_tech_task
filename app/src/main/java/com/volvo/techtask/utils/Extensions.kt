/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.utils

import android.content.Context
import android.widget.Toast


fun String.makeToast(context: Context){
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}