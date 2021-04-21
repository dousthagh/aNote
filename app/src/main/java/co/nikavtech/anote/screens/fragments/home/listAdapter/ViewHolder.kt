package co.nikavtech.anote.screens.fragments.home.listAdapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.nikavtech.anote.R
import co.nikavtech.anote.database.entities.NoteEntity

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(noteEntity: NoteEntity, listener: NoteItemEvent) {
        val lblTitleView = itemView.findViewById<TextView>(R.id.lbl_note_item_title)
        val lblTextView = itemView.findViewById<TextView>(R.id.lbl_note_item_text)
        val lblInsertedDateView = itemView.findViewById<TextView>(R.id.lbl_note_item_inserted_date)

        lblTitleView.setText(noteEntity._title)
        lblTextView.setText(noteEntity._text)
//        lblInsertedDateView.setText(noteDataObject.insertedDate)
    }
}