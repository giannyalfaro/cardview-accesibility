package com.example.cardviewactivity

import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat


class MainActivity : AppCompatActivity() {

    private var myCardOne:CardView? = null
    private var myCardTwo:CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myCardOne = findViewById<View>(R.id.cardView_one_account) as CardView
        myCardTwo = findViewById<View>(R.id.cardView_two_account) as CardView

        ViewCompat.setAccessibilityDelegate(myCardOne!!, object : AccessibilityDelegateCompat() {

            override fun onPopulateAccessibilityEvent(host: View, event: AccessibilityEvent) {
                super.onPopulateAccessibilityEvent(host, event)
                // We call the super implementation to populate its text for the
                // event. Then we add our text not present in a super class.
                // Very often you only need to add the text for the custom view.
                /*if (text?.isNotEmpty() == true) {
                    event.text.add(text)
                }*/
            }

            override fun onInitializeAccessibilityEvent(host: View, event: AccessibilityEvent) {
                super.onInitializeAccessibilityEvent(host, event);
                // We call the super implementation to let super classes
                // set appropriate event properties. Then we add the new property
                // (checked) which is not supported by a super class.
                event.action = AccessibilityEvent.TYPE_VIEW_CLICKED;
            }

            override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                // We call the super implementation to let super classes set
                // appropriate info properties. Then we add our properties
                // (checkable and checked) which are not supported by a super class.
                info.isFocusable = true
                info.isSelected = true
                // Quite often you only need to add the text for the custom view.

                val contentDescriptionAmount = host.context.getString(R.string.soles_amount_account).replace("S/", "")
                val contentDescriptionAccountNumber = host.context.getString(R.string.bcp_number_account)
                val lastCharacter: String = contentDescriptionAccountNumber.substring(contentDescriptionAccountNumber.length - 2)

                info.contentDescription = host.context.getString(R.string.soles_saving_account) + ", " + contentDescriptionAmount + "Nuevos Soles" + ", " +  " Número de cuenta que termina en " + lastCharacter

                info.roleDescription = "Botón"

                val customClick = AccessibilityActionCompat(
                        AccessibilityNodeInfoCompat.ACTION_CLICK,
                        host.context.getString(
                            R.string.description_accessibility_text))
                info.addAction(customClick)
            }
        })

        ViewCompat.setAccessibilityDelegate(myCardTwo!!, object : AccessibilityDelegateCompat() {

            override fun onPopulateAccessibilityEvent(host: View, event: AccessibilityEvent) {
                super.onPopulateAccessibilityEvent(host, event)
                // We call the super implementation to populate its text for the
                // event. Then we add our text not present in a super class.
                // Very often you only need to add the text for the custom view.
                /*if (text?.isNotEmpty() == true) {
                    event.text.add(text)
                }*/
            }

            override fun onInitializeAccessibilityEvent(host: View, event: AccessibilityEvent) {
                super.onInitializeAccessibilityEvent(host, event);
                // We call the super implementation to let super classes
                // set appropriate event properties. Then we add the new property
                // (checked) which is not supported by a super class.
                event.action = AccessibilityEvent.TYPE_VIEW_CLICKED;
            }

            override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                // We call the super implementation to let super classes set
                // appropriate info properties. Then we add our properties
                // (checkable and checked) which are not supported by a super class.
                // Quite often you only need to add the text for the custom view.
                val contentDescriptionAmount = host.context.getString(R.string.soles_amount_account_two).replace("S/", "")
                val contentDescriptionAccountNumber = host.context.getString(R.string.bcp_number_two_account)
                val lastCharacter: String = contentDescriptionAccountNumber.substring(contentDescriptionAccountNumber.length - 2)

                info.contentDescription = host.context.getString(R.string.soles_saving_account) + ", " + contentDescriptionAmount + " Nuevos Soles" + ", " + "Número de cuenta que termina en " + lastCharacter

                info.roleDescription = "Botón"

                val customClick = AccessibilityActionCompat(
                    AccessibilityNodeInfoCompat.ACTION_CLICK,
                    host.context.getString(
                        R.string.description_accessibility_text))
                info.addAction(customClick)
            }
        })
    }
}