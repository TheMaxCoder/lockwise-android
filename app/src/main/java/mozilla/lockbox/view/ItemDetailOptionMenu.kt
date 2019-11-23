/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mozilla.lockbox.view

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import mozilla.lockbox.R

class ItemDetailOptionMenu(
    val context: Context,
    private val listener: View.OnClickListener
) : PopupWindow(), View.OnClickListener {

    var dismissListener: (() -> Unit)? = null

    init {
        contentView =
            LayoutInflater.from(context).inflate(R.layout.menu_fragment_item_details, null)
        setBackgroundDrawable(context.getDrawable(R.drawable.sort_menu_bg))
        isFocusable = true
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        width = ViewGroup.LayoutParams.WRAP_CONTENT
        elevation = context.resources.getDimension(R.dimen.menu_elevation)
        contentView.findViewById<Button>(R.id.edit).setOnClickListener(this)
        contentView.findViewById<Button>(R.id.delete).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        dismiss()
        listener.onClick(v)
    }

    fun show(anchor: View) {
        super.showAtLocation(anchor, Gravity.TOP or Gravity.END, 0, 0)
    }

    override fun dismiss() {
        super.dismiss()
        dismissListener?.invoke()
    }
}