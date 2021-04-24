package co.nikavtech.anote.screens.adapters.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.nikavtech.anote.R
import co.nikavtech.anote.database.entities.CategoryEntity

class CategoryAdapter(var diffCallback: DiffUtil.ItemCallback<CategoryEntity?>) :
    ListAdapter<CategoryEntity?, CategoryAdapter.CategoryAdapterViewHolder?>(diffCallback) {
    private var listener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapterViewHolder {
        return CategoryAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryAdapterViewHolder, position: Int) {
        holder.Bind(getItem(position))
    }

    fun getReminderAt(position: Int): CategoryEntity? {
        return getItem(position)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(reminder: CategoryEntity?)
    }

    inner class CategoryAdapterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        internal fun Bind(category: CategoryEntity?) {
            if (category != null) {
                itemView.findViewById<TextView>(R.id.tv_category_title).text = category.title
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
        val DIFF_CALLBACK: DiffUtil.ItemCallback<CategoryEntity?> =
            object : DiffUtil.ItemCallback<CategoryEntity?>() {
                override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
                    return oldItem.id === newItem.id
                }

                override fun areContentsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
                    return oldItem.title == newItem.title
                }
            }
    }

}