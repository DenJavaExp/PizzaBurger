import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaburger.R
import com.example.pizzaburger.databinding.ItemCategoryBinding
import models.Category

class CategoriesListAdapter(private val dataSet: List<Category>) :
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(categoryId: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class ViewHolder(binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = binding.imageCategory
        val titleTextView: TextView = binding.tvTitleCategory
        val descriptionTextView: TextView = binding.description
        val cardView: CardView = binding.cardView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemCategoryBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val category: Category = dataSet[position]
        viewHolder.titleTextView.text = category.title
        viewHolder.descriptionTextView.text = category.description

        val drawable = try {
            Drawable.createFromStream(
                viewHolder.imageView.context.assets.open(category.imageUrl),
                null
            )
        } catch (e: Exception) {
            Log.d("!!!", "Image not found: ${category.imageUrl}")
            null
        }
        viewHolder.imageView.setImageDrawable(drawable)
        viewHolder.imageView.contentDescription = viewHolder.imageView.context.getString(
            R.string.image_content_description, category.title
        )

        viewHolder.cardView.setOnClickListener {
            itemClickListener?.onItemClick(category.id)
        }
    }

    override fun getItemCount() = dataSet.size

}
