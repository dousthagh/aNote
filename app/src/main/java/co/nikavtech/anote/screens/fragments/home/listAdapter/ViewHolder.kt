package co.nikavtech.anote.screens.fragments.home.listAdapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.nikavtech.anote.R
import co.nikavtech.anote.database.entities.NoteWithCategoryEntity

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(noteWithCategoryEntity: NoteWithCategoryEntity, listener: NoteItemEvent) {
        val lblTitleView = itemView.findViewById<TextView>(R.id.lbl_note_item_title)
        val lblTextView = itemView.findViewById<TextView>(R.id.lbl_note_item_text)
        val lblNoteCategoryView = itemView.findViewById<TextView>(R.id.tv_category_title)

        lblTitleView.setText(noteWithCategoryEntity.noteEntity._title)
        lblTextView.setText(noteWithCategoryEntity.noteEntity._text)
        lblNoteCategoryView.setText(noteWithCategoryEntity.categoryEntity?.title)
    }
}