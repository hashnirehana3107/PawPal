package com.example.pawpal.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pawpal.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Shop : AppCompatActivity() {

    data class Item(
        val title: String,
        val meta: String,
        val price: String,
        val imageUrl: String
    )

    private val items = listOf(
        Item(
            "Puppy Starter Pack",
            "Bundle · Dog",
            "Rs 1,999",
            "https://m.media-amazon.com/images/I/61Gn2jJVtDL._AC_SL1080_.jpg"
        ),
        Item(
            "Catnip Mouse Toy",
            "Cat",
            "Rs 199",
            "https://m.media-amazon.com/images/I/71eYpjdkSPL._AC_SL1500_.jpg"
        ),
        Item(
            "Omega-3 Supplement",
            "All pets · 60 caps",
            "Rs 699",
            "https://m.media-amazon.com/images/I/811shC-r4cL._AC_SL1500_.jpg"
        ),
        Item(
            "Harness & Leash Set",
            "Dog · M",
            "Rs 899",
            "https://m.media-amazon.com/images/I/71xgLaOGVpL._AC_SL1500_.jpg"
        ),
        Item(
            "Stain & Odor Remover",
            "500ml",
            "Rs 349",
            "https://m.media-amazon.com/images/I/71NzS0XbVxL._AC_SL1500_.jpg"
        ),
        Item(
            "Slow Feeder Bowl",
            "Dog · L",
            "Rs 499",
            "https://m.media-amazon.com/images/I/719YHkRVmhL._AC_SL1500_.jpg"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        setupBottomNav(R.id.nav_library)

        val rv = findViewById<RecyclerView>(R.id.rvProducts)
        rv.layoutManager = GridLayoutManager(this, 2)
        rv.adapter = ItemAdapter(items)
    }

    private fun setupBottomNav(selected: Int) {
        val bottom = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottom.selectedItemId = selected
        bottom.setOnItemSelectedListener {
            val handled = when (it.itemId) {
                R.id.nav_home -> { startActivity(Intent(this, Home::class.java)); true }
                R.id.nav_library -> true
                R.id.nav_journal -> { startActivity(Intent(this, Care::class.java)); true }
                R.id.nav_history -> { startActivity(Intent(this, Orders::class.java)); true }
                else -> false
            }
            overridePendingTransition(0, 0)
            if (handled && it.itemId != R.id.nav_library) finish()
            handled
        }
    }

    class ItemAdapter(private val data: List<Item>) :
        RecyclerView.Adapter<ItemAdapter.VH>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product, parent, false)
            return VH(v)
        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(data[position])

        class VH(view: View) : RecyclerView.ViewHolder(view) {
            private val cardRoot = view.findViewById<View>(R.id.cardRoot)
            private val ivThumb  = view.findViewById<ImageView>(R.id.ivThumb)
            private val tvTitle  = view.findViewById<TextView>(R.id.tvTitle)
            private val tvMeta   = view.findViewById<TextView>(R.id.tvMeta)
            private val tvPrice  = view.findViewById<TextView>(R.id.tvPrice)
            private val btnOrder = view.findViewById<TextView>(R.id.btnOrder)

            fun bind(item: Item) {
                tvTitle.text = item.title
                tvMeta.text  = item.meta
                tvPrice.text = item.price

                ivThumb.load(item.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.placeholder)   // add a simple drawable if you like
                    error(R.drawable.placeholder)
                }

                // Tap anywhere on the card
                cardRoot.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "Open details for ${item.title}",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Example: itemView.context.startActivity(Intent(itemView.context, ProductDetail::class.java))
                }

                // Tap Order
                btnOrder.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "Added ${item.title} to cart",
                        Toast.LENGTH_SHORT
                    ).show()
                    // TODO: hook to your cart flow
                }
            }
        }
    }
}