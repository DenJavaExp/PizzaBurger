import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.pizzaburger.R
import com.example.pizzaburger.databinding.FragmentCategoriesListBinding
import models.Category


class CategoriesListFragment : Fragment() {

    private var _binding: FragmentCategoriesListBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityMainBinding must not be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesListBinding.inflate(inflater, container, false)
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
        val categoryAdapter = CategoriesListAdapter(STUB.getCategories())
        binding.rvCategories.adapter = categoryAdapter

        categoryAdapter.setOnItemClickListener(object : CategoriesListAdapter.OnItemClickListener {
            override fun onItemClick(category: Category) {

                openRecipesByCategoryId()

            }
        })
    }

    private fun openRecipesByCategoryId() {
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            replace<RecipesListFragment>(R.id.mainContainer)
        }
    }
}