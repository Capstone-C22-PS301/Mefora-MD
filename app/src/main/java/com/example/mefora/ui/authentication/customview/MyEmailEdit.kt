package com.example.mefora.ui.authentication.customview

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import androidx.appcompat.widget.AppCompatEditText
import com.example.mefora.R

class MyEmailEditText : AppCompatEditText {

    constructor(context: Context) : super(context){
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }


    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Do nothing.
            }
            override fun afterTextChanged(s: Editable) {
                error = when {
                    !Patterns.EMAIL_ADDRESS.matcher(s).matches() -> context.getString(R.string.error_email)
                    s.isEmpty() -> context.getString(R.string.error_empty)
                    else -> null
                }
                setError(error, null)
            }
        })
    }
}