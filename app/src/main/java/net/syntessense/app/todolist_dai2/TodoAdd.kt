package net.syntessense.app.todolist_dai2

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat as DF
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.text.DateFormat
import java.util.*

class TimePickerFragment(val callback: (Int, Int) -> Unit) : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, this, hour, minute, DF.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        // Do something with the time chosen by the user
        callback(hourOfDay, minute)
    }
}
class DatePickerFragment(private val activity: Context) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(activity, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user
    }
}

class TodoAdd : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide()
        setContentView(R.layout.activity_todo_add)
        getSupportActionBar()?.elevation = 0F



        val fab = findViewById<View>(R.id.fab)
        fab.setOnLongClickListener(View.OnLongClickListener {
            true
        })

        fab.setOnClickListener(View.OnClickListener {
            finish()
            true
        })

        //getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)


        val todoDate = Date()

        val tpicker = findViewById<TextView>(R.id.time_text)
        tpicker.setOnClickListener(View.OnClickListener {
            val tp = TimePickerFragment { h, m ->
                todoDate.hours = h
                todoDate.minutes = m
                tpicker.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(todoDate)
            }
            tp.show(supportFragmentManager, "timePicker")
            //tpicker.performClick()
            true
        })

        /*
        val dpicker = findViewById<Button>(R.id.datepicker)
        dpicker.setOnTouchListener { _, event ->
            if (event?.action == MotionEvent.ACTION_DOWN) {
                val newFragment = DatePickerFragment(this)
                newFragment.show(supportFragmentManager, "datePicker")
            }
            dpicker.performClick()
            true
        }
        */
    }
}
