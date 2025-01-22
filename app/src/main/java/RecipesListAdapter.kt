import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaburger.R
import com.example.pizzaburger.databinding.ItemRecipesBurgersBinding
import models.Recipe

class RecipesListAdapter(private val dataSet: List<Recipe>) :
    RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(recipeId: Int)
    }


    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener

    }

    class ViewHolder(binding: ItemRecipesBurgersBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = binding.imageBurger
        val titleTextView: TextView = binding.tvTitleBurgerRecipe
        val cardViewRecipe: CardView = binding.cardViewRecipe
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemRecipesBurgersBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val recipe: Recipe = dataSet[position]
        viewHolder.titleTextView.text = recipe.title

        val drawable = try {
            Drawable.createFromStream(
                viewHolder.imageView.context.assets.open(recipe.imageUrl),
                null
            )
        } catch (e: Exception) {
            Log.d("!!!", "Image not found: ${recipe.imageUrl}")
            null
        }
        viewHolder.imageView.setImageDrawable(drawable)
        viewHolder.imageView.contentDescription = viewHolder.imageView.context.getString(
            R.string.image_content_description, recipe.title
        )

        viewHolder.cardViewRecipe.setOnClickListener {
            itemClickListener?.onItemClick(recipe.id)
        }
    }

    override fun getItemCount(): Int = dataSet.size

}