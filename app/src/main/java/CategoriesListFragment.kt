import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.pizzaburger.R
import com.example.pizzaburger.databinding.FragmentCategoriesListBinding


class CategoriesListFragment : Fragment() {

    private var _binding: FragmentCategoriesListBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityMainBinding must not be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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
            override fun onItemClick(categoryId: Int) {

                openRecipesByCategoryId(categoryId)

            }
        })
    }



    private fun openRecipesByCategoryId(categoryId: Int) {
        val category = STUB.getCategories().find { it.id == categoryId }
        val categoryName = category?.title
        val categoryUrl = category?.imageUrl
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            replace<RecipesListFragment>(R.id.mainContainer)


            val bundle = Bundle().apply {
                putInt("ARG_CATEGORY_ID", categoryId)
                putString("ARG_CATEGORY_NAME", categoryName)
                putString("ARG_CATEGORY_IMAGE_URL", categoryUrl)
            }

            replaceFragment(RecipesListFragment(), bundle)
        }
    }

    private fun replaceFragment(fragment: RecipesListFragment, args: Bundle) {
        fragment.arguments = args
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object{
        const val ARG_CATEGORY_ID = "arg_category_id"
        const val ARG_CATEGORY_NAME = "arg_category_name"
        const val ARG_CATEGORY_URI = "arg_category_uri"

    }
}