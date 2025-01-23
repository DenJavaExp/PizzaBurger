import CategoriesListFragment.Companion.ARG_CATEGORY_ID
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.pizzaburger.R
import com.example.pizzaburger.databinding.FragmentRecipesListBinding


class RecipesListFragment: Fragment() {

    private var _binding: FragmentRecipesListBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityMainBinding must not be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecycler() {
        val categoryId = arguments?.getInt(ARG_CATEGORY_ID)
            ?: throw IllegalArgumentException("Category ID is missing.")
        val recipeAdapter = RecipesListAdapter(STUB.getRecipesByCategoryId(categoryId))
        binding.rvRecipes.adapter = recipeAdapter

        recipeAdapter.setOnItemClickListener(object : RecipesListAdapter.OnItemClickListener {
            override fun onItemClick(recipeId: Int) {
                openRecipesByRecipeId(recipeId)
            }
        })
    }

    private fun openRecipesByRecipeId(recipeId: Int) {
        val recipe = STUB.getRecipesByCategoryId(recipeId).find { it.id == recipeId }
        val recipeName = recipe?.title
        val recipeUrl = recipe?.imageUrl
        val bundle = bundleOf(
            ARG_RECIPE_ID to recipeId,
            ARG_RECIPE_NAME to recipeName,
            ARG_RECIPE_IMAGE_URL to recipeUrl
        )
        replaceFragment(RecipeFragment(), bundle)
    }

    private fun replaceFragment(fragment: Fragment, bundle: Bundle) {
        fragment.arguments = bundle
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<RecipeFragment>(R.id.mainContainer, args = bundle)
            addToBackStack(null)
        }
    }

    companion object {
        const val ARG_RECIPE_ID = "arg_recipe_id"
        const val ARG_RECIPE_NAME = "arg_recipe_name"
        const val ARG_RECIPE_IMAGE_URL = "arg_recipe_image_url"
    }

}