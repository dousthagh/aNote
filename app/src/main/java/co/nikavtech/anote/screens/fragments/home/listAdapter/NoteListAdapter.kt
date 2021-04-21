package co.nikavtech.anote.screens.fragments.home.listAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import co.nikavtech.anote.R
import co.nikavtech.anote.database.entities.NoteEntity

class NoteListAdapter(noteItemEvent: NoteItemEvent) : RecyclerView.Adapter<ViewHolder>(), Filterable {
    private var noteList: List<NoteEntity> = arrayListOf()
    private var filteredNoteList: List<NoteEntity> = arrayListOf()
    private val listener: NoteItemEvent = noteItemEvent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_note_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredNoteList[position], listener)
    }

    override fun getItemCount(): Int = filteredNoteList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString = p0.toString()
                filteredNoteList = if (charString.isEmpty()) {
                    noteList
                } else {
                    val filteredList = arrayListOf<NoteEntity>()
                    for (row in noteList) {
                        if (row._title?.toLowerCase()!!.contains(charString.toLowerCase())
                            || row._text!!.contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = filteredNoteList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filteredNoteList = p1?.values as List<NoteEntity>
                notifyDataSetChanged()
            }

        }
    }

    fun setAllNoteItem(noteItems:List<NoteEntity>){
        this.noteList = noteItems
        this.filteredNoteList = noteItems
        notifyDataSetChanged()
    }
}
