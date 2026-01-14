package com.example.pawpal.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pawpal.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {

    private lateinit var slogans: List<String>
    private var sloganIndex = 0

    data class Product(
        val title: String,
        val meta: String,
        val price: String,
        val imageUrl: String
    )

    private val featured = listOf(
        Product(
            "Grain-Free Kibble",
            "Dog · 2kg",
            "Rs 1,299",
            "https://m.media-amazon.com/images/I/81AVsPdbBFL._AC_SL1500_.jpg"
        ),
        Product(
            "Interactive Ball",
            "All pets",
            "Rs 499",
            "https://m.media-amazon.com/images/I/71QyM7t90TL._AC_SL1500_.jpg"
        ),
        Product(
            "Hypoallergenic Shampoo",
            "300 ml",
            "Rs 399",
            "https://m.media-amazon.com/images/I/61-shpV-0AL._AC_SL1400_.jpg"
        ),
        Product(
            "Dental Chews",
            "Pack of 12",
            "Rs 349",
            "https://m.media-amazon.com/images/I/81FxURioCoL._AC_SL1500_.jpg"
        ),

        Product(
            "Cool Cat Shades",
            "Russian Blue",
            "Rs 1,499",
            "https://m.media-amazon.com/images/I/61SsKdiBIYL._AC_SL1000_.jpg"
        ),
        Product(
            "Flea & Tick Collar",
            "Up to 8 months",
            "Rs 999",
            "https://m.media-amazon.com/images/I/61DrJLMx0LL._AC_.jpg"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        slogans = listOf(
            getString(R.string.home_tagline_1),
            getString(R.string.home_tagline_2),
            getString(R.string.home_tagline_3)
        )

        setupBottomNav(R.id.nav_home)

        val tvSlogan = findViewById<TextView>(R.id.tvSlogan)
        val btnShopNow = findViewById<View>(R.id.btnShopNow)
        val cardTrackOrders = findViewById<View>(R.id.cardTrackOrders)

        tvSlogan.setOnClickListener {
            sloganIndex = (sloganIndex + 1) % slogans.size
            tvSlogan.text = slogans[sloganIndex]
        }

        btnShopNow.setOnClickListener {
            startActivity(Intent(this, Shop::class.java))
            overridePendingTransition(0, 0)
            finish()
        }

        cardTrackOrders.setOnClickListener {
            startActivity(Intent(this, Orders::class.java))
            overridePendingTransition(0, 0)
            finish()
        }

        val rv = findViewById<RecyclerView>(R.id.rvFeatured)
        rv.layoutManager = GridLayoutManager(this, 2)
        rv.adapter = ProductAdapter(featured)
    }

    private fun setupBottomNav(selected: Int) {
        val bottom = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottom.selectedItemId = selected
        bottom.setOnItemSelectedListener { item ->
            if (item.itemId == selected) return@setOnItemSelectedListener true
            when (item.itemId) {
                R.id.nav_home -> startActivity(Intent(this, Home::class.java))
                R.id.nav_library -> startActivity(Intent(this, Shop::class.java))
                R.id.nav_journal -> startActivity(Intent(this, Care::class.java))
                R.id.nav_history -> startActivity(Intent(this, Orders::class.java))
                else -> return@setOnItemSelectedListener false
            }
            overridePendingTransition(0, 0)
            finish()
            true
        }
    }

    class ProductAdapter(private val data: List<Product>) :
        RecyclerView.Adapter<ProductAdapter.ProductVH>() {

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): ProductVH {
            val v = android.view.LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product, parent, false) // reuse the same card
            return ProductVH(v)
        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: ProductVH, position: Int) {
            holder.bind(data[position])
        }

        class ProductVH(view: android.view.View) : RecyclerView.ViewHolder(view) {
            private val ivThumb = view.findViewById<ImageView>(R.id.ivThumb)
            private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            private val tvMeta  = view.findViewById<TextView>(R.id.tvMeta)
            private val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
            private val btnOrder = view.findViewById<TextView>(R.id.btnOrder)

            fun bind(item: Product) {
                tvTitle.text = item.title
                tvMeta.text  = item.meta
                tvPrice.text = item.price

                ivThumb.load(item.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.placeholder)
                }

                btnOrder.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "Added ${item.title} to cart",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}