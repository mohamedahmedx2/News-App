import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.WebViewActivity
import com.example.newsapp.databinding.ActivityBottomSheetNewsBinding
import com.example.newsapp.model.newsResponse.News
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewsBottomSheetFragment(private val newsItem: News) : BottomSheetDialogFragment() {

    private var _binding: ActivityBottomSheetNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

        ): View {
        _binding = ActivityBottomSheetNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleBottom.text = newsItem.title
        Glide.with(this)
            .load(newsItem.urlToImage)
            .error(R.drawable.image_failed)
            .into(binding.imageBottom)
        (view.parent as View).setBackgroundResource(android.R.color.transparent)

        binding.buttonArticel.setOnClickListener {
            openNewsLink(newsItem)

        }

    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun openNewsLink(news: News) {
        val url = news.url

        if (!url.isNullOrEmpty()) {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            intent.putExtra("URL", url)
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "الرابط غير متوفر", Toast.LENGTH_SHORT).show()
        }
    }

//    private fun openNewsLink(news: News, context: Context) {
//        val url = news.url
//
//        if (!url.isNullOrEmpty()) {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//            if (intent.resolveActivity(context.packageManager) != null) {
//                context.startActivity(intent)
//            } else {
//                Toast.makeText(context, "لا يوجد متصفح متاح", Toast.LENGTH_SHORT).show()
//            }
//        } else {
//            Toast.makeText(context, "الرابط غير متوفر", Toast.LENGTH_SHORT).show()
//        }
//    }


    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val bottomSheet = view?.parent as View
        val params = bottomSheet.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(25, 0, 25, 100)
        bottomSheet.layoutParams = params
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
