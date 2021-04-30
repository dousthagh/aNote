package co.nikavtech.anote.screens.adapters.category.select_categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.nikavtech.anote.R
import co.nikavtech.anote.database.entities.CategoryEntity

class SelectCategoryAdapter(var diffCallback: DiffUtil.ItemCallback<CategoryEntity?>) :
    ListAdapter<CategoryEntity?, SelectCategoryAdapter.SelectCategoryAdapterViewHolder?>(
        diffCallback
    ) {
    private var listener: OnItemClickListener? = null
    private var selectedIndex: Int = -1
    private var selectedCategoryId: Int = -1


    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<CategoryEntity?> =
            object : DiffUtil.ItemCallback<CategoryEntity?>() {
                override fun areItemsTheSame(
                    oldItem: CategoryEntity,
                    newItem: CategoryEntity
                ): Boolean {
                    return oldItem.id === newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: CategoryEntity,
                    newItem: CategoryEntity
                ): Boolean {
                    return oldItem.title == newItem.title
                }
            }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectCategoryAdapterViewHolder {
        return SelectCategoryAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.select_category_item, parent, false)
        )
    }

    override fun onBindViewHolder(holderSelect: SelectCategoryAdapterViewHolder, position: Int) {
        holderSelect.Bind(getItem(position))
        val view = holderSelect.itemView

        holderSelect.itemView.setOnClickListener {
            this.listener?.onItemClick(getItem(position))
        }
        if (selectedCategoryId > -1) {
            if (getCategoryAt(position)?.id == selectedCategoryId) {
                selectedIndex = position
                view.findViewById<ImageView>(R.id.imgSelect).visibility = View.VISIBLE
            } else
                view.findViewById<ImageView>(R.id.imgSelect).visibility = View.GONE
        }
    }

    fun setSelectCategoryItem(id: Int) {
        selectedCategoryId = id
    }

    fun getCategoryAt(position: Int): CategoryEntity? {
        return getItem(position)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    private fun selectItem(view: View) {
        view.findViewById<ImageView>(R.id.imgSelect).visibility = View.VISIBLE
    }

    private fun unSelectItem(view: View) {
        view.findViewById<ImageView>(R.id.imgSelect).visibility = View.GONE
    }

    interface OnItemClickListener {
        fun onItemClick(category: CategoryEntity?)
    }

    inner class SelectCategoryAdapterViewHolder(itemView: View) :
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


}
