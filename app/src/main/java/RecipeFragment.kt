import RecipesListFragment.Companion.ARG_RECIPE
import RecipesListFragment.Companion.ARG_RECIPE_NAME
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.replace
import com.example.pizzaburger.R
import com.example.pizzaburger.databinding.ActivityMainBinding
import com.example.pizzaburger.databinding.FragmentRecipeBinding
import com.example.pizzaburger.databinding.FragmentRecipesListBinding
import models.Recipe


class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for RecipeFragmentBinding must not be null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipe: Recipe? = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(ARG_RECIPE, Recipe::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable(ARG_RECIPE)
        }

        recipe?.let {
            displayRecipeData(it)
        }

    }

    private fun displayRecipeData(recipe: Recipe) {
        _binding?.tvFragmentRecipe?.text = recipe.title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}