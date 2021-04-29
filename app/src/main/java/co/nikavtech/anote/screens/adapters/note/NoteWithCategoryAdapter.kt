package co.nikavtech.anote.screens.adapters.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.nikavtech.anote.R
import co.nikavtech.anote.database.entities.NoteWithCategoryEntity

class NoteWithCategoryAdapter(var diffCallback: DiffUtil.ItemCallback<NoteWithCategoryEntity?>) :
    ListAdapter<NoteWithCategoryEntity?, NoteWithCategoryAdapter.NoteAdapterViewHolder?>(diffCallback) {
    private var listener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapterViewHolder {
        return NoteAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteAdapterViewHolder, position: Int) {
        holder.Bind(getItem(position))
    }

    fun getNoteAt(position: Int): NoteWithCategoryEntity? {
        return getItem(position)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(noteWithCategory: NoteWithCategoryEntity?)
    }

    inner class NoteAdapterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        internal fun Bind(noteWithCategory: NoteWithCategoryEntity?) {
            if (noteWithCategory != null) {
                itemView.findViewById<TextView>(R.id.tv_note_item_title).text = noteWithCategory.noteEntity._title
                itemView.findViewById<TextView>(R.id.tv_note_item_text).text = noteWithCategory.noteEntity._text
                itemView.findViewById<TextView>(R.id.tv_category_title).text = noteWithCategory.categoryEntity?.title
            }
        }

        init {
            itemView.setOnClickListener { v: View? ->
                if (listener != null && adapterPosition != RecyclerView.NO_POSITION) listener!!.onItemClick(
                    getItem(adapterPosition)
                )
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<NoteWithCategoryEntity?> =
            object : DiffUtil.ItemCallback<NoteWithCategoryEntity?>() {
                override fun areItemsTheSame(oldItem: NoteWithCategoryEntity, newItem: NoteWithCategoryEntity): Boolean {
                    return oldItem.noteEntity._id === newItem.noteEntity._id
                }

                override fun areContentsTheSame(oldItem: NoteWithCategoryEntity, newItem: NoteWithCategoryEntity): Boolean {
                    return oldItem.noteEntity._title == newItem.noteEntity._title
                }
            }
    }

}