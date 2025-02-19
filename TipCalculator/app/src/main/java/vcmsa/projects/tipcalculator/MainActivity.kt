package vcmsa.projects.tipcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding

//handling user interaction and calculation when clicking btnClc

class MainActivity() : AppCompatActivity(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //sets up the UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets
        }

        //binding UI components by their ID
        val edtBillAmt = findViewById<EditText>(R.id.edtBillAmt)
        val edtTipPerc = findViewById<EditText>(R.id.edtTipPerc)
        val btnCalc = findViewById<Button>(R.id.btnCalc)
        val txtTip = findViewById<TextView>(R.id.txtTip)
        val edtSplit = findViewById<EditText>(R.id.edtSplit)
        val txtTipPerPerson = findViewById<TextView>(R.id.txtTipPerPerson)

        //?:0.0 means that if its null the default value will be 0.0
        btnCalc.setOnClickListener() {
            val billAmt = edtBillAmt.text.toString().toDoubleOrNull() ?: 0.0
            val tipPerc = edtTipPerc.text.toString().toDoubleOrNull() ?: 0.0
            val split = edtSplit.text.toString().toDoubleOrNull()?:0.0

            //calculation
            val tipAmnt = billAmt * (tipPerc / 100)
            val splitAmount = billAmt / split
            val tipPerPerson = splitAmount * (tipPerc / 100)

            //display
            txtTip.text = "Tip Amount: R${String.format("%.2f", tipAmnt)}"
            txtTipPerPerson.text = "Tip Per Person: R${String.format("%.2f", tipPerPerson)}"
        }
    }
}