package co.nikavtech.anote.screens.fragments.home.listAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import co.nikavtech.anote.R
import co.nikavtech.anote.database.entities.NoteWithCategoryEntity

class NoteListAdapter(noteItemEvent: NoteItemEvent) : RecyclerView.Adapter<ViewHolder>(), Filterable {
    private var noteListEntity: List<NoteWithCategoryEntity> = arrayListOf()
    private var filteredNoteListEntity: List<NoteWithCategoryEntity> = arrayListOf()
    private val listener: NoteItemEvent = noteItemEvent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_note_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredNoteListEntity[position], listener)
    }

    override fun getItemCount(): Int = filteredNoteListEntity.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString = p0.toString()
                filteredNoteListEntity = if (charString.isEmpty()) {
                    noteListEntity
                } else {
                    val filteredList = arrayListOf<NoteWithCategoryEntity>()
                    for (row in noteListEntity) {
                        if (row.noteEntity._title?.toLowerCase()!!.contains(charString.toLowerCase())
                            || row.noteEntity._text!!.contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = filteredNoteListEntity
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filteredNoteListEntity = p1?.values as List<NoteWithCategoryEntity>
                notifyDataSetChanged()
            }

        }
    }

    fun setAllNoteItem(noteItemEntities:List<NoteWithCategoryEntity>){
        this.noteListEntity = noteItemEntities
        this.filteredNoteListEntity = noteItemEntities
        notifyDataSetChanged()
    }
}
