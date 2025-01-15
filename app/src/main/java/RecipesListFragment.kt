import CategoriesListFragment.Companion.ARG_CATEGORY_ID
import CategoriesListFragment.Companion.ARG_CATEGORY_NAME
import CategoriesListFragment.Companion.ARG_CATEGORY_URI
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pizzaburger.databinding.FragmentRecipesListBinding

class RecipesListFragment: Fragment() {

    private var categoryId: Int? = null
    private var categoryName: String? = null
    private var categoryUrl: String? = null

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
            arguments?.let {
                categoryId = it.getInt(ARG_CATEGORY_ID)
                categoryName = it.getString(ARG_CATEGORY_NAME)
                categoryUrl = it.getString(ARG_CATEGORY_URI)
            }
    }
}